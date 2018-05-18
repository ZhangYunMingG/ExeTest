package design.agent;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.CallbackFilter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import net.sf.cglib.proxy.NoOp;

public class MethodFilterTest {
	
	public static void main(String[] args) {
		
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(BookServiceImpl.class);
		enhancer.setCallbackFilter(new BookFilter());
		
		Handler hand = new Handler("boss");
		enhancer.setCallback(hand);
		enhancer.setCallbacks(new Callback[]{hand,NoOp.INSTANCE});     
		
		BookService service = (BookService)enhancer.create();
		service.create();
		
	}
	
	static interface BookService 
	{
		public void create();
		public void query();
		public void update();
		public void delete();
	}
	
	static class BookServiceImpl implements BookService
	{
		@Override
		public void create() {
			System.out.println("create is running!");
		}
		@Override
		public void query() {
			System.out.println("query is running!");
		}
		@Override
		public void update() {
			System.out.println("update is running!");
		}
		@Override
		public void delete() {
			System.out.println("delete is running!");
		}
	}
	
	static class Handler implements MethodInterceptor
	{
		private String name;
		public Handler(String name) {
			this.name = name;
		}
		@Override
		public Object intercept(Object obj, Method method, Object[] arr, MethodProxy proxy) throws Throwable {
			System.out.println("deal --- before ");
		//	method.invoke(obj, arr);  运行时，会报错！！！
			if(!"boss".equals(name)) {
				System.out.println("you are limit!!");
				return null;
			}
			System.out.println(method.getName());
			proxy.invokeSuper(obj, arr);
			System.out.println("deal --- after ");
			return null;
		}

	}
	
	static class BookFilter implements CallbackFilter
	{
		@Override
		public int accept(Method method) {
			if(!"query".equalsIgnoreCase(method.getName()))
				return 0;
			return 1;
		}
	}

}
