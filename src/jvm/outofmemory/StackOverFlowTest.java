package jvm.outofmemory;

public class StackOverFlowTest {

	private int length = 1;
	private void stack() {
		length ++;
		stack();
	}
	
	public static void main(String[] args) {
		
		StackOverFlowTest test = new StackOverFlowTest();
		try {
			test.stack();
		} catch (Exception e) {
			System.out.println("----");
			System.out.println(""+test.length);
			throw e;
		}
		
		
	}
}
