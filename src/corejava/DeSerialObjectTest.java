package corejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

//
public class DeSerialObjectTest {

	public static void main(String[] args) {
		
		Person person = new Person();
		person.setName("test");
		person.setAge("12");
		person.setSex("boy");
		
		File file = new File("E:/file/person.txt");
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(file);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(person);
			oos.flush();
			
			ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file));
	        Person newInstance = (Person) ois.readObject();
	        System.out.println(newInstance);
		
		} catch (Exception e) {
			
		} finally {
			try {
				if(oos != null)
					oos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	        
	}
	
	static class Person implements Serializable
	{
		private static final long serialVersionUID = 1l;
		private String name;
		private String sex;
		private String age;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			this.sex = sex;
		}
		public String getAge() {
			return age;
		}
		public void setAge(String age) {
			this.age = age;
		}
		public String toString() {
			return "name:" + name + ",sex:" + sex + ",age:" + age;
		}
	}
}

