

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import java.io.File;
import java.io.FileFilter;
 
 class MyFileFilter implements FileFilter {
 
    public boolean accept(File file) {
        String fileName = file.getName().toLowerCase();
        if (fileName.endsWith(".jar") || file.isDirectory()) {
            return true;
        }
        return false;
    }
}

public class CopyJar {

	public static void main(String[] args) throws Exception {
		JarCollectionUtil collectionUtil = new JarCollectionUtil();
//		collectionUtil.collectionJar(new File("Z:\\openconnect\\repo"), "c:\\jar\\openconnect");
		collectionUtil.collectionJar(new File("Z:\\openconnect\\jboss-as-7.1.3.Final"), "c:\\jar\\jboss713");
		
	}
	
}


/**
 * 
 * @author pat 将文件中的jar文件提取出来
 */
 class JarCollectionUtil {

	public String collectionJar(File baseFile, String targetFilePath) throws Exception {
		File targetDir = new File(targetFilePath);
		if (!targetDir.exists()) {
			targetDir.mkdir();
		}

		if (baseFile.exists() && baseFile.isDirectory()) {
			File[] files = baseFile.listFiles(new MyFileFilter());
			for (File file : files) {
				if (file.isDirectory()) {
					collectionJar(file, targetFilePath);
				} else {
					File targetFile = new File(targetFilePath + "\\" + file.getName());
					if(!targetFile.exists()) {
						FileUtils.copyFile(file, targetFile);
						System.out.println("copy:" + file +" to:" + targetFile);
					}
					else {
						System.out.println("escape:" + targetFile);
					}
				}
			}
			return "success";
		} else {
			return "文件路径不存在";
		}

	}

}

