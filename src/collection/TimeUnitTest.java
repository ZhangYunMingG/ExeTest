package collection;

import java.util.concurrent.TimeUnit;

public class TimeUnitTest {
	
	public static void main(String[] args) {
		
		TimeUnit unit = TimeUnit.MILLISECONDS;
		System.out.println(unit.convert(10000, TimeUnit.MILLISECONDS));
		
	}

}
