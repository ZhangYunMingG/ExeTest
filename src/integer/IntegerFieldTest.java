package integer;

import java.lang.reflect.Field;

public class IntegerFieldTest {
	
	public static void main(String[] args) {
		
		try {
//			Field[] field = Integer.class.getDeclaredFields();
//			for(int i = 0; i < field.length; i ++) {
//				System.out.println(field[i]);
//			}
			System.out.println();
			Field[] cField = Integer.class.getDeclaredClasses()[0].getDeclaredFields();
			for(int i = 0; i < cField.length; i ++) {
				System.out.println(cField[i]);
			}
			
		} catch (Exception e) {
			
		}
	}
}
