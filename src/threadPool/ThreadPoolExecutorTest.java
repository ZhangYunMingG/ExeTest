package threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolExecutorTest {
	
	public static void main(String[] args) {
		
		BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
		int coreThread = 5;
		int maxThread = 10;
		long time = 10;
		TimeUnit unit = TimeUnit.SECONDS;
		ExecutorService service = new ThreadPoolExecutor(coreThread, maxThread, time, unit, queue);
		
	}

}
