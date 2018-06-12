package exception;

import java.util.ArrayList;
import java.util.List;

public class NoClassDefFoundErrorTest {
	
	public static void main(String[] args) {
		
		List<User> list = new ArrayList<User>();
		for(int i = 0; i< 2; i ++) {
			list.add(new User());
		}
		
	}
	
	static class User 
	{
		public static String id = getID();
		private static String getID() {
			throw new RuntimeException("class not found!");
		}
	}
}
