package com.xy.util;

import java.io.File;


public class URLUtils {
	public static String getClassPath(){
		String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		File file = new File(basePath);
		return file.getPath();
	}
	
	public static void main(String[] args) {
		String str = getClassPath();
		//File file = new File(str);
		System.out.println(str);
	}
}
