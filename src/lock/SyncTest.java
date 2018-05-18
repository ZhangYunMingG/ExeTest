package lock;


// synchronized(this)锁住的是这个对象,synchronized(*.class)锁住的是这个类
public class SyncTest {
	
	public static void main(String[] args) {
		
	//	Sync sync = new FairSync();
		Syn syn = new Syn();
		Thread t1 = new Thread(new R1(syn));
		Thread t2 = new Thread(new R2(new Syn()));
		Thread t3 = new Thread() {
			public void run() {
				new Other().other();
			}
		};
		
		
		t1.start();
		t2.start();
		t3.start();
	}
	
	static class R1 implements Runnable
	{
		private Syn syn;
		public R1(Syn syn) {
			this.syn = syn;
		}
		public void run() {
			syn.r1();
		}
	}
	static class R2 implements Runnable
	{
		private Syn syn;
		public R2(Syn syn) {
			this.syn = syn;
		}
		public void run() {
			syn.r2();
		}
	}
	
	static class Syn
	{
		public void r1() {
			synchronized(Syn.class) {
				try {
					System.out.println("r1 is start !");
					Thread.sleep(200);
					System.out.println("r1 is end !");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		public void r2() {
			synchronized(Syn.class) {
				try {
					System.out.println("r2 is start !");
					Thread.sleep(200);
					System.out.println("r2 is end !");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class Other
	{
		public void other() {
			synchronized(Syn.class) {
				try {
					System.out.println("other is start !");
					Thread.sleep(200);
					System.out.println("other is end !");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
