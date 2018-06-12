package netty.code;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

public class Client {
	
	public void connect(String ip, int port) throws Exception {
		
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class)
				.option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChildHandlerChannel());
			
			ChannelFuture f = b.connect(ip, port).sync();
			f.channel().closeFuture().sync();
			
		} finally {
			group.shutdownGracefully();
		}
	}
	
	class ChildHandlerChannel extends ChannelInitializer<SocketChannel> {
	
		@Override
		public void initChannel(SocketChannel channel) throws Exception {
			
			channel.pipeline().addLast(new LineBasedFrameDecoder(1024));
			channel.pipeline().addLast(new StringDecoder());
			channel.pipeline().addLast(new ClientHandler());
		}
	}
	
	public static void main(String[] args) throws Exception {
		new Client().connect("127.0.0.1", 60000);
	}

}
