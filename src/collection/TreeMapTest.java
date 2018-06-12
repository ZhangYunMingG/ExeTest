package collection;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class TreeMapTest {
	
	public static void main(String[] args) {
		
		TreeMap<String, String> map = new TreeMap<String, String>(new Compare());
		map.put("a12", "34");
		map.put("b34", "34");
		map.put("c45", "34");
		for (Entry<String, String> string : map.entrySet()) {
			System.out.println(string);
		}
		
		System.out.println("-------------");
		Map<String, String> m = new LinkedHashMap<String, String>();
		m.put("m1", "mq");
		m.put("m2", "mq");
		m.put("m3", "mq");
		m.put("m3", "mq");
		m.put("m1", "mq");
		m.put("m2", "mq");
		for (Entry<String, String> string : m.entrySet()) {
			System.out.println(string);
		}
	}
	
	static class Compare implements Comparator<String>
	{
		@Override
		public int compare(String o1, String o2) {
			return o1.compareTo(o2);
		}
		
	}

}
