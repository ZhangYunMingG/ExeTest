package design.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicAgentT {

	public static void main(String[] args) {
		
		Car car = new Car();
		TimeHandler time = new TimeHandler(car);
		Class<?> cls = car.getClass();
		Moveable moveable = (Moveable)Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), time);
		moveable.move();
	}
	
	static interface Moveable
	{
		public void move();
	}
	
	static class Car implements Moveable
	{
		public void move() {
			System.out.println("正在行驶！");
		}
	}
	
	static class TimeHandler implements InvocationHandler
	{
		private Object target;
		public TimeHandler(Object target) {
			this.target = target;
		}
		@Override
		public Object invoke(Object object, Method method, Object[] tar) {
			System.out.println("开车！");
			try {
				method.invoke(target, tar);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("结束！");
			return null;
		}
	}
}
