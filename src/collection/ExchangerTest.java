package collection;

import java.util.concurrent.Exchanger;

// 交换两个线程中的两个数值
public class ExchangerTest {
	
	public static void main(String[] args) {
		Exchanger<Traffic> exchanger = new Exchanger<Traffic>();
		Traffic carTraffic = new Car();
		Traffic bikeTraffic =new Bike();
		Thread car = new Thread(new Car(exchanger, carTraffic));
		Thread bike = new Thread(new Bike(exchanger, bikeTraffic));
		car.start();
		bike.start();
	}
	
	static class Car implements Runnable,Traffic
	{
		private String name;
		private Exchanger<Traffic> exchanger;
		private Traffic traffic;
		public Car(Exchanger<Traffic> exchanger, Traffic traffic) {
			this.exchanger = exchanger;
			name = "car";
			this.traffic = traffic;
		}
		public Car() {
			name = "car";
		}
		public void run() {
			try {
				System.out.println(name + Thread.currentThread().getName() + exchanger.exchange(traffic));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public String getName() {
			return name;
		}
		@Override
		public void fun() {
			System.out.println("car");
		}
		public String toString() {
			return "name:" + name ;
		}
	}
	
	static class Bike implements Runnable,Traffic
	{
		private String name;
		private Traffic traffic;
		private Exchanger<Traffic> exchanger;
		public Bike(Exchanger<Traffic> exchanger, Traffic traffic) {
			this.exchanger = exchanger;
			name = "bike";
			this.traffic = traffic; 
		}
		public Bike() {
			name = "bike";
		}
		public void run() {
			try {
				System.out.println(name + Thread.currentThread().getName() + exchanger.exchange(traffic));
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		@Override
		public String getName() {
			return name;
		}
		@Override
		public void fun() {
			System.out.println("bike");
		}
		public String toString() {
			return "name:" + name ;
		}
	}
	
	static interface Traffic
	{
		public String getName();
		public void fun();
	}

}
