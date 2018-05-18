package sorted;

import java.util.Arrays;
import java.util.Random;

/* 
 * javadoc @author merge  归并排序（小程序）
 */
public class MergeSortTest {

	public static void main(String[] args) {
		int[] a = new int[2000000];
		Random random = new Random();
		for(int i = 0; i < a.length; i ++)
		{
			a[i] = random.nextInt(99999);
		}
//		int[] two = f(a);
//		System.out.println(Arrays.toString(two));
//		
//		int[] three = f(two, 2);
//		System.out.println(Arrays.toString(three));
//		long t0 = System.currentTimeMillis();
//		int temp = 0;
//		for(int i = a.length - 1; i > 0; i --)
//		{
//			for(int j = 0; j < i; j++)
//			{
//				if(a[j] > a[j + 1])
//				{
//					temp = a[j];
//					a[j] = a[j + 1];
//					a[j + 1] = temp;
//				}
//			}
//		}
//		System.out.println(Arrays.toString(a));
		long t1 = System.currentTimeMillis();
		;
		System.out.println(Arrays.toString(transfer(a)));
		long t2 = System.currentTimeMillis();
		Arrays.sort(a);
		long t3 = System.currentTimeMillis();
//		System.out.println(Arrays.toString(a));
//		System.out.println(t1 - t0);
		System.out.println(t2 - t1);
		System.out.println(t3 - t2);
		
	}
	
	public static int[] transfer(int[] array)  {
		int length = array.length;
		int num = 1;
		while(length > 1)
		{
			array = f(array, num);
			num *= 2;
			length = (length + 1)/2;
		}
		return array;
	}
	
	private static int[] f(int[] array, int num)
	{
		int[] newArray = new int[array.length];
		int i = 0;
		while ((i + 2 * num) < array.length) {
			System.arraycopy(
					merge(Arrays.copyOfRange(array, i, i + num), Arrays.copyOfRange(array, i + num, i + 2 * num)), 0,
					newArray, i, 2 * num);
			i = i + 2 * num;
		}
		if(i + num < array.length)
			System.arraycopy(
					merge(Arrays.copyOfRange(array, i, i + num), Arrays.copyOfRange(array, i + num, array.length)), 0,
					newArray, i, array.length - i);
		else
			System.arraycopy(
					array, i,
					newArray, i, array.length - i);
		return newArray;
	}
	
	private static int[] f(int[] array) 
	{
		int length = array.length;
		int[] newArray = new int[length];
		if(length > 1)
		{
			int i = 0;
			while(i < length - 1)
			{
				if(array[i] < array[i+1])
				{
					newArray[i] = array[i];
					newArray[i + 1] = array[i + 1];
				} else {
					newArray[i] = array[i + 1];
					newArray[i + 1] = array[i];
				}
				i = i + 2;
			}
			if(i != length)
			newArray[length - 1] = array[length - 1];
		}
		return newArray;
	}

	private static int[] merge(int[] a, int[] b) {
		int alength = a.length;
		int blength = b.length;
		int aIndex = 0;
		int bIndex = 0;
		int k = 0;
		int[] num = new int[alength + blength];
		while (aIndex < alength && bIndex < blength) {
			if (a[aIndex] < b[bIndex]) {
				num[k++] = a[aIndex];
				aIndex++;
			} else {
				num[k++] = b[bIndex];
				bIndex++;
			}

		}
		if (aIndex == alength)
			System.arraycopy(b, bIndex, num, k, blength - bIndex);
		else
			System.arraycopy(a, aIndex, num, k, alength - aIndex);
		return num;
	}
}
