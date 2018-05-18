package corejava;

import java.lang.reflect.Method;

/*
 * 反射
 * */
public class ReflectMethodTest {
	public static void main(String[] args) throws Exception {
		
		// 反射静态内部类的对象
		Reflect reflect = (Reflect)Class.forName("corejava.ReflectMethodTest$Reflect").newInstance();
	//	Reflect reflect = new Reflect();
		// 反射无参的方法
		Method method = reflect.getClass().getMethod("fun");
		method.invoke(reflect);
		
		// 反射有参的方法
		Method m = Reflect.class.getMethod("fun", String.class);
		System.out.println(m.invoke(reflect, "hello!"));
		
		// 反射内部类的对象
		Class<?> out = Class.forName("corejava.ReflectMethodTest");
		Class<?> in = Class.forName("corejava.ReflectMethodTest$Inner");
		System.out.println(in.getDeclaredConstructor(out).newInstance(out.newInstance()));
		
	}
	
	class Inner
	{
		public Inner() {
			System.out.println("inner is init");
		}
	}
	
	static class Reflect 
	{
		public void fun() {
			System.out.println("Reflect fun() !!!");
		}
		
		public String fun(String str) {
			System.out.println("Reflect fun()" + str);
			return str;
		}
	}

}
