package lock;

public class BaseSynchronizedTest {
	
	private static Object obj = new Object();
	public static volatile int sum = 9;
	public static void main(String[] args) {
		
		R1 r1 = new R1();
		R2 r2 = new R2();
		Thread t1 = new Thread(r1);
		Thread t2 = new Thread(r2);
		t1.start();
		t2.start();
		
	}
	
	static class R1 implements Runnable
	{
		@Override
		public void run() {
			for(int i = 0; i < 10000000; i ++)
			{
				synchronized(obj)
				{
					sum ++;
					System.out.println(sum);
				}
			}
		}
	}
	
	static class R2 implements Runnable
	{
		@Override
		public void run() {
			for(int i = 0; i < 10000000; i ++)
			{
				synchronized(obj)
				{
					sum --;
					System.out.println(sum);
				}
			}
		}
	}
}
