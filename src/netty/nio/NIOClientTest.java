package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

public class NIOClientTest {

	public void init() throws IOException {
		Selector select = Selector.open();
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		boolean flag = channel.connect(new InetSocketAddress("localhost", 60000));
		System.out.println(flag);
		if(flag) {
			channel.register(select, SelectionKey.OP_READ);
			doWrite(channel, "66666");
		} else {
			channel.register(select, SelectionKey.OP_CONNECT);
		}
		
	}
	
	private void doWrite(SocketChannel channel, String data) throws IOException {
		byte[] bytes = data.getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		buffer.put(bytes);
		System.out.println(Arrays.toString(bytes));
		buffer.flip();
		channel.write(buffer);
	}
	
	public static void main(String[] args)
	{
		try {
			new NIOClientTest().init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
