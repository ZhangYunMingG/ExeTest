package integer;

public class NotAddTest {
	
	public static void main(String[] args) {
		
		int a = 100;
		int b = -100;
		System.out.println(plus(a, b));
	}
	
	
	private static int plus(int a, int b) {
		int tmp;
		while(a != 0) {
			tmp = a^b;
			a = (a&b)<<1;
			b = tmp;
		}
		return b;
	}
}
