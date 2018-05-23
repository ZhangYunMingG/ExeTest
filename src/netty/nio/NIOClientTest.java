package netty.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

//	传统的NIO客户端
public class NIOClientTest {

	private volatile boolean stop = false;
	public void init() throws IOException {
		Selector select = Selector.open();
		SocketChannel channel = SocketChannel.open();
		channel.configureBlocking(false);
		boolean flag = channel.connect(new InetSocketAddress("127.0.0.1", 60000));
		System.out.println(flag);
		if(flag) {
			channel.register(select, SelectionKey.OP_READ);
			doWrite(channel, "66666");
		} else {
			channel.register(select, SelectionKey.OP_CONNECT);
		}
		
		 while (!stop){
			 select.select(1000);
	            Set<SelectionKey> keys = select.selectedKeys();
	            Iterator<SelectionKey> it = keys.iterator();
	            SelectionKey key = null;
	            while (it.hasNext()){
	                key = it.next();
	                it.remove();
	                SocketChannel sc = (SocketChannel) key.channel();
	                // OP_CONNECT 两种情况，链接成功或失败这个方法都会返回true
	                if (key.isConnectable()){
	                    // 由于非阻塞模式，connect只管发起连接请求，finishConnect()方法会阻塞到链接结束并返回是否成功
	                    // 另外还有一个isConnectionPending()返回的是是否处于正在连接状态(还在三次握手中)
	                    if (channel.finishConnect()) {
	                       /* System.out.println("准备发送数据");
	                        // 链接成功了可以做一些自己的处理
	                        channel.write(charset.encode("I am Coming"));
	                        // 处理完后必须吧OP_CONNECT关注去掉，改为关注OP_READ
	                        key.interestOps(SelectionKey.OP_READ);*/
	                          sc.register(select,SelectionKey.OP_READ);
	                    //    new Thread(new DoWrite(channel)).start();
	                      doWrite(channel, "66666666");
	                    }else {
	                        //链接失败，进程推出或直接抛出IOException
	                        System.exit(1);
	                    }
	                } if(key.isReadable()){
	                //读取服务端的响应
	                    ByteBuffer buffer = ByteBuffer.allocate(1024);
	                     int readBytes = sc.read(buffer);
	                    String content = "";
	                    if (readBytes>0){
	                        buffer.flip();
	                        byte[] bytes = new byte[buffer.remaining()];
	                        buffer.get(bytes);
	                        content+=new String(bytes);
	                        stop=true;
	                    }else if(readBytes<0) {
	                        //对端链路关闭
	                        key.channel();
	                        sc.close();
	                    }
	                    System.out.println(content);
	                    key.interestOps(SelectionKey.OP_READ);
	                }
	            }
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
