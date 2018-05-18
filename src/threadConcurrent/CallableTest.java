package threadConcurrent;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class CallableTest {
	
	public static void main(String[] args) {
		
		Call call = new Call();
		FutureTask<String> future = new FutureTask<String>(call);
		Thread thread = new Thread(future);
		thread.start();
		try {
			System.out.println(future.get());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	static class Call implements Callable<String> 
	{
		public String call() throws Exception {
			System.out.println("Suc!");
			return "success";
		}
	}

}
