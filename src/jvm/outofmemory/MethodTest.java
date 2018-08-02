package jvm.outofmemory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MethodTest {
	
	public static void main(String[] args) throws Exception {
		
		Class<?> outCls = Class.forName("jvm.outofmemory.MethodTest");
		Class<?> inCls = Class.forName("jvm.outofmemory.MethodTest$Test");
		Constructor<?> testCon = inCls.getDeclaredConstructor(outCls);
		testCon.setAccessible(true);
		Test t = (Test)testCon.newInstance(outCls.newInstance());
		System.out.println(t);
		
		Field field = t.getClass().getDeclaredField("name");
		field.setAccessible(true);
		System.out.println(field.get(t));;
		
		Method method = t.getClass().getDeclaredMethod("get");
		method.setAccessible(true);
		System.out.println(method.invoke(t));
	}
	
	private class Test
	{
		private String name = "helo";
		private String get() {
			System.out.println("method is act!");
			return name;
		}
	}

}
