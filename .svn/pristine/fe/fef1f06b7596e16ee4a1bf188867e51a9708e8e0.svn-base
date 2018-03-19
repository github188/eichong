package com.bluemobi.product.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * 验证码生成器，使用此类需要将表单里的验证码输入框的name属性设为"verifycode"
 * 
 * VerifyCodeGenerator vg = VerifyCodeGenerator.getInstance();
 * vg.printImage(request, response);
 * 
 * vg.check(request)
 */
public class ImageCodeUtil {

	private static final ImageCodeUtil generator = new ImageCodeUtil();

	private final String ATTRIBUTE_NAME = "verifycode";
	// 图片的宽度
	private final int WIDTH = 15;
	// 图片的高度
	private final int HEIGHT = 22;
	// 字符串长度
	private final int CODE_LENGTH = 4;
	// 随机字符串范围
	private final String RAND_RANGE = "abcdefghijklmnopqrstuvwxyz"
			+ "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "1234567890" + "@#quot";

	private final char[] CHARS = RAND_RANGE.toCharArray();

	private Random random = new Random();

	private ImageCodeUtil() {
		//
	}

	public static ImageCodeUtil getInstance() {
		return generator;
	}

	/**
	 * 生成随机字符串
	 * 
	 * @return 随机字符串
	 */
	private String getRandString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < CODE_LENGTH; i++)
			sb.append(CHARS[random.nextInt(CHARS.length)]);
		return sb.toString();
	}

	/**
	 * 生成随机颜色
	 * 
	 * @param ll
	 *            产生颜色值下限(lower limit)
	 * @param ul
	 *            产生颜色值上限(upper limit)
	 * @return 生成的随机颜色对象
	 */
	private Color getRandColor(int ll, int ul) {
		if (ll > 255)
			ll = 255;
		if (ll < 1)
			ll = 1;
		if (ul > 255)
			ul = 255;
		if (ul < 1)
			ul = 1;
		if (ul == ll)
			ul = ll + 1;
		int r = random.nextInt(ul - ll) + ll;
		int g = random.nextInt(ul - ll) + ll;
		int b = random.nextInt(ul - ll) + ll;
		Color color = new Color(r, g, b);
		return color;
	}

	/**
	 * 生成指定字符串的图像数据
	 * 
	 * @param verifyCode
	 *            即将被打印的随机字符串
	 * @return 生成的图像数据
	 * */
	private BufferedImage getImage(String verifyCode) {

		BufferedImage image = new BufferedImage(WIDTH * CODE_LENGTH, HEIGHT,
				BufferedImage.TYPE_INT_RGB);

		// 获取图形上下文
		Graphics graphics = image.getGraphics();

		// 设置背景色
		graphics.setColor(getRandColor(1, 50));
		// 填充背景色
		graphics.fillRect(0, 0, WIDTH * 4, HEIGHT);

		// 设置边框颜色
		graphics.setColor(new Color(0, 255, 0));
		// 画边框
		for (int i = 0; i < 2; i++)
			graphics.drawRect(i, i, WIDTH * CODE_LENGTH - i * 2 - 1, HEIGHT - i
					* 2 - 1);

		// 设置随机干扰线条颜色
		graphics.setColor(getRandColor(50, 100));
		// 产生50条干扰线条
		for (int i = 0; i < 50; i++) {
			int x1 = random.nextInt(WIDTH * CODE_LENGTH - 4) + 2;
			int y1 = random.nextInt(HEIGHT - 4) + 2;
			int x2 = random.nextInt(WIDTH * CODE_LENGTH - 2 - x1) + x1;
			int y2 = y1;
			graphics.drawLine(x1, y1, x2, y2);
		}

		// 设置字体
		graphics.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		// 画字符串
		for (int i = 0; i < this.CODE_LENGTH; i++) {
			String temp = verifyCode.substring(i, i + 1);
			graphics.setColor(getRandColor(100, 255));
			graphics.drawString(temp, 13 * i + 6, 16);
		}

		// 图像生效
		graphics.dispose();

		return image;
	}

	/**
	 * 将验证码的图像输出
	 * 
	 * @param request
	 *            用户的请求对象
	 * @param response
	 *            用户的响应对象
	 * */
	public void printImage(HttpServletRequest request,
			HttpServletResponse response) {
		// 将ContentType设为"image/jpeg"，让浏览器识别图像格式。
		response.setContentType("image/jpeg");
		// 设置页面不缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 2000);

		// 获得随机验证码
		String verifyCode = this.getRandString();
		String str = "ssss";
		for (int i = 0; i < 10; i++)
			str = str + str;
		// 获得验证码的图像数据
		BufferedImage bi = this.getImage(verifyCode);
		// 把验证码存入session
		request.getSession().setAttribute(ATTRIBUTE_NAME, verifyCode);
		try {
			// 获得Servlet输出流
			ServletOutputStream outStream = response.getOutputStream();
			// 创建可用来将图像数据编码为JPEG数据流的编码器
			JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(outStream);
			// 将图像数据进行编码
			encoder.encode(bi);
			// 强行将缓冲区的内容输入到页面
			outStream.flush();
			// 关闭输出流
			outStream.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * 检查输入的验证码是否正确，若用户输入的验证码与生成的验证码相符则返回true，否则返回false。
	 * 
	 * @param request
	 *            用户的请求对象
	 * @return 验证结果
	 * */
	public boolean check(HttpServletRequest request) {
		if (((String) request.getParameter(ATTRIBUTE_NAME))
				.equalsIgnoreCase((String) request.getSession().getAttribute(
						ATTRIBUTE_NAME))) {
			request.getSession().removeAttribute(ATTRIBUTE_NAME);
			return true;
		}
		return false;
	}
}
