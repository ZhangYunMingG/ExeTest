package collection;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class SetTest {
	
	public static void main(String[] args) {
		
		Field[] field = Student.class.getDeclaredFields();
		for (Field field2 : field) {
			System.out.println(field2);
		}
		
		Set<String> set = new HashSet<String>();
		set.add("str");
		set.contains("str");
		System.out.println(set.add("str"));
	//	HashMap<String, String> map = new HashMap<String, String>();
		ConcurrentHashMap<String, String> concurrent = new ConcurrentHashMap<String, String>();
		concurrent.put("111", "12");
		System.out.println(concurrent.contains("123"));
		Hashtable<String, String> hashtable = new Hashtable<String, String>();
		hashtable.put("111", "22");
		System.out.println(hashtable.contains("123"));
		;
		
	}
	
	static class Student
	{
		public String name;
		String id;
		String desc;
	}

}
