package compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.commons.compress.archivers.ArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveInputStream;

/**
 * 
 * @author zhangyunming
 *
 */
public class DeCompressTest {
	
	public static void deCompressZip(String zipPath, String savePath) {
		new DeCompressTest().deCompressFiles2Zip(zipPath, savePath);
	}
	/**
	 * 解压文件
	 * @param zipPath
	 * @param savePath
	 */
	public void deCompressFiles2Zip(String zipPath, String savePath) {
		
		File zipFile = new File(zipPath);
		if(!zipFile.exists()) {
			System.out.println("zopPath is not exist!");
			return;
		}
		File saveFile = new File(savePath);
		if(!saveFile.exists()) {
			saveFile.mkdirs();
		}
		
		ZipArchiveInputStream zis = null;
		try {
			zis = new ZipArchiveInputStream(new FileInputStream(zipPath));
			ArchiveEntry entry = null;
			while((entry = zis.getNextZipEntry()) != null) {
				
				String name = entry.getName();
				String path = savePath + name;
				byte[] context = new byte[(int)entry.getSize()];
				zis.read(context);
				OutputStream os = null;
				try {
					File file = new File(path);
					if(!file.getParentFile().exists())
						file.getParentFile().mkdirs();
					os = new FileOutputStream(file);
					os.write(context);
				} finally {
					if(os != null) {
						os.flush();
						os.close();
					}
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(zis != null) {
				try {
					zis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
