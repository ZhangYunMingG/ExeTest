package threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class CallableTest {
	
	public static void main(String[] args) throws Exception {
		
		List<Future<String>> list = new ArrayList<Future<String>>();
		for(int i = 0; i < 5; i ++) {
			Call call = new Call(i);
			FutureTask<String> task = new FutureTask<String>(call);
			Thread thread = new Thread(task);
			thread.start();
			list.add(task);
		}
		for (Future<String> future : list) {
			System.out.println(future.get());
		}
		
	}
	
	static class Call implements Callable<String>
	{
		private int id;
		public Call(int id) {
			this.id = id;
		}
		public String call() throws Exception {
			System.out.println(Thread.currentThread());
			return Thread.currentThread().getName() + " ,  id: " + id;
		}
	}

}
