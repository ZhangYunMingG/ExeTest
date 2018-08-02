package jvm.concurrent;

// 在变量中要加volatile, 否则会出现内存不可见问题
public class VolatilTest {
	
	private static volatile boolean flag = false; 
	public static void main(String[] args) {
		
		Thread run = new Thread(new Run());
		Thread r = new Thread(new R());
		r.start();
		run.start();
		
	}
	
	static class Run implements Runnable
	{
		public void run() {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("Run!");
			flag = true;
		}
	}
	
	static class R implements Runnable
	{
		public void run() {
			while(!flag) ;
			System.out.println("R!");
		}
	}
}
