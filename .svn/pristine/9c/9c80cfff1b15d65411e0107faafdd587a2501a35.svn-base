package com.bluemobi.product.utils;

import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImageUtils {
	/**
	 * BASE64加密图片字节
	 * @param filename
	 * @return 加密后的字符串
	 */
	public static String getBase64Pic(String filename) {
		String content = null;
		try {
			InputStream inputStream = new FileInputStream(filename);
			byte[] bytes = new byte[inputStream.available()];
			inputStream.read(bytes);
			content = new sun.misc.BASE64Encoder().encode(bytes); // 具体的编码方法
			inputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return content;
	}
	
	private static BufferedImage getResizedImage(BufferedImage source, int targetW,
			int targetH) {
		int type = source.getType();
		BufferedImage target = null;
		// targetW，targetH分别表示目标长和宽
		double sx = (double) targetW / source.getWidth();
		double sy = (double) targetH / source.getHeight();
		// 这里想实现在targetW，targetH范围内实现等比缩放。如果不需要等比缩放
		// 则将下面的if else语句注释即可
		if (sx > sy) {
			sx = sy;
			targetW = (int) (sx * source.getWidth());
		} else {
			sy = sx;
			targetH = (int) (sy * source.getHeight());
		}
		if (type == BufferedImage.TYPE_CUSTOM) { // handmade
			ColorModel cm = source.getColorModel();
			WritableRaster raster = cm.createCompatibleWritableRaster(targetW,targetH);
			boolean alphaPremultiplied = cm.isAlphaPremultiplied();
			target = new BufferedImage(cm, raster, alphaPremultiplied, null);
		} else{
			target = new BufferedImage(targetW, targetH, type);
		}
		Graphics2D g = target.createGraphics();
		// smoother than exlax:
		g.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		g.drawRenderedImage(source, AffineTransform.getScaleInstance(sx, sy));
		g.dispose();
		return target;
	}   
	/**
	 * 等比缩放图片
	 * @param image
	 * @param width
	 * @param height
	 * @return
	 */
	public static BufferedImage resize(String image,int width,int height){
		try {
			BufferedImage srcImage = ImageIO.read(new File(image));
			if (width > 0 || height > 0) {
				srcImage = getResizedImage(srcImage, width, height);
			}
			return srcImage;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void main(String[] args) throws IOException {
		
		 
			/**
		String imagePath = "E:\\test.png";
		BufferedImage image = getBufferedImage(imagePath, 100, 100);
		ImageIO.write(image, "png", new File("e:/"+System.currentTimeMillis()+".png"));
		*/
	}
}
