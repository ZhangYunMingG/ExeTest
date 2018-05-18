package foreach;

import java.util.Arrays;

public class ArrayQuoteTest {
	
	// foreach传的是值，而不是引用，所以无法修改数组中的值
	public static void main(String[] args)
	{
		ArrayQuote[] old = new ArrayQuote[10];
		for(int i = 0; i < 10; i ++)
		{
			old[i] = new ArrayQuote();
		}
		System.out.println(Arrays.toString(old));
		for (ArrayQuote i : old) {
			System.out.println(i);
			i = new ArrayQuote("24");
		}
		System.out.println(Arrays.toString(old));
		
		
	}
	
	static class ArrayQuote
	{
		String id = "18";
		public ArrayQuote()
		{
			
		}
		public ArrayQuote(String string)
		{
			this.id = string;
		}
		
		public String toString()
		{
			return id;
		}
		
	}

}
