package netty.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class TimeClientHandler extends ChannelHandlerAdapter {
	
	@Override
	public void channelActive(ChannelHandlerContext context) {
		byte[] req = ("client $_ send $_ request! $_" ).getBytes();
		for(int i = 0; i < 1; i ++) {
			ByteBuf buf = Unpooled.buffer(req.length);
		//	ByteBuf buf = Unpooled.copiedBuffer(req);
			buf.writeBytes(req);
			context.writeAndFlush(buf);
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext context) {
		context.flush();
	}
	
	@Override
	public void channelRead(ChannelHandlerContext context, Object msg) throws Exception {
		String str = (String)msg;
		System.out.println("server -> client: " + str);
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] req = new byte[buf.readableBytes()];
//		buf.readBytes(req);
//		String str = new String(req, "utf-8");
//		System.out.println("client receiver --" + str);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext context, Throwable cause) {
		context.close();
	}

}
