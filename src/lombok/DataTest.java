package lombok;

public class DataTest {
	
	public static void main(String[] args) {
		
		Person person = new Person();
		person.desc = "qwe";
		System.out.println(person);
	}
	
	@Data
	static class Person {
		@Getter @Setter private String name;
		private String age;
		private String desc;
		
		public String toString() {
			return "name:" + name + ",age:" + age + ",desc:" + desc;
		}
		
	}

}
