package netty.nio;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeServer {

	//	注意,System.getProperty("line.separator") 为换行符,在粘包问题中很重要,不要忘记！！！
	//	可能是writeAndFlush和 \n 必须一起使用
	public void bind(int port) throws Exception {
		//	配置服务端的NIO线程组
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workerGroup)
				.channel(NioServerSocketChannel.class)
					.option(ChannelOption.SO_BACKLOG, 1024)
						.childHandler(new ChildChannelHandler());
			//	绑定端口,同步等待成功
			ChannelFuture f = b.bind(port).sync();
			
			//	等待服务端监听端口关闭
			f.channel().closeFuture().sync();
		} finally {
			//	优雅退出,释放线程池资源
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
		
		@Override
		protected void initChannel(SocketChannel channel) throws Exception {
			
			//	解决nio的粘包问题
//			channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//			channel.pipeline().addLast(new StringDecoder());
			//	解决nio的分隔符问题
//			ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
//			channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
//			channel.pipeline().addLast(new StringDecoder());
			
//			解决nio的定长分解问题
			channel.pipeline().addLast(new FixedLengthFrameDecoder(10));
			channel.pipeline().addLast(new StringDecoder());
			channel.pipeline().addLast(new TimeServerHandler());
		}
	}
	
	public static void main(String[] args) throws Exception {
		new TimeServer().bind(60000);
	}
}
