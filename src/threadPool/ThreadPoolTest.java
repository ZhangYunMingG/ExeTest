package threadPool;

public class ThreadPoolTest {  
    public static void main(String[] args) {  
        // 创建3个线程的线程池  
        ThreadPool t = ThreadPool.getThreadPool(3);  
        t.execute(new Thread[] { new Task(), new Task(), new Task() });  
        t.execute(new Thread[] { new Task(), new Task(), new Task() });  
        System.out.println(t);  
        t.destroy();// 所有线程都执行完成才destory  
  //      System.out.println(t);  
    }  
  
    // 任务类  
    static class Task extends Thread {  
        private static volatile int i = 1;  
  
        @Override  
        public void run() {// 执行任务  
      //  	synchronized(Task.class)
        	{
        		 System.out.println("任务 " + (i++) + " 完成");  
        	}
        }  
    }  
}  
