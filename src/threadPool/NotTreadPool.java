package threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class NotTreadPool {
	
	// 使用callable起一个线程
	public static void main(String[] args) {
		
		List<Future<String>> futureList = new ArrayList<Future<String>>();
		
		for(int i = 0; i < 5; i ++) {
			FutureTask<String> future = new FutureTask<String>(new SinglePool(i));
			futureList.add(future);
			Thread thread = new Thread(future);
			thread.start();
		}
		
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
}
