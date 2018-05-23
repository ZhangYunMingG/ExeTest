package corejava;

public class FinallyTest {

	//  及时try中报异常, finally的方法也会走的
	public static void main(String[] args) {
		
		try {
			int a = 19/0;
			System.out.println(a);
//		} catch (Exception e) {
//			System.out.println("catch a Exception");
		} finally {
			System.out.println("finally is start !");
		}
		
	}
}
