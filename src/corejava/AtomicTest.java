package corejava;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicTest {
	
	public static void main(String[] args) {
		
		AtomicInteger atomic =new AtomicInteger();
		System.out.println(atomic.get());
		atomic.incrementAndGet();
		System.out.println(atomic.get());
		
	}

}
