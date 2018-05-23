package netty.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeServerHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
		String str = (String)msg;
		System.out.println("client >> server: " + str);
		
		String send = "client $_hello $_yyy$_";
		ByteBuf resp = Unpooled.copiedBuffer(send.getBytes());
		context.writeAndFlush(resp);
//		nio通信
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String str = new String(req, "utf-8");
//		System.out.println("server receive --" + str + "--!");
		
//		nio的防止粘包通信
//		String send = "hello client, i am receive !\n";
//		ByteBuf resp = Unpooled.copiedBuffer(send.getBytes());
//		context.writeAndFlush(resp);
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext context) throws Exception {
	//	context.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		System.out.println("exceptionCaught");
		context.close();
	}
}
