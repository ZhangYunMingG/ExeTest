package jvm.classload;

public class InitTest {
	public static void main(String[] args) {
		
		System.out.println(SubClass.value);
		//	SuperClass init!	12
	}
	
	static class SubClass extends SuperClass
	{	
		static {
			System.out.println("subClass init!");
		}
	}

	static class SuperClass 
	{
		public static int value = 12;
		static 
		{
			System.out.println("SuperClass init!");
		}
	}
}

