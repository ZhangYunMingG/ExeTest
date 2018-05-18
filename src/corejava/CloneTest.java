package corejava;

public class CloneTest {
	public static void main(String[] args) {
		
		Student student = new Student("xiao ming", "24");
		Student nstudent = student.clone();
		System.out.println(nstudent);
		student = null;
		System.out.println(nstudent);
	}
	
	static class Student implements Cloneable
	{
		public Student(String name, String age) {
			this.name = name;
			this.age = age;
		}
		private String name;
		private String age;
		public String getName() {
			return name;
		}
		public String getAge() {
			return age;
		}
		public String toString() {
			return "name:" + name + "--age:" + age;
		}
		public Student clone() {
			try {
				return (Student)super.clone();
			} catch (CloneNotSupportedException e) {
				return null;
			}
		}
	}

}
