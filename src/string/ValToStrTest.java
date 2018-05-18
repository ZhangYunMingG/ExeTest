package string;

// 测试+"",toString性能
// valueOf方法就是比toString多一个非空判断
public class ValToStrTest {
	private static final int loop = 10000000;
	public static void main(String[] args) {
		
		String tmp = null;
		Integer teg = 0;
		// toString
		long start = System.currentTimeMillis();
		for(int i = 0; i < loop; i ++) {
			tmp = teg.toString();
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
		
		// +""
		start = System.currentTimeMillis();
		for(int i = 0; i < loop; i ++) {
			tmp = teg + "";
		}
		end = System.currentTimeMillis();
		System.out.println(end - start);
		System.out.println(tmp);
		
	}
}
