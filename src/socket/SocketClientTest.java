package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class SocketClientTest {
	
	public static void main(String[] args) {
		
	//	for(int i = 0;i < 10000; i ++) {
			new Thread(new Run()).start();
	//	}
		
	}
	
	static class Run implements Runnable
	{
		public void run() {
			try {
				Socket socket = new Socket("localhost", 60000);
				OutputStream os = null;
				OutputStreamWriter osw = null;
				BufferedWriter bw = null;
				
				InputStream is = null;
				InputStreamReader isr = null;
				BufferedReader br = null;
				try {
					os = socket.getOutputStream();
					osw = new OutputStreamWriter(os);
					bw = new BufferedWriter(osw);
					
					is = socket.getInputStream();
					isr = new InputStreamReader(is);
					br = new BufferedReader(isr);
					String str;
					
					bw.write("123");
					bw.newLine();
					bw.write("hello i am client!!! ");
					bw.newLine();
					bw.write("exit");
					bw.newLine();
					bw.flush();
					Thread.sleep(10000);
					
					while((str = br.readLine()) != null) {
						System.out.println(str);
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
