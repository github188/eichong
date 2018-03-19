package com.bluemobi.product.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.utils.FileUtil;

/**
 * 富文本文件上传方法
 * 
 */
@Controller
@RequestMapping("/admin/uuUploader")
public class UUController {

	// 日志输出对象
	private static Logger log = Logger.getLogger(UUController.class);

	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public String uploadFile(
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value = "filedata", required = false) MultipartFile file)
			throws IllegalStateException, IOException {

		log.info("上传图片");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("err", "");
		// 处理结果信息
		try {

			int fileLength = request.getContentLength();
			String deployUrl;
			String richtTextPath;

			MessageManager manager = MessageManager.getMessageManager();

			deployUrl = manager.getSystemProperties("deploy.url");
			richtTextPath = manager
					.getSystemProperties("storage.path.rich.text");
			// 获取服务IP、端口、项目名称
			StringBuilder builder = new StringBuilder();
			builder.append(deployUrl);

			// 文件大小限制
			if (fileLength > 2 * 1024 * 1024) {
				map.put("err", "上传文件不能大于2M");
				map.put("msg", "");
			} else {

				if ("application/octet-stream".equals(request.getContentType())) {

					String dispoString = request.getHeader(
							"Content-Disposition").replaceAll("\"", "");
					// 获取文件名称
					String sFileName = dispoString.substring(dispoString
							.indexOf("filename=") + 9);
					String fileName = getName(sFileName);

					// 设置文件夹名称
					SimpleDateFormat formater = new SimpleDateFormat("yy/MMdd");
					String sPath = richtTextPath.concat("/").concat(
							formater.format(new Date())).concat("/");

					// 获取保存的路径
					String path = getPhysicalPath(request, sPath).concat("/");
					File imgFile = new File(path.concat(fileName));
					FileUtils.copyInputStreamToFile(request.getInputStream(),
							imgFile);

					// 设置返回信息
					map.put("msg", builder.append(sPath).append(fileName));
				} else {
					if (!file.isEmpty()) {
						// 获取文件名称
						String fileName_ = file.getOriginalFilename();
						String fileName = getName(FileUtil.getName(fileName_));

						SimpleDateFormat formater = new SimpleDateFormat(
								"yy/MMdd");
						String sPath = richtTextPath.concat("/").concat(formater.format(new Date()).concat("/"));

						// 获取保存的路径
						String path = getPhysicalPath(request, sPath).concat(
								"/");

						// 保存文件
						FileUtils.copyInputStreamToFile(file.getInputStream(),
								new File(path, fileName));

						// 设置返回信息
						map.put("msg", builder.append(sPath).append(fileName));
					}
				}
			}

		} catch (Exception e) {
			// 出错日志
			log.error("error", e);
			map.put("err", "保存文件异常!");
			map.put("msg", "");
		}
		// 返回处理结果信息
		return new String(
				JSONObject.toJSONString(map).getBytes("utf-8"),
				"iso-8859-1");
	}

	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	private String getName(String fileName) {
		Random random = new Random();
		return fileName = "" + random.nextInt(10000)
				+ System.currentTimeMillis() + this.getFileExt(fileName);
	}

	private String getPhysicalPath(HttpServletRequest request, String sPath) {
		String uploadpath = request.getSession().getServletContext()
				.getRealPath(sPath);
		return uploadpath;
	}
}
