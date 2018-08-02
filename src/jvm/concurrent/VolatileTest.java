package jvm.concurrent;

public class VolatileTest {
	
	private static boolean flag = false;
	public static void main(String[] args) {
		
		new Thread(new Run()).start();
		while(!flag) {
			
		}
		System.out.println("main is stop!");
	}
	
	static class Run implements Runnable {
		public void run() {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flag = true;
			System.out.println(flag);
			fun(flag);
		}
		
		private void fun(boolean b) {
			Boolean lean = b;
			synchronized(lean) {
				b = true;
			}
		}
	}

}
