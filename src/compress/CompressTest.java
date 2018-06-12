package compress;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.compress.archivers.zip.Zip64Mode;
import org.apache.commons.compress.archivers.zip.ZipArchiveEntry;
import org.apache.commons.compress.archivers.zip.ZipArchiveOutputStream;

/**
 * 文件压缩
 * !!!	流一定要记得关闭
 * 
 * @author zhangyunming
 *
 */
public class CompressTest {
	
	public static void compressZip(File file, String zipPath) {
		new CompressTest().compressFiles2Zip(file, zipPath);
	}
	
	private void compressFiles2Zip(File file, String zipPath) {
		
		ZipArchiveOutputStream zos = null;
		try {
			zos = new ZipArchiveOutputStream(new File(zipPath));
			zos.setUseZip64(Zip64Mode.AsNeeded);
			if(file.exists()) {
				fileLoop(file, zos, "");
			}
			zos.finish();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(zos != null) {
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 递归遍历文件夹及其中的文件
	 * @param file
	 * @param zos
	 * @param dirPath
	 */
	public void fileLoop(File file, ZipArchiveOutputStream zos, String dirPath) {
		if(file.isDirectory()) {
			File[] files = file.listFiles();
			int length = files.length;
			for(int i = 0; i < length; i ++) {
				fileLoop(files[i], zos, dirPath + "/" + file.getName());
			}
		} else {
			writeFile(file, zos, dirPath);
		}
	}
	
	/**
	 * 将文件写成压缩包
	 * @param file
	 * @param zos
	 * @param dirPath
	 */
	private void writeFile(File file, ZipArchiveOutputStream zos, String dirPath) {
		
		ZipArchiveEntry zipEntry = new ZipArchiveEntry(file, dirPath + "/" + file.getName());
		InputStream is = null;
		try {
			zos.putArchiveEntry(zipEntry);
			is = new FileInputStream(file);
			byte[] buffer = new byte[1024 * 4];
			int len = -1;
			while((len = is.read(buffer)) > 0) {
				// 把缓冲区的字节写入到ZipArchiveEntry
				zos.write(buffer, 0, len);
			}
			zos.closeArchiveEntry();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(is != null) {
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
