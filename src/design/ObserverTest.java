package design;

import java.util.ArrayList;
import java.util.List;

/*
 * 观察者
 * */
public class ObserverTest {
	
	public static void main(String[] args) {
		
		Subject subject = new ConcreateSubject();
		ConcreateObserver o1 = new ConcreateObserver("����");
		Observer o2 = new ConcreateObserver("����");
		Observer o3 = new ConcreateObserver("����");
		subject.add(o1);
		subject.add(o2);
		subject.add(o3);
		subject.notify("�����ˡ�����");
		
		
		
		
	}

}

interface Observer
{
	public void update(String message);
}

class ConcreateObserver implements Observer
{
	private String name;
	public ConcreateObserver(String name) {
		this.name = name;
	}
	
	@Override
	public void update(String message) {
		System.out.println(name + "_" + message);
	}
}

interface Subject
{
	public void add(Observer observer);
	public void remove(Observer observer);
	public void notify(String message);
	
}

class ConcreateSubject implements Subject
{
	private List<Observer> list = new ArrayList<Observer>();
	@Override
	public void add(Observer observer) {
		list.add(observer);
	}
	
	@Override
	public void remove(Observer observer) {
		list.remove(observer);
	}
	
	@Override
	public void notify(String message) {
		for (Observer observer : list) {
			observer.update(message);
		}
	}
}