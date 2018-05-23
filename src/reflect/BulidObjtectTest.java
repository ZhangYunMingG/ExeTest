package reflect;

import java.lang.reflect.Constructor;

public class BulidObjtectTest {
	
	public static void main(String[] args) {
		
		BulidObjtectTest test = new BulidObjtectTest();
		test.getAnimal("Dog").talk();
		test.getAnimal("Cat").talk();
		String str = "a";
		String string = "E";
		System.out.println(str.toUpperCase());
		System.out.println(string.toLowerCase());
	}
	
	public Animal getAnimal(String str) {
		try {
			Class<?> in = Class.forName("reflect.BulidObjtectTest$" + str);
			Class<?> out = Class.forName("reflect.BulidObjtectTest");
			Constructor<?> con = in.getDeclaredConstructor(out);
			con.setAccessible(true);
			Object obj = con.newInstance(out.newInstance());
			return (Animal)obj;
		} catch (Exception e) {
			return null;
		}
	}
	
	interface Animal
	{
		public void talk();
	}
	
	@SuppressWarnings("unused")
	private class Dog implements Animal
	{
		@Override
		public void talk() {
			System.out.println("dog is talking!");
		}
	}
	
	@SuppressWarnings("unused")
	private class Cat implements Animal
	{
		@Override
		public void talk() {
			System.out.println("car is talking!");
		}
	}

}
