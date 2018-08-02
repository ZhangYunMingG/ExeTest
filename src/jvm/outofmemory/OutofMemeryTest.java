package jvm.outofmemory;

import java.util.concurrent.atomic.AtomicInteger;

public class OutofMemeryTest {

	private static AtomicInteger integer = new AtomicInteger(0);
	public static void main(String[] args) {
		
//		List<Object> list = new ArrayList<Object>();
//		while(true) {
//			list.add(new Object());
//		}
		while(true) {
			new Thread(new Run()).start();
		}
	}
	
	static class Run implements Runnable {
		public void run() {
			System.out.println(integer.incrementAndGet());
			while(true);
		}
	}
}
