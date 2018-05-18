package collection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.HashMap;

//	hashmap扩容,不是内部的有效数组达到75%,进行扩容的,而是数据量达到75%扩容的
//	所以说hashmap的扩容不是真正的扩容,内部可能有大量的数组都是空的(某种意义上说,扩容是毫无意义的)
public class MapNumTest {
	
	public static void main(String[] args) throws Exception {
		
		HashMap<String, String> map = new HashMap<String, String>(16);
		for(int i = 0; i < 12; i ++) {
			StringBuffer sbuffer = new StringBuffer();
			for(int j = 0; j < i; j++) {
				sbuffer.append("1");
			}
			map.put(sbuffer.toString(), null);
		}
		Field field = map.getClass().getDeclaredField("table");
		field.setAccessible(true);
		Object[] node = (Object[])(field.get(map));
		System.out.println(Arrays.toString(node));
		System.out.println(node.length/4*3);
		
		for(int i = 0; i < 10; i ++) {
	//		System.out.println((i + "2").hashCode());
		}
		
	}

}
