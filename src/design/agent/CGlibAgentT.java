package design.agent;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CGlibAgentT {
	
	public static void main(String[] args) {
		
		Car car = new Car();
		TimeHandler time = new TimeHandler(car);
		Enhancer enhancer = new Enhancer();
		enhancer.setCallback(time);
		enhancer.setSuperclass(car.getClass());
		Moveable moveable = (Moveable)enhancer.create();
		moveable.move();
	}
	
	static interface Moveable
	{
		public void move();
	}
	
	static class Car implements Moveable
	{
		public void move() {
			System.out.println("car is start!! ");
		}
	}
	
	static class TimeHandler implements MethodInterceptor
	{
		private Object target;
		public TimeHandler(Object target) {
			this.target = target;
		}
		public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Exception {
			System.out.println(" before !! ");
			method.invoke(target, arr);
			System.out.println(" after !! ");
			return null;
		}
	}

}
