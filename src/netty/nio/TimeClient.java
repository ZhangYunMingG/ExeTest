package netty.nio;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class TimeClient {

	public void connect(String host, int port) throws Exception {
		
		//	配置客户端NIO线程组
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChildChannelHandler());
			
			//	发起异步连接操作
			ChannelFuture f = b.connect(host, port).sync();
			//	等待客户端链路关闭
			f.channel().closeFuture().sync();
					
		} finally {
			//	优雅退出,释放 NIO 线程池资源
			group.shutdownGracefully();
		}
	}
	
	class ChildChannelHandler extends ChannelInitializer<SocketChannel>
	{
		@Override
		protected void initChannel(SocketChannel channel) throws Exception {
//			channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
//			channel.pipeline().addLast(new StringDecoder());
			
			ByteBuf buf = Unpooled.copiedBuffer("$_".getBytes());
			channel.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, buf));
			channel.pipeline().addLast(new StringDecoder());
			channel.pipeline().addLast(new TimeClientHandler());
		}
	}
	
	public static void main(String[] args) throws Exception {
		new TimeClient().connect("127.0.0.1", 60000);
	}
}
