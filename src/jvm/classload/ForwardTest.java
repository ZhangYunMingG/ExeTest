package jvm.classload;

public class ForwardTest {

	static int i = 0;
	static {
		i = 12;
		System.out.println(i);
	}
}
