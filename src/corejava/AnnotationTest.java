package corejava;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

// @TestAnnotation是自定义注解不可缺少


@TestAnnotation("test")
public class AnnotationTest {
	
	//  元注解@Retention
	//  @Retention(RetentionPolicy.RUNTIME)
	//  Retention注解括号中的"RetentionPolicy.RUNTIME"意思是让MyAnnotation这个注解的生命周期一直程序运行时都存在
	//	@Retention(RetentionPolicy.SOURCE)
	//  这个注解的意思是让MyAnnotation注解只在java源文件中存在，编译成.class文件后注解就不存在了
	//  @Retention(RetentionPolicy.CLASS)
	//  这个注解的意思是让MyAnnotation注解在java源文件(.java文件)中存在，编译成.class文件后注解也还存在
	public static void main(String[] args) {
		
		TestAnnotation test = AnnotationTest.class.getAnnotation(TestAnnotation.class);
		System.out.println("test value =" + test.value() + ",test defaultValue = "+ test.defaultValue());
		
		// 关于元注解inherited的测试
		InheritedTest s1 =  Son.class.getAnnotation(InheritedTest.class);
		System.out.println(s1.value()); 
		
		unInheritedTest s2 =  Son.class.getAnnotation(unInheritedTest.class);
		System.out.println(s2.value()); // 报  null pointer Exception
		
		// 当子类自定义输入参数时,使用自己的参数
		// 当子类没有定义时,看父类的注解是否有@Inherited修饰，修饰则子类拥有父类相应的参数
		
	}
}

@Retention(RetentionPolicy.RUNTIME)
@interface TestAnnotation 
{
	public String value();
	public String defaultValue() default "default";
}

@InheritedTest("InheritedTest")
@unInheritedTest("unInheritedTest")
class Father
{
	
}

//@InheritedTest("InheritedTestSon")
//@unInheritedTest("unInheritedTestSon")
class Son extends Father
{
	
}

@Inherited
@Retention(RetentionPolicy.RUNTIME)
@interface InheritedTest
{
	String value();
}

@Retention(RetentionPolicy.RUNTIME)
@interface unInheritedTest
{
	String value();
}

