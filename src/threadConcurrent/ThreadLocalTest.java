package threadConcurrent;

public class ThreadLocalTest {

	public static void main(String[] args) {
		
		ThreadLocal<String> threadLocal = new ThreadLocal<String>();
		threadLocal.set("");
		
	}
}
