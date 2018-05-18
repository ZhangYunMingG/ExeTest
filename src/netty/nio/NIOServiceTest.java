package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServiceTest {
	
	public void init() throws IOException {
		
		// 创建一个选择器
		Selector select = Selector.open();
		// 创建ServerSocketChannel，并把它绑定到指定端口上
		ServerSocketChannel channel = ServerSocketChannel.open();
		channel.socket().bind(new InetSocketAddress(60000));
		// 设置为非阻塞模式
		channel.configureBlocking(false);
		channel.register(select, SelectionKey.OP_ACCEPT);
		
		while(true) {
			select.select(1000);
			Iterator<SelectionKey> itor = select.selectedKeys().iterator();
			SelectionKey key = null;
			while(itor.hasNext()) {
				key = itor.next();
				itor.remove();
				
				if(key.isAcceptable()) {
					ServerSocketChannel ssc = (ServerSocketChannel)key.channel();
					SocketChannel socketChannel = ssc.accept();
					socketChannel.configureBlocking(false);
					socketChannel.register(select, SelectionKey.OP_READ);
					key.interestOps(SelectionKey.OP_ACCEPT);
				}
				
				boolean flag = key.isReadable();
				if(flag) {
					SocketChannel socketChannel = (SocketChannel)key.channel();
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					StringBuilder stringBilder = new StringBuilder();
					System.out.println("before -- try -- 48");
					
					try {
						int readBytes = socketChannel.read(buffer);
						if(readBytes > 0) {
							System.out.println(readBytes + "-- 52");
							buffer.flip();
							byte[] bytes = new byte[buffer.remaining()];
							buffer.get(bytes);
							stringBilder.append(new String(bytes));
							System.out.println(stringBilder.toString());
							doWrite(socketChannel);
						}
						key.interestOps(SelectionKey.OP_READ);
					} catch (Exception e) {
						key.cancel();
						if(key.channel() != null) {
							key.channel().close();
						}
					}
					
				}
				
			}
		}
	}
	
	private void doWrite(SocketChannel channel) throws IOException {
		byte[] bytes = "service is accept".getBytes();
		ByteBuffer buffer = ByteBuffer.allocate(bytes.length);
		buffer.put(bytes);
		buffer.flip();
		channel.write(buffer);
	}
	
	public static void main(String[] args) {
		try {
			new NIOServiceTest().init();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
