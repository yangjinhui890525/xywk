package com.xy.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class ZipUtils {
	public static boolean Unzip(String sZipPathFile, String sDestPath) {
		boolean flag = false;
		try {
			FileInputStream fins = new FileInputStream(sZipPathFile);
			ZipInputStream zins = new ZipInputStream(fins);
			ZipEntry ze = null;
			byte ch[] = new byte[256];
			while ((ze = zins.getNextEntry()) != null) {
				File zfile = new File(sDestPath + File.separator + ze.getName());
				File fpath = new File(zfile.getParentFile().getPath());
				if (ze.isDirectory()) {
					if (!zfile.exists())
						zfile.mkdirs();
					zins.closeEntry();
				} else {
					if (!fpath.exists())
						fpath.mkdirs();
					FileOutputStream fouts = new FileOutputStream(zfile);
					int i;
					//String zfilePath = zfile.getAbsolutePath();
					while ((i = zins.read(ch)) != -1)
						fouts.write(ch, 0, i);
					zins.closeEntry();
					fouts.close();

					//һ���ǵݹ��ѹ���е�zip
					/*if (zfilePath.endsWith(".zip")) {
						Unzip(zfilePath, zfilePath.substring(0, zfilePath
								.lastIndexOf(".zip")));
					}*/

				}
			}
			fins.close();
			zins.close();

			// �����Ҫ��ѹ���ɾ��ZIP�ļ�����
			File file = new File(sZipPathFile);
			file.delete();
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
			System.err.println("Extract error:" + e.getMessage());
		}
		 return flag;  
	}

	public static void main(String[] args) {
		String sZipPathFile = "D:\\acenter\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\acenter\\downloads\\oem_iactive.zip";
		String sDestPath = "D:\\acenter\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\acenter\\downloads\\";
		ZipUtils.Unzip(sZipPathFile, sDestPath);
	}
}
