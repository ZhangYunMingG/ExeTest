package socket;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;

public class NIOSocketServerTest {
	
	
	public static void main(String[] args) {
		
		try {
			ServerSocketChannel channel = ServerSocketChannel.open();
			ServerSocket socket = channel.socket();
			Selector select = Selector.open();
			socket.bind(new InetSocketAddress(60000));
			channel.configureBlocking(false);
			channel.register(select, SelectionKey.OP_ACCEPT);
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		
	}
	
}
