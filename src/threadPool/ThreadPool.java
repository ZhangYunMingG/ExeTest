package threadPool;

import java.util.ArrayList;
import java.util.List;

public class ThreadPool {

	private static int work_num = 5;
	private static ThreadPool threadPool = null;
	private List<Thread> queue = new ArrayList<Thread>();
	private WorkThread[] workThread = null;

	private ThreadPool(int num) {
		work_num = num; 
		workThread = new WorkThread[work_num];
		for (int i = 0; i < num; i++) {
			workThread[i] = new WorkThread();
			workThread[i].start();
		}

	}
	
	public void execute(Thread run) {
		queue.add(run);
		queue.notifyAll();
	}
	
	public void execute(Thread[] t)
	{
		synchronized(queue)
		{
			for (Thread thread : t) {
				queue.add(thread);
			}
			queue.notify();
		}
	}
	
	public void execute(List<Thread> list) {
		for (Thread thread : list) {
			queue.add(thread);
		}
		queue.notifyAll();
	}
	
	public void destroy()
	{
		while(!queue.isEmpty())
		{
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < workThread.length; i ++)
		{
			workThread[i].stopWork();
			workThread[i] = null;
		}
		threadPool = null;
		queue.clear();;
		
	}
	
	
	private ThreadPool() {
		this(5);
	}

	public static ThreadPool getThreadPool(int num) {
		if(num <= 0)
			num = work_num;
		if(null == threadPool)
			return new ThreadPool(num);
		return threadPool;
	}
	
	class WorkThread extends Thread
	{
		boolean flag = true;
		public void run() 
		{
			while (flag) 
			{
				Runnable r = null;
				synchronized(queue)
				{
					while(flag && queue.isEmpty())
					{
						try {
							queue.wait(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					if(!queue.isEmpty())
						r = queue.remove(0);
				}
				
				if(r != null)
				{
					r.run();
				}
				r = null;
			}
		}
		private void stopWork()
		{
			flag = false;
		}
	}
	
	public String toString() {  
        return "WorkThread number:" + work_num + "  finished task number:" ; 
    }  
	
//	public void 
	
	

}
