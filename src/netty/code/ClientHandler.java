package netty.code;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ClientHandler extends ChannelHandlerAdapter {

	@Override
	public void channelActive(ChannelHandlerContext context) {
		byte[] strbyte = "hello ,lige\n".getBytes();
		ByteBuf buf = Unpooled.buffer(strbyte.length);
		buf.writeBytes(strbyte);
		context.writeAndFlush(buf);
	}
	
	@Override
	public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
		String str = (String)msg;
		System.out.println("server -> client:" + str);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable able) throws Exception {
		
	}
	
}
