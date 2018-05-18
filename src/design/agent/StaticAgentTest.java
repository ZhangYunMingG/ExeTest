package design.agent;

//  静态代理的测试
public class StaticAgentTest {

	public static void main(String[] args) {
		
		Admin admin = new Admin();
		AdminProxy proxy = new AdminProxy(admin);
		proxy.doSomething();
		
		try {
			//  测试getInterfaces的方法，返回值是接口的数组
			Class<?>[] classArray = admin.getClass().getInterfaces();
			for(int i = 0; i < classArray.length; i++) {
				System.out.println(classArray[i]);
			}
		} catch (Exception e) {
			
		}
	}
	
	static interface Manger
	{
		public void doSomething();
	}
	
	static class Admin implements Manger
	{
		@Override
		public void doSomething() {
			System.out.println("admin is start! ");
		}
	}
	
	static class AdminProxy implements Manger
	{
		private Manger manger;
		public AdminProxy(Manger manger) {
			this.manger = manger;
		}
		@Override
		public void doSomething() {
			System.out.println("adminProxy before ! ");
			manger.doSomething();
			System.out.println("adminProxy after ! ");
		}
	}
}

