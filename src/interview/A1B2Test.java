package interview;

public class A1B2Test {
	
	//	一个线程打印1234...90123..,一个线程打印ABCD...XYZABC...,最后打印1A2B3C...
	public static void main(String[] args) throws Exception {
		new Thread(new R1()).start();
		Thread.sleep(500);
		new Thread(new R2()).start();
	}
	
	static class R1 implements Runnable
	{
		public void run() {
			int i = 0;
			while(true) {
				if(i == 9)
					i = i - 10;
				System.out.println(++i);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class R2 implements Runnable
	{
		public void run() {
			int i = 65;
			while(true) {
				if(i == 91)
					i = i - 26;
				char c = (char)i;
				System.out.println(c);
				i ++;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
