package threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CachedTest {
	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newCachedThreadPool();
		List<Future<String>> futureList = new ArrayList<Future<String>>();
		Call call = new Call();
		for(int i = 0; i < 20; i++) {
			futureList.add(executorService.submit(call));
		}
		executorService.shutdown();
		for (Future<String> future : futureList) {
			try {
				System.out.println(future.get());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	static class Call implements Callable<String>
	{
		public String call() throws Exception {
			System.out.println(Thread.currentThread() + " : ");
			return Thread.currentThread().getName();
		}
	}
}


