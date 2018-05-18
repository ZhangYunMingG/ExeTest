package design.agent;

import java.lang.reflect.Method;
import java.util.Random;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGlibAgentTest {
	
	public static void main(String[] args) throws Exception {
		
		Car car = new Car();
		TimeHandler handler = new TimeHandler(car);
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(handler);
		enhancer.setSuperclass(car.getClass());
		Moveable moveable = (Moveable)enhancer.create();
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
			System.out.println("汽车正在行驶！");
		}
	}
	
	static class TimeHandler implements MethodInterceptor
	{
		private Object target;
		public TimeHandler(Object target) {
			this.target = target;
		}

		@Override
		public Object intercept(Object arg0, Method method, Object[] obj, MethodProxy methodProxy) throws Throwable {
			long start = System.currentTimeMillis();
			System.out.println("汽车开始行驶");
			method.invoke(target, obj);
			long end = System.currentTimeMillis();
			System.out.println("汽车结束行驶…汽车行驶时间："+ (end - start) +"毫秒！");
			return null;
		}
	}

}
