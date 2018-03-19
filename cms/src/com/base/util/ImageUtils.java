/*
 *Copyright (c) 2014 crc All Rights Reserved. 
 */   
package com.base.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/***
 *   图片压缩
  * @author bruce cheng(http://blog.csdn.net/brucehome)
  * @createTime 2015-6-9 下午06:19:01
  * @updator： 
  * @updateTime：   
  * @version：V1.0
  * 其他可选例子：
 */
public class ImageUtils {
	
	public static float qa = 0.85f;
	public static float soften_Factor = 0.00f; 
	
	/***
	 * @param sourceFilePath 源图片路径
	 * @param targetFilePath 压缩后图片路径
	 * @param newWidth       压缩后图片宽度
	 * @param newHeight      压缩后图片高度
	 * @throws IOException
	 */
	public static void compress(File sourceFilePath, String targetFilePath,  
            int newWidth,int newHeight) throws IOException {  
		compress(sourceFilePath, targetFilePath, newWidth, newHeight, qa, soften_Factor);
	}
	
	/***
	 * @param sourceFilePath 源图片路径
	 * @param targetFilePath 压缩后图片路径
	 * @param newWidth       压缩后图片宽度
	 * @param newHeight      压缩后图片高度
	 * @param quality        压缩质量(0-1)
	 * @param softenFactor   柔化因子(-1 -- 1),为负数时为锐化处理,正数柔化处理,0则不做任何处理
	 * @throws IOException
	 */
	public static void compress(File sourceFile, String targetFilePath,  
            int newWidth,int newHeight, float quality,float softenFactor) throws IOException {  
		//File sourceFile = new File(sourceFilePath);
		File targetFile = new File(targetFilePath);
		
		targetFile.getParentFile().mkdirs();
  
		int w = newWidth ;
		BufferedImage src = ImageIO.read(sourceFile);
		if(src.getWidth() < src.getHeight()){
			w = newHeight ;
		}
		
        if (quality > 1) {  
            throw new IllegalArgumentException("压缩质量的值必须(0-1)之间");  
        }  
        
        if(softenFactor > 1 || softenFactor < -1){
        	throw new IllegalArgumentException("柔化因子必须在(-1 -- 1)之间");  
        }
  
        ImageIcon ii = new ImageIcon(sourceFile.getCanonicalPath());  
        Image i = ii.getImage();  
        Image resizedImage = null;  
  
        int iWidth = i.getWidth(null);  
        int iHeight = i.getHeight(null);  
  
        if (iWidth >= iHeight) {  
            resizedImage = i.getScaledInstance(w, (w * iHeight)  
                    / iWidth, Image.SCALE_SMOOTH);  
        } else {  
            resizedImage = i.getScaledInstance((w * iWidth) / iHeight,  
            		w, Image.SCALE_SMOOTH);  
        }  

        Image temp = new ImageIcon(resizedImage).getImage();  
        BufferedImage bufferedImage = new BufferedImage(temp.getWidth(null),  
                temp.getHeight(null), BufferedImage.TYPE_INT_RGB);   
        Graphics g = bufferedImage.createGraphics();  
        g.setColor(Color.white);  
        g.fillRect(0, 0, temp.getWidth(null), temp.getHeight(null));  
        g.drawImage(temp, 0, 0, null);  
        g.dispose();  
        
        float[] softenArray = { 0, softenFactor, 0, softenFactor,  
                1 - (softenFactor * 4), softenFactor, 0, softenFactor, 0 };  
        
        Kernel kernel = new Kernel(3, 3, softenArray);  
        ConvolveOp cOp = new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
        bufferedImage = cOp.filter(bufferedImage, null);  

        FileOutputStream out = new FileOutputStream(targetFile);  
        try {
        	     
            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);  
      
            JPEGEncodeParam param = encoder  
                    .getDefaultJPEGEncodeParam(bufferedImage);  
      
            param.setQuality(quality, true);             
            encoder.setJPEGEncodeParam(param);  
            encoder.encode(bufferedImage);  
            out.flush();
            out.close();
		} finally {
			if(out != null){
				out.close();
			}
		}
    }
	/**
	 * 获取图片大小
	 * @return
	 */
	public static Integer getImageSize(String path){
		File picture = new File(path);  
		return new Long(picture.length()/1024).intValue();
	}
	public static void main(String[] args) {
		String sourceFilePath = "D:\\imges.png";
		String targetFilePath = "";
		
		Date Date1 = new Date();
		
		
		try {
			File sourceFilePath1= new File(sourceFilePath);
			targetFilePath = "D:\\java\\img\\130x100.jpg";
			ImageUtils.compress(sourceFilePath1, targetFilePath, 130,100);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Date Date2 = new Date();
		Long longlm = (Date2.getTime()-Date1.getTime());
		System.out.println("耗时："+longlm/1000+"(秒)");
//		float i = 40l;
//		float scale = i/1000 ;
//		float dd = (float)(Math.round(scale*100))/100;
//		System.out.println(dd);
	}
	
}
