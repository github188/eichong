package com.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.log4j.Logger;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.base.common.CommonConsts;
import com.base.common.GlobalSystem;
import com.base.common.WanmaConstants;

public class MultiFileUtil {

	// 日志输出对象
	private static Logger log = Logger.getLogger(MultiFileUtil.class);

	/**
	 * 取得所有文件url
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static List<String> getUrls(String type, String referenceId) {
		MultiFileDao multipartFileDao = new MultiFileDao();
		return multipartFileDao.getUrls(type, referenceId, null);
	}

	/**
	 * 删除文件
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 删除结果 001成功 002失败
	 */
	public static String delelteFile(String bussinessType, String referenceId) {
		return delelteFile(bussinessType, referenceId, null);
	}

	/**
	 * 删除文件
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @param fileName
	 *            文件名
	 * @return 删除结果 001成功 002失败
	 */
	public static String delelteFile(String bussinessType, String referenceId,
			String fileName) {
		String processFlg = CommonConsts.PROCESS_STATUS_OK;
		MultiFileDao multipartFileDao = new MultiFileDao();
		List<String> allFileUrls = multipartFileDao.getUrls(bussinessType,
				referenceId, fileName);
		multipartFileDao.deleteFile(bussinessType, referenceId, fileName);
		try {
			log.info("删除文件:" + allFileUrls);
			for (String fileUrl : allFileUrls) {
				if (ObjectUtil.isEmpty(fileUrl)) {
					continue;
				}
				if (fileUrl.indexOf("/") >= 0 && !fileUrl.endsWith("/")) {
					fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
				} else {
					fileName = fileUrl;
				}
				// 正式环境 使用 --start
				MoveFileUtil.delete(bussinessType + "/" + fileName);
				// 正式环境 使用 --end
				log.debug("删除压缩文件开始");
				String zipFileName = replaceName(fileName,
						WanmaConstants.PICTRUE_W, WanmaConstants.PICTRUE_H);
				MoveFileUtil.delete(bussinessType + "/" + zipFileName);
				zipFileName = replaceName(fileName,
						WanmaConstants.PICTRUE_W_1280,
						WanmaConstants.PICTRUE_H_720);
				MoveFileUtil.delete(bussinessType + "/" + zipFileName);
				log.debug("删除压缩文件结束");
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return CommonConsts.PROCESS_STATUS_NG;
		}

		return processFlg;
	}

	/**
	 * 对图片文件名进行长宽度重命名
	 * 
	 * @param fileName
	 * @param width
	 * @param height
	 * @return
	 */
	private static String replaceName(String fileName, int width, int height) {
		String filePrefixName = fileName
				.substring(0, fileName.lastIndexOf("."));
		String fileSuffixName = fileName.substring(fileName.lastIndexOf("."));
		String resultFileName = filePrefixName + "_" + width + "X" + height
				+ fileSuffixName;
		return resultFileName;
	}

	/**
	 * 判断file是否上传了图片。 true-是 flase-否
	 * 
	 * @param multiFiles
	 * @return
	 */
	public static boolean hasFiles(MultipartFile[] multiFiles) {
		boolean flag = true;
		for (int i = 0; i < multiFiles.length; i++) {
			MultipartFile multiFile = multiFiles[i];
			if (multiFile == null || multiFile.getSize() == 0) {
				flag = false;
				continue;
			}
		}
		return flag;
	}
	
	/**
	 * 保存所有文件
	 * @param multiFiles
	 * @param bussinessType
	 * @param referenceId
	 * @return
	 */
	public static String saveFile(MultipartFile[] multiFiles,
			String bussinessType, String referenceId) {
		return saveFile(multiFiles, bussinessType, referenceId, false, false);
	}
	/**
	 * 保存所有文件
	 * 
	 * @param multiFiles
	 * @param bussinessType
	 * @param referenceId
	 * @param isZip
	 * @return
	 */
	public static String saveFile(MultipartFile[] multiFiles,
			String bussinessType, String referenceId, boolean isZip) {
		return saveFile(multiFiles, bussinessType, referenceId, isZip, false);
	}

	/**
	 * 保存所有文件
	 * 
	 * @param multiFiles
	 * @param bussinessType
	 * @param referenceId
	 * @param isZip
	 * @param isTitle
	 * @return
	 */
	public static String saveFile(MultipartFile[] multiFiles,
			String bussinessType, String referenceId, boolean isZip,
			boolean isTitle) {
		String processFlg = CommonConsts.PROCESS_STATUS_OK;
		MultiFileDao multipartFileDao = new MultiFileDao();
		try {
			if (ObjectUtil.isEmpty(multiFiles)) {
				return processFlg;
			}
			MultipartFile upFile = null;
			int size = multiFiles.length;

			for (int i = 0; i < size; i++) {
				upFile = multiFiles[i];
				if (upFile == null || upFile.getSize() == 0) {
					continue;
				}
				CommonsMultipartFile cf = (CommonsMultipartFile) upFile;
				// 上传文件名
				String originalName = cf.getOriginalFilename();
				// 存储用文件名
				String filePrefixName = referenceId + UUIDUtil.uuid() + i;
				String fileSuffixName = originalName.substring(originalName
						.lastIndexOf("."));
				// 得到文件上传到服务器的临时位置
				String dir = getFileDir(bussinessType);
				// 判断是否压缩文件,压缩文件并上传到Zip额外后缀的ftp文件夹中
				if (isZip) {
					int width = WanmaConstants.PICTRUE_W;
					int height = WanmaConstants.PICTRUE_H;
					String zipFileName = filePrefixName + "_" + width + "X"
							+ height + fileSuffixName;
					compressAndUpload(upFile, dir, zipFileName, bussinessType,
							width, height);
					width = WanmaConstants.PICTRUE_W_1280;
					height = WanmaConstants.PICTRUE_H_720;
					zipFileName = filePrefixName + "_" + width + "X" + height
							+ fileSuffixName;
					compressAndUpload(upFile, dir, zipFileName, bussinessType,
							width, height);
				}
				String fileName = filePrefixName + fileSuffixName;
				MoveFileUtil.fileCopybyFtp(upFile.getInputStream(),
						bussinessType + "/" + fileName);
				multipartFileDao.saveFile(bussinessType, referenceId,
						bussinessType, fileName, isTitle);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			System.out.println(e);
			return CommonConsts.PROCESS_STATUS_NG;
		}
		return processFlg;
	}

	/**
	 * 获取文件转存地址
	 * 
	 * @param bussinessType
	 * @return
	 */
	public static String getFileDir(String bussinessType) {
		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();
		// 01 获取相对路径
		// 正式文件存放路径
		String relFilePath = GlobalSystem
				.getConfig(CommonConsts.PRO_KEY_STORAGE_REL_PATH);
		// 图片存放相对路径
		String relativePath = GlobalSystem.getConfig(bussinessType);
		String fullPath = relFilePath + "/" + relativePath;

		String dir = request.getSession().getServletContext()
				.getRealPath(fullPath);

		return dir;
	}

	public static void compressAndUpload(MultipartFile file, String dir,
			String fileName, String bussinessType, int width, int height)
			throws Exception {
		InputStream is = null;
		File tempFile = null;
		String filePath = dir + File.separator + fileName;
		try {
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fi = (DiskFileItem) cf.getFileItem();
			File f = fi.getStoreLocation();
			ImageUtils.compress(f, filePath, width, height);
			tempFile = new File(filePath);
			is = new FileInputStream(tempFile);
			// 上传子文件
			MoveFileUtil.fileCopybyFtp(is, bussinessType + "/" + fileName);
			is.close();
			if (tempFile != null && tempFile.exists()) {
				tempFile.delete();
			}
		} catch (Exception e) {
			log.error("文件压缩失败:" + e.getLocalizedMessage());
			is = file.getInputStream();
		}
	}

	public static void main(String[] args) {
		MultiFileUtil.delelteFile("userAvatar", "21587");
	}
}