package other;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class MessCodeTest {
	
	public static void main(String[] args) {
		
		// http://blog.csdn.net/u010234516/article/details/52853214
		try {
			String srcString = "我们是中国人";  
	        String utf2GbkString = new String(srcString.getBytes("UTF-8"),"GBK");  
	        System.out.println("UTF-8转换成GBK："+utf2GbkString);  
	        String utf2Gbk2UtfString = new String(utf2GbkString.getBytes("GBK"),"UTF-8");  
	        System.out.println("UTF-8转换成GBK再转成UTF-8："+utf2Gbk2UtfString);  
		} catch (Exception e) {
			
		}
		
		String str = " ͨ�������ഴ������";
		try {
			byte[] byteStr = str.getBytes("UTF-8");
			System.out.println(byteStr.length);
		//	byte[] tran = new byte[3];
			System.out.println(new String(Arrays.copyOfRange(byteStr, 0, 3), "GBK"));;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		
	}

}
