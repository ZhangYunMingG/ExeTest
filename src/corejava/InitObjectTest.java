package corejava;

public class InitObjectTest {
	
	public static void main(String[] args) {
		
		new Son() {
			public void toName() {
				System.out.println("ha ha ha !!!");
			}
		};
		
	}
	
	static class Father
	{
		public static String sname = "sname";
		public String name = "name";
		public Father() {
			toName();
		}
		public void toName() {
			System.out.println("f:" + name);
		}
		
	}
	
	static class Son extends Father
	{
		public static String sname = "sson"; 
		public String name = "sonname";
		public Son() {
			toName();
		}
//		public void toName() {
//			System.out.println("s:" + name);
//		} 
	}

}
