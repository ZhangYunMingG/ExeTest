package sorted;

import java.util.Arrays;
import java.util.Random;

//	快排
public class QuickSortedTest {
	
	private static final int length = 10;
	public static void main(String[] args) {
		
		Random random = new Random();
		int[] array = new int[length];
		for(int i = 0; i < length; i ++) {
			array[i] = random.nextInt(100);
		}
		System.out.println(Arrays.toString(array));;
		finalSort(array, 0, 9);
		System.out.println(Arrays.toString(array));;
	}
	
	private static int sort(int[] array, int begin, int end) {
		int i = begin;
		int j = end;
		int num = array[begin];
		while(i < j) {
			while(num <= array[j] && i < j)
				j--;
			while(num >= array[i] && i < j)
				i++;
			if(i < j) {
				transfer(array, i, j);
			}
		}
		if(array[i] < num) {
			transfer(array, i, begin);
		} else if(array[i] == num) {
			
		} else {
			i--;
			transfer(array, i, begin);
		}
		return i;
	}
	
	private static void transfer(int[] array, int x, int y) {
		int tmp = array[x];
		array[x] = array[y];
		array[y] = tmp;
	}
	
	public static void finalSort(int[] array, int begin, int end) {
		int index = sort(array, begin, end);
		if(begin < index - 1)
		finalSort(array, begin, index - 1);
		if(end > index + 1)
		finalSort(array, index + 1, end);
	}

}
