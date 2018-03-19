package com.wanma.web.support.utils;

import java.io.File;

public class PathUtil {
	
	/*
	 * 项目路径
	 */
	public static String getRootPath() {
		  String classPath = PathUtil.class.getClassLoader().getResource("/").getPath();
		  String rootPath  = "";
		  //windows下
		  if("\\".equals(File.separator)){   
		   rootPath  = classPath.substring(1,classPath.indexOf("/WEB-INF/classes"));
		   rootPath = rootPath.replace("/", "\\");
		  }
		  //linux下
		  if("/".equals(File.separator)){   
		   rootPath  = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
		   rootPath = rootPath.replace("\\", "/");
		  }
		  return rootPath;
		 }
}
