package other;

import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskTest {
	
	public static void main(String[] args) {
		Timer time = new Timer();
		//	表示只运行一次,1ms后开始运行
		time.schedule(new RunTask(), 1);
		//	表示1ms后开始运行,然后每间隔1000ms运行一次
		time.schedule(new RunTask(), 1, 1000);
		time.schedule(new RunT(), 1, 1000);
	}
	
	static class RunTask extends TimerTask
	{
		public void run() {
			System.out.println("start!");
		}
	}
	
	static class RunT extends TimerTask
	{
		public void run() {
			System.out.println("run is start!");
		}
	}

}
