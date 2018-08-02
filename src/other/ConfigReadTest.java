package other;

import java.io.FileInputStream;
import java.util.Enumeration;
import java.util.Properties;

public class ConfigReadTest {

	public static void main(String[] args) throws Exception {
		
		String path = "G:\\Users\\zhangyunming\\eclipse-workspace\\ExeJava8\\src\\other\\config.properties";
		Properties pps = new Properties();
		pps.load(new FileInputStream(path));
		Enumeration<?> et = pps.propertyNames();
		while(et.hasMoreElements()) {
			String key = et.nextElement().toString();
			String value = pps.getProperty(key);
			System.out.println("key:" + key + ",value:" + value);
		}
	}
}
