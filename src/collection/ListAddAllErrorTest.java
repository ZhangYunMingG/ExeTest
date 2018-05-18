package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListAddAllErrorTest {
	
	public static void main(String[] args) {
		
		String[] str1 = new String[] {"1", "2", "3"};
		String[] str2 = new String[] {"4", "5", "6"};
		List<String> list1 = Arrays.asList(str1);
		List<String> list2 = Arrays.asList(str2);
		// 报错unsupportedOperationException 原因是list1固定了长度,没有扩容的操作
		list1.addAll(list2);
		
		List<String> list3 = new ArrayList<String>(Arrays.asList(str1));
		List<String> list4 = new ArrayList<String>(Arrays.asList(str2));
		list3.addAll(list4);
		System.out.println(list3);
	}

}
