/**
 * FileName:ImageController.java
 * Author: Administrator
 * Create: 2014年7月3日
 * Last Modified: 2014年7月3日
 * Version: V1.0 
 */
package com.bluemobi.product.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluemobi.product.utils.FileUtil;

/**
 * 用户控制器
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月3日
 */
@Controller
@RequestMapping("/image")
public class ImageController {

	/**
	 * 用户登录处理
	 * 
	 * @author yangwr
	 * @since Version 1.0
	 * @param pager
	 *            分页显示相关信息
	 * @param model
	 *            画面显示对象
	 * @return 无
	 * @throws 无
	 */
	/*@RequestMapping(value = "/display")
	public void getImage(@RequestParam("imageName") String imageName,
			@RequestParam("displayType") String displayType,
			HttpServletRequest request, HttpServletResponse response) {
		// 图片路径
		String path = "";

		// 根据文件显示类型取得存放路径
		path = FileUtil.getPathByType(displayType);

		// 取得绝对路径
		String dir = request.getSession().getServletContext().getRealPath(path);
		byte[] bytes = null;
		FileInputStream fis = null;
		try {

			if (!FileUtils.directoryContains(new File(dir), new File(dir,
					imageName))) {
				bytes = new byte[0];
			} else {

				// 图片文件
				File image = new File(dir, imageName);
				fis = new FileInputStream(image);
				bytes = new byte[fis.available()];
				fis.read(bytes, 0, fis.available());
			}
			ServletOutputStream soo = response.getOutputStream();
			soo.write(bytes);
			soo.close();
			if (fis != null) {
				fis.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
}
