package compress;

import java.io.File;

public class Test {

	public static void main(String[] args) {
		
		CompressTest.compressZip(new File("E:\\study"), "E:/file/add/1032.zip");
		
	//	DeCompressTest.deCompressZip("E:/file/add/1032.zip", "E:/file/new");
	}
}
