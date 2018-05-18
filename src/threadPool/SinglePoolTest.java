package threadPool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SinglePoolTest {
	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		List<Future<String>> futrueList = new ArrayList<Future<String>>();
		for(int i = 0; i < 5; i ++) {
			futrueList.add(executorService.submit(new SinglePool(i)));
		}
		
		executorService.shutdown();
		
		try {
			
			for (Future<String> future : futrueList) {
				System.out.println(future.get());;
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		
		
	}
}

class SinglePool implements Callable<String> 
{
	private int id;
	public SinglePool(int id) {
		this.id = id;
	}
	@Override
	public String call() throws Exception {
		System.out.println("single pool:" + id);
		Thread.sleep(50);
		return "suc:" + id;
	}
}
