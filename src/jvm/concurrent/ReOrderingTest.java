package jvm.concurrent;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class ReOrderingTest {

	static int x = 0, y = 0, a = 0, b = 0, t = 1;
	
	public static void main(String[] args) throws InterruptedException {
		
		File file = new File("E:/file/reOrdering.txt");
		FileOutputStream fis = null;
		OutputStreamWriter isr = null;
		BufferedWriter br = null;
		List<String> list = new ArrayList<String>();
		
	//	ExecutorService exeService = Executors.newCachedThreadPool();
		Run run = new Run();
		R r = new R();
		for(int i = 0; i < 1000000; i ++) {
			Thread t1 = new Thread(run);
			Thread t2 = new Thread(r);
			t1.start();
			t2.start();
			t1.join();
			t2.join();
	//		exeService.execute(run);
	//		exeService.execute(r);
			list.add("("+ a + "," + b +")");
			a = b = x = y = 0;
		}
	//	exeService.shutdown();
		try {
			fis = new FileOutputStream(file);
			isr = new OutputStreamWriter(fis);
			br = new BufferedWriter(isr);
			br.write(list.toString());
			br.newLine();
			br.flush();
		} catch (Exception e) {
			
		}
	}
	
	static class Run implements Runnable
	{
		public void run() {
			x = 1;
			a = y;
		}
	}
	
	static class R implements Runnable
	{
		public void run() {
			y = 1;
			b = x;
		}
	}
}
