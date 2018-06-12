package fileRead;

import java.io.File;

public class ListFilesTest {
	public static void main(String[] args) {
		
		File file = new File("E:/file/tranisent");
		file.mkdir();
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			System.out.println(files.length);
		}
		
		file.delete();
	}
}
