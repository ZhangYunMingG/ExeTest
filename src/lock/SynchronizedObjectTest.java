package lock;

import java.util.ArrayList;
import java.util.List;

//  在synchronized修饰的对象,例如synchronized(object);
//  其他的含有synchronized(object)的不同对象  也是与他同步的
//  synchronized(object)中的Object是可以修改的
public class SynchronizedObjectTest {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		Thread thread = new Thread(new Array(list));
		thread.start();
		list.add("123");
		
		Thread t1 = new Thread(new Run());
		Thread t2 = new Thread(new R());
		t1.start();
		t2.start();
		
	}
	
	static class Array implements Runnable
	{
		private List<String> list;
		public Array(List<String> list) {
			this.list = list;
		}
		public void run() {
			synchronized(list) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	static class Run implements Runnable
	{
		public void run() {
			synchronized(Integer.valueOf(1)) {
				System.out.println("run is running");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("end!");
			}
		}
	}
	
	static class R implements Runnable
	{
		public void run() {
			synchronized(Integer.valueOf(1)) {
				System.out.println("r is running");
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
