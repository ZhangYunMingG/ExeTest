package design;

//  备忘录模式(快照模式),
public class MemoTest {

	public static void main(String[] args) {
		
		Originator origin = new Originator();
		CareTaker careTaker = new CareTaker();
		origin.setState("On");
		careTaker.saveMemento(origin.createMemento());
		origin.setState("Off");
		origin.restoreMemento(careTaker.retrieveMemento());
		System.out.println(origin.getState());
		
	}
	
	static class Originator
	{
		private String state;
		
		//  工厂方法，返回一个新的备忘录对象
		public Memento createMemento() {
			return new Memento(state);
		}
		
		//  将发起人恢复到备忘录对象所记载的状态
		public void restoreMemento(Memento memento) {
			this.state = memento.getState();
		}
		
		public void setState(String state) {
			this.state = state;
		}
		public String getState() {
			return state;
		}
	}
	
	//  负责人
	static class CareTaker
	{
		private Memento memento;
		
		public Memento retrieveMemento() {
			return memento;
		}
		
		public void saveMemento(Memento memento) {
			this.memento = memento;
		}
	}
	
	//  备忘录角色类
	static class Memento
	{
		private String state;	
		public Memento() {
			
		}
		public Memento(String state) {
			this.state = state;
		}
		public String getState() {
			return state;
		}
		public void setState(String state) {
			this.state = state;
		}
	}
}
