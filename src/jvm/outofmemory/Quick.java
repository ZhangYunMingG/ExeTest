package jvm.outofmemory;

import java.util.ArrayList;
import java.util.List;

public class Quick {
	
	static List<String> list = new ArrayList<String>();
	public static void main(String[] args) {
		
		for(int i = 0; i < 11726187; i ++) {
			System.out.println(i);
			list.add(String.valueOf(i ++).intern());
		}
		System.out.println("---");
		System.out.println("size:" + list.size());
		System.out.println("---");
		for(int i = 0; i < 11726187; i ++) {
			System.out.println(list.get(i));
		}
		System.out.println("end!");
		
	}
//	static class Run implements Runnable {
//		public void run() {
//			int i = 10000000;
//			while(true) {
//				list.add(String.valueOf(i ++).intern());
//			}
//		}
//	}
}
