package collection;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

//  重写getDelay方法,为了设置延时队列的过期时间
//  重写compareTo方法,等同于修改优先队列的获取出来的顺序
public class DelayQueueTest {
	
	public static void main(String[] args) {
		
		DelayQueue<Delay> queue = new DelayQueue<Delay>();
		ExecutorService executorService = Executors.newCachedThreadPool();
		Producer producer = new Producer(queue);
		executorService.execute(producer);
		executorService.shutdown();
		try {
			Thread.sleep(200);
			while(!queue.isEmpty()) 
			{
				System.out.println(queue.take());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	static class Producer implements Runnable
	{
		private Queue<Delay> queue;
		public Producer(Queue<Delay> queue) {
			this.queue  = queue;
		}
		public void run() {
			for(int i = 0; i < 5; i ++) {
				System.out.println("queue" + i + " is start!!!");
				try {
					Thread.sleep(100);
				} catch (Exception e) {
					e.printStackTrace();
				}
				queue.offer(new Delay(String.valueOf(i), String.valueOf(i)));
			//	System.out.println("queue" + i + " is end!!!");
			}
		}
	}
	
	static class Delay implements Delayed
	{ 
		Random random = new Random();
		private String name;
		private String age;
		private String id;
		
		private long expire;
		public Delay(String id, String age) {
			this(3000);
			name = "N" + id;
			this.id = id;
			this.age = age;
	//		this.id = String.valueOf(random.nextInt(50));
		}
		public Delay() {
			this(3000);
		}
		public Delay(long time) {
			expire = System.currentTimeMillis() + time;
		}
		
		@Override
		public int compareTo(Delayed delayed) {
			Delay delay = (Delay)delayed;
			if(id.compareTo(delay.getId()) > 0)	
				return -1;
			return 1;
//			return (int) (this.getDelay(TimeUnit.MILLISECONDS) -delayed.getDelay(TimeUnit.MILLISECONDS));
		}

		@Override
		public long getDelay(TimeUnit unit) {
			return unit.convert(expire - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String toString() {
			return "name:" + name +"-id:" + id + "-age:" + age;
		}
	}

}
