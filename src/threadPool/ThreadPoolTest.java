package threadPool;

public class ThreadPoolTest {  
    public static void main(String[] args) {  
        // ����3���̵߳��̳߳�  
        ThreadPool t = ThreadPool.getThreadPool(3);  
        t.execute(new Thread[] { new Task(), new Task(), new Task() });  
        t.execute(new Thread[] { new Task(), new Task(), new Task() });  
        System.out.println(t);  
        t.destroy();// �����̶߳�ִ����ɲ�destory  
  //      System.out.println(t);  
    }  
  
    // ������  
    static class Task extends Thread {  
        private static volatile int i = 1;  
  
        @Override  
        public void run() {// ִ������  
      //  	synchronized(Task.class)
        	{
        		 System.out.println("���� " + (i++) + " ���");  
        	}
        }  
    }  
}  
