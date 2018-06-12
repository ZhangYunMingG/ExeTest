package netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
		String str = (String)msg;
		System.out.println("client -> server :" + str);
		
		String string = "resvice ...\n";
		ByteBuf buf = Unpooled.copiedBuffer(string.getBytes());
		context.writeAndFlush(buf);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext context) {
		
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable able) {
		System.out.println("exceptionCaught");
		context.close();
	}
	
}
