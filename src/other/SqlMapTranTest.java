package other;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class SqlMapTranTest {
	
	public static void main(String[] args) {
		
		File file = new File("E:/file/Test/sqlmap.txt");
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		FileOutputStream fos = null;
		OutputStreamWriter osw = null;
		BufferedWriter bw = null;
		
		try {
			
			fis = new FileInputStream(file);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			file = new File("E:/file/Test/sqlmapOut.txt");
			fos = new FileOutputStream(file);
			osw = new OutputStreamWriter(fos);
			bw = new BufferedWriter(osw);
			
			StringBuffer strBuffer = new StringBuffer();
			String str;
			while((str = br.readLine()) != null) {
				strBuffer.append(str);
				strBuffer.append(";");
			}
			
			List<String> list = new ArrayList<String>();
			String[] array = strBuffer.toString().split(";");
			for (String string : array) {
				if(string.startsWith("private")) {
					list.add(string.split(" ")[2]);
				}
			}
			
			for (String string : list) {
				bw.write("<result column=\""+ string +"\" property=\""+ string +"\"/>");
				bw.newLine();
			}
			bw.flush();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(bw != null) {
				System.out.println("bw start close !");
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(br != null) {
				System.out.println("br start close !");
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
