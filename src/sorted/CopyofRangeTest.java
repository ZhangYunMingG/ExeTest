package sorted;

import java.util.Arrays;
import java.util.Random;

public class CopyofRangeTest {
	public static void main(String[] args)
	{
		
		int[] a = new int[16];
		Random random = new Random();
		for(int i = 0; i < a.length; i ++)
		{
			a[i] = random.nextInt(30);
		}
		System.out.println(Arrays.toString(Arrays.copyOfRange(a, 0, 3)));
	}

}
