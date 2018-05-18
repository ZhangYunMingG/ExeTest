package corejava;

import java.lang.reflect.Field;

public class MemoryTranTest {

	public static void main(String[] args) {
		Integer a = new Integer(1);
		Integer b = new Integer(2);
		System.out.println("a="+ a + ", b=" + b);
		tran(a, b);
		System.out.println("a="+ a + ", b=" + b);
	}
	
	private static void tran(Integer a, Integer b) {
		try {
			Field field = Integer.class.getDeclaredField("value");
			field.setAccessible(true);
			Integer tmp = new Integer(a);
			field.set(a, b);
			field.set(b, tmp);
		} catch (Exception e) {
			System.out.println("fail!");
		}
		
	}
}
