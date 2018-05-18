package jvm.garbagec;

public class FinalizeTest {

	    public static FinalizeTest garbage = null;  
	  
	    public static void main(String[] args) throws InterruptedException {  
	    	garbage = new FinalizeTest();  
	    	garbage = null;  
	        System.gc();  
	  //      Thread.sleep(500);  
	        if (null != garbage) { //此时对象应该处于(reachable, finalized)状态  
	            System.out.println("Yes , I am still alive");  
	        } else {  
	            System.out.println("No , I am dead");  
	        }  
	        garbage = null;  
	        System.gc();  
	        Thread.sleep(500);  
	        if (null != garbage) {  
	            System.out.println("Yes , I am still alive");  
	        } else {  
	            System.out.println("No , I am dead");  
	        }  
	    }  
	  
	    @Override  
	    protected void finalize() throws Throwable {  
	        super.finalize();  
	        System.out.println("execute method finalize()");  
	        garbage = this;  
	    }  
	}  