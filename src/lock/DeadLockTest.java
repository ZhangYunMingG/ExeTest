package lock;

public class DeadLockTest {
	
	public static void main(String[] args)
	{
		Object A = new Object();
		Object B = new Object();
		ThreadA a = new ThreadA(A, B);
		ThreadB b = new ThreadB(A, B);
		a.start();
		b.start();
		
	}
}
class ThreadA extends Thread
{
	private Object A;
	private Object B;
	public ThreadA(Object A, Object B) {
		this.A = A;
		this.B = B;
	}
	
	public void run() {
		
		synchronized (A) {
			System.out.println("ThreadA获取了锁A!");
			try {
				Thread.sleep(2000);
				synchronized (B) {
					System.out.println("ThreadA获取了锁B!");
					System.out.println("ThreadA is end !");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	}
}

class ThreadB extends Thread
{
	private Object A;
	private Object B;
	public ThreadB(Object A, Object B)
	{
		this.A = A;
		this.B = B;
	}
	public void run()
	{
		synchronized(B) {
			try {
				System.out.println("ThreadB获取了锁B!");
				Thread.sleep(2000);
				synchronized(A) {
					System.out.println("ThreadB获取了锁A!");
					System.out.println("ThreadB is end!");
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class ThreadAA extends Thread
{
	private Object objLock = new Object();
	private Object A;
	private Object B;
	public ThreadAA (Object A, Object B) {
		this.A = A;
		this.B = B;
	}
	
	@Override
	public void run() {
		synchronized(objLock) {
			System.out.println(A.equals(""));
	//		Thread
			System.out.println(B.equals(""));
		}
	}
}