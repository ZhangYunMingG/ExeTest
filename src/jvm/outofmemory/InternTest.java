package jvm.outofmemory;

public class InternTest {
	
	public static void main(String[] args) {
		
		String s = new String("java");  
		s.intern();
		String s2 = "java";  
		System.out.println(s == s2);  
		  
		String s3 = new String("1") + new String("1");  
		s3.intern();  
		String s4 = "11";  
		System.out.println(s3 == s4);
		
	//	String str2 = "SEUCalvin";//新加的一行代码，其余不变  
		String str1 = new String("SEU")+ new String("Calvin");      
		System.out.println(str1.intern() == str1);   
		System.out.println(str1 == "SEUCalvin"); 
		
		// false  false
//		String s1 = new StringBuffer("ja").append("va").toString();
//		System.out.println(s1 == s1.intern());
//		
//		String s2 = new StringBuffer("程序员").append("开发").toString();
//		System.out.println(s2 == s2.intern());
//		
//		String s3 = new StringBuffer("ja").append("va").toString();
//		System.out.println(s3 == s3.intern());
//		
//		String s4 = new StringBuffer("程序员").append("开发").toString();
//		System.out.println(s4 == s4.intern());
		// false  true false  false
	}

}
