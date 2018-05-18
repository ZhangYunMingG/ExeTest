package lock;

public class JoinTest {
	public static void main(String[] args)
	{
		R1 r1 = new R1("r1");
		R1 r2 = new R1("r2");
		r1.start();
		r2.start();
		try {
			r1.join();
			System.out.println("r1!!!");
			r2.join();
			System.out.println("r2!!!");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
 class R1 extends Thread
 {
	 private String str;
	 public R1(String str)
	 {
		 this.str = str;
	 }
	 public void run()
	 {
		 System.out.println(str + " is start!");
	 }
 }