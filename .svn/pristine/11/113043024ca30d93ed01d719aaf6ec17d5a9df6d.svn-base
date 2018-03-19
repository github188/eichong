package com.wanma.common;
import it.sauronsoftware.ftp4j.FTPClient;
import it.sauronsoftware.ftp4j.FTPException;
import it.sauronsoftware.ftp4j.FTPFile;
import it.sauronsoftware.ftp4j.FTPIllegalReplyException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.log4j.Logger;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.utils.MultipartFileUtil;


/**
 * FTP上传图片
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime 2015-6-18 下午05:35:12
  * @updator： 
  * @updateTime：   
  * @version：V1.0
  * 其他可选例子：
 */
public class MoveFileUtil {
	// 日志输出对象
	private static Logger log = Logger.getLogger(MultipartFileUtil.class);
    
    /**wxa
     * 实现了FTP上文件的copy
     * @param f
     * @param cp
     * @param day
     * @param creator
     * @param filename  Target
     * @return
     * @throws Exception
     */
    public static String fileCopybyFtp (InputStream input,String targetFile) throws Exception {
    	
    	String mzurl="";//测试专用
    	// mzurl="ftp://web:aichong@119.254.210.85:21/Bruce/";//测试专用
    	//Date startDate = new Date();
    	MessageManager messageManager = MessageManager.getMessageManager();
    	mzurl=messageManager.getSystemProperties(CommonConsts.PICTURE_SERVICE_UPLOADURL);
    	
		URL f2 = new URL (mzurl + targetFile);
		//解决jdk1.7不能连接FTP问题
		System.setProperty("java.net.preferIPv4Stack", "true");
		URLConnection con = f2.openConnection();
		con.connect();
		FTPClient client = makeClient (f2);
		makeDir(client, "/"+getSubStr(mzurl,2)+targetFile.substring(0, targetFile.lastIndexOf('/')), client.currentDirectory() +"/");
		client.disconnect(true);
		//FileInputStream input = new FileInputStream (sourceFile);
		OutputStream output =  con.getOutputStream();
		int size = 0;
		for (byte[] buffer = new byte[1024]; (size = input.read(buffer)) > 0; ) {
			output.write(buffer, 0, size);
		}
		input.close();
		output.close();
		//Date baseDate = new Date();
		return targetFile;
	}
    
	public static void makeDir(FTPClient client, String targetPath, String root) throws Exception {
		String [] paths = targetPath.split("/");
		String curPath = root;
		for (int i =0; i < paths.length; i++) {
			String p = paths[i];
			if (p.equals("")) continue;
			client.changeDirectory(curPath);
			curPath = curPath + p + "/";
			if (!dirExists(client, p)) {
				client.createDirectory(p);
			}
		}
	}
	private static String getSubStr(String str, int num) {
		  String result = "";
		  int i = 0;
		  while(i < num) {
		   int lastFirst = str.lastIndexOf('/');
		   result = str.substring(lastFirst) + result;
		   str = str.substring(0, lastFirst);
		   i++;
		  }
		  return result.substring(1);
		 } 
		 
	public static boolean dirExists(FTPClient client, String p) throws Exception{
		FTPFile f[] = client.list();
		for (int i =0; i < f.length; i++) {
			
			if (f[i].getName().equals(p)) {
				if (f[i].getType() == FTPFile.TYPE_DIRECTORY) return true;
				throw new RuntimeException("已有与目录同样的文件建立");
			}
					
		}
		return false;
	}
    
	private static FTPClient makeClient (URL f2) throws IllegalStateException, IOException, FTPIllegalReplyException, FTPException {
		FTPClient client = new FTPClient ();
		client.connect(f2.getHost(),f2.getPort());
		String[] v = f2.getUserInfo().split(":");
		client.login(v[0], v[1]);
		return client;
	}
    

	 /** 
     * 删除文件或目录 
     *  
     * @param dir 
     *            文件或目录数组 
     * @throws Exception 
     */  
    public static void delete(String file)  {  
        FTPClient client = null;  
        try {  
        	String mzurl="";//测试专用
          	MessageManager messageManager = MessageManager.getMessageManager();
        	mzurl=messageManager.getSystemProperties(CommonConsts.PICTURE_SERVICE_UPLOADURL);
          	// mzurl="ftp://web:aichong@119.254.210.85:21/Bruce/";//测试专用
          	
        	URL f2 = new URL (mzurl + file);
            client = makeClient(f2);  
            client.deleteFile("/"+getSubStr(mzurl,2)+file);  
        } catch(Exception e){
        	log.error(e.getLocalizedMessage());
        } 
    }  
	public static void main (String args[]) throws Exception {
		System.out.println("运行开始....");
		FileInputStream input = new FileInputStream ("D:\\1.png");
    	System.out.println(fileCopybyFtp(input,"bussinessType/453031506191432160.png"));
		 //delete("bussinessType/453031506191432160.png");
		System.out.println("运行结束....");
	}
	
}
