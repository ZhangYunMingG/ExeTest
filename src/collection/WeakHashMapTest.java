package collection;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;

public class WeakHashMapTest {
	
	public static void main(String[] args) {
		
		Map<String, List<String>> map = new WeakHashMap<String, List<String>>();
		String str = new String("key");
		List<String> list = new ArrayList<String>();
		list.add("123");
		list.add("qqq");
		list.add("asd");
		map.put(str, list);
		System.out.println(map);
		list = null;
		System.out.println(map);
		str = null;
		System.gc();
		System.out.println(map);
		
	}

}
