package design.agent;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Random;

public class DynamicAgentTest {

	public static void main(String[] args) throws Exception {
		
		Car car = new Car();
		TimeHandler timeHandler = new TimeHandler(car);
		Class<?> cls = car.getClass();
		Moveable moveable = (Moveable)Proxy.newProxyInstance(
				cls.getClassLoader(), cls.getInterfaces(), timeHandler);
		moveable.move();
		
	}
	
	static interface Moveable
	{
		public void move() throws Exception;
	}
	
	static class Car implements Moveable
	{
		public void move() throws Exception {
			Thread.sleep(new Random().nextInt(1000));
			System.out.println("汽车行驶中…");
		}
	}
	
	static class TimeHandler implements InvocationHandler 
	{
		private Object target;
		public TimeHandler(Object target) {
			this.target = target;
		}
		@Override
		public Object invoke(Object obj, Method method, Object[] aobj) throws Throwable {
			long start = System.currentTimeMillis();
			System.out.println("汽车开始行驶！");
			method.invoke(target, aobj);
			long end = System.currentTimeMillis();
			System.out.println("汽车结束行驶…汽车行驶时间：" + (end - start) +"毫秒！");
			return null;
		}
	}
}
