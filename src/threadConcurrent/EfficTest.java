package threadConcurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//  关于线程池性能的测试
public class EfficTest {
	
	public static void main(String[] args) {
		
		long l1 = System.currentTimeMillis();
		for(int i = 0;i < 100000; i ++) {
			new Thread(new Run()).start();
		}
		long l2 = System.currentTimeMillis();
		ExecutorService exeService = Executors.newCachedThreadPool();
		for(int i = 0;i < 100000; i ++) {
			exeService.execute(new Run());
		}
		exeService.shutdown();
		System.out.println(exeService.isTerminated());;
		while(!exeService.isTerminated());
		long l3 = System.currentTimeMillis();
		System.out.println("new Run() 耗时" + (l2-l1));
		System.out.println("线程池 耗时" + (l3-l2));
		
	}
	
	static class Run implements Runnable
	{
		public void run () {
			
		}
	}

}
