package collection;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

/*
 * PriorityQueue优先级问题，重写Comparator中的compare方法，
 * 不按照入队的先后顺序出列，按照compare方法的优先级出列，
 * 当优先级相同时，按照第一个顺序不变，其他的按照倒序排列
 *  */
public class PriporityQueueTest {
	
	public static void main(String[] args) {
		
		Comparator<Person> com = new Comparator<Person>() {
			public int compare(Person p1, Person p2) {
				if(p1.getId() < p2.getId())
					return -1;
				else if(p1.getId() > p2.getId())
					return 1;
				return 0;
			}
		};
		
		PriorityQueue<String> queue = new PriorityQueue<String>();
		queue.offer("1");
		queue.offer("5");
		queue.offer("8");
		queue.offer("4");
		System.out.println(queue.size());
		StringBuffer str = new StringBuffer();
		while (!queue.isEmpty()) {
			str.append(queue.poll() + "--");
		}
		System.out.println(str.toString());
		
		PriorityQueue<Person> pQueue = new PriorityQueue<Person>(com);
		for(int i = 0; i < 5; i ++ ) {
			Person person = Person.builder();
			System.out.println(person);
			pQueue.offer(person);
		}
		System.out.println(pQueue.size());
		str = new StringBuffer();
		while (!pQueue.isEmpty()) {
			str.append(pQueue.poll());
			str.append("\n");
		}
		System.out.println(str.toString());
		
	}
	
	static class Person
	{
		private String name;
		private int id;
		private int age;
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String toString() {
			return "P:" + name + "-" + id + "-" + age;
		}
		
		static Random radnom = new Random();
		public static Person builder() {
			Person person = new Person();
			person.setName("name");
			person.setId(radnom.nextInt(20));
			person.setAge(20);
			return person;
		}
	}

	
	
}
