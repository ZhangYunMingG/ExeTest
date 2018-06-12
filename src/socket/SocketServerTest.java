package socket;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SocketServerTest {
	
	public static void main(String[] args) {
		
		try {
			ExecutorService service = Executors.newFixedThreadPool(100);
			ServerSocket server = new ServerSocket(60000);
			System.out.println(server);
			while(true) {
				Socket socket = server.accept();
				service.execute(new Run(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	static class Run implements Runnable
	{
		private Socket socket;
		public Run(Socket socket) {
			this.socket = socket;
		}
		public void run() {
			System.out.println("have a client connect");
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
				while(!"exit".equals(str = br.readLine())) {
					System.out.println(str);
					if("123".equals(str)) {
						Thread.sleep(10000);
					}
				}
				bw.write("i am receive !!! ");
				bw.write("hello");
				Thread.sleep(100000);
				bw.newLine();
				bw.flush();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}
}
