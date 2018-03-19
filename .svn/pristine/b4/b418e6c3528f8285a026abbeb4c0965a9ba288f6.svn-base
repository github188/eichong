/**
 * FileName:MultipartFileUtil.java
 * Author: Administrator
 * Create: 2014年8月10日
 * Last Modified: 2014年8月10日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bluemobi.product.common.CommonConsts;
import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.common.dao.MultipartFileDao;
import com.bluemobi.product.model.common.BasicMutiFileModel;
import com.wanma.common.MoveFileUtil;

/**
 * 文件工具类
 *
 * @version V1.0
 * @author Administrator
 * @date 2014年8月10日
 */
public class MultipartFileUtil {

	// 日志输出对象
	private static Logger log = Logger.getLogger(MultipartFileUtil.class);

	/**
	 * 取得所有文件url（APP用）
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static List<String> getAllMultiUrl(String type, String referenceId) {
		List<String> urlList = null;
		MultipartFileDao multipartFileDao = new MultipartFileDao();
		urlList = multipartFileDao.getAllMultiUrl(type, referenceId, false);
		return urlList;
	}

	/**
	 * 取得所有文件url（服务器用）
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static List<String> getServerMultiUrl(String type, String referenceId) {
		List<String> urlList = null;
		MultipartFileDao multipartFileDao = new MultipartFileDao();
		urlList = multipartFileDao.getAllMultiUrl(type, referenceId, true);
		return urlList;
	}

	/**
	 * 取得其他文件url
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static List<String> getServerOtherMultiUrl(String type,
			String referenceId) {
		List<String> urlList = null;
		MultipartFileDao multipartFileDao = new MultipartFileDao();
		urlList = multipartFileDao.getOtherMultiUrl(type, referenceId, true);
		return urlList;
	}

	/**
	 * 取得其他文件url
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static List<String> getOtherMultiUrl(String type, String referenceId) {
		List<String> urlList = null;
		MultipartFileDao multipartFileDao = new MultipartFileDao();
		urlList = multipartFileDao.getOtherMultiUrl(type, referenceId, false);
		return urlList;
	}

	/**
	 * 删除文件
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String delelteMulti(List<String> allMultiFile,
			String bussinessType, String referenceId) {
		return delelteMulti(allMultiFile, bussinessType, referenceId, "system");
	}

	/**
	 * 删除文件
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String delelteMulti(List<String> allMultiFile,
			String bussinessType, String referenceId, String userId) {
		String processFlg = CommonConsts.PROCESS_STATUS_OK;
		if (allMultiFile == null || allMultiFile.size() == 0) {
			return processFlg;
		}

		MultipartFileDao multipartFileDao = new MultipartFileDao();

		MessageManager messageManager = MessageManager.getMessageManager();
		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		// 完整路径
		String fullPath = "";
		// 正式文件存放路径
		String relFilePath = messageManager
				.getSystemProperties(CommonConsts.PRO_KEY_STORAGE_REL_PATH);
		// 图片存放相对路径
		String relativePath = messageManager.getSystemProperties(bussinessType);
		fullPath = relFilePath + "/" + relativePath;

		String dir = request.getSession().getServletContext()
				.getRealPath(fullPath);

		String fileName = "";
		try {

			for (int i = 0; i < allMultiFile.size(); i++) {
				String imageFileUrl = allMultiFile.get(i);

				if (StringUtils.isEmpty(imageFileUrl)) {
					continue;
				}

				if (imageFileUrl.indexOf("/") >= 0
						&& !imageFileUrl.endsWith("/")) {
					fileName = imageFileUrl.substring(imageFileUrl
							.lastIndexOf("/") + 1);
				} else {
					fileName = imageFileUrl;
				}

				//
				// 删除文件以及数据库的存储
				//
				//测试环境 使用 --start
				 /*if (FileUtils.directoryContains(new File(dir), new File(dir,
						fileName))) {
					FileUtils.deleteQuietly(new File(dir, fileName));
				}*/
				//测试环境 使用 --end
				
				//正式环境 使用 --start
				 MoveFileUtil.delete(bussinessType+"/"+fileName);
				//正式环境 使用 --end
				
				multipartFileDao.deleteMultiFile(bussinessType, referenceId,
						fileName);

			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			return CommonConsts.PROCESS_STATUS_NG;
		}

		return processFlg;
	}

	/**
	 * 保存所有文件
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String processAllMulti(BasicMutiFileModel mutiFileModel,
			String bussinessType, String referenceId, String userId,
			boolean isUpdate) {
		String processFlg = CommonConsts.PROCESS_STATUS_OK;
		if (mutiFileModel == null) {
			return processFlg;
		}
		MultipartFile[] allMultiFile = mutiFileModel.getAllMultiFile();
		
		MultipartFileDao multipartFileDao = new MultipartFileDao();

		MessageManager messageManager = MessageManager.getMessageManager();
		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		//测试环境 使用 --start
		/*// 完整路径
		String fullPath = "";
		// 正式文件存放路径
		String relFilePath = messageManager.getSystemProperties(CommonConsts.PRO_KEY_STORAGE_REL_PATH);
		// 图片存放相对路径
		String relativePath = messageManager.getSystemProperties(bussinessType);
		fullPath = relFilePath + "/" + relativePath;

		String dir = request.getSession().getServletContext()
				.getRealPath(fullPath);*/
		//测试环境 使用 --end
		
		//正式环境 使用 --start
		 String relativePath = messageManager.getSystemProperties(bussinessType);
		String dir =  messageManager.getSystemProperties(CommonConsts.PICTURE_SERVICE_UPLOADURL)+"/"+bussinessType; 
		//正式环境 使用 --end
		
		List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(
				bussinessType, referenceId);

		try {
			File fileDir = new File(dir);
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
			// 删除所有文件以及数据库的存储
			if (isUpdate && allMultiFileName != null
					&& allMultiFileName.size() > 0) {
				for (String fileName : allMultiFileName) {
					
					if (FileUtils.directoryContains(new File(dir), new File(
							dir, fileName))) {
						FileUtils.deleteQuietly(new File(dir, fileName));
					}
				}
				multipartFileDao.deleteAllMultiFile(bussinessType, referenceId);
			}

			/**
			 * 单文件
			 */
			if (mutiFileModel.getTitleMultiFile() != null
					&& mutiFileModel.getTitleMultiFile().getSize() > 0) {
				//
				// 保存标题图片
				//
				MultipartFile titleFile = mutiFileModel.getTitleMultiFile();

				// 上传文件名
				String originalName = titleFile.getOriginalFilename();

				// 存储用文件名取得
				String fileName = referenceId + getNowDateFormart()
						+ originalName.substring(originalName.lastIndexOf("."));

				// 保存文件
				FileUtils.copyInputStreamToFile(titleFile.getInputStream(),
						new File(dir, fileName));
				multipartFileDao.addTitleMultiFile(bussinessType, referenceId,
						relativePath, fileName, userId);
			}
//			单文件处理完成
			if (allMultiFile == null || allMultiFile.length == 0) {
				return processFlg;
			}

			//是否含有多文件,多文件处理
			for (int i = 0; i < allMultiFile.length; i++) {
				MultipartFile imageFile = allMultiFile[i];

				if (imageFile == null || imageFile.getSize() == 0) {
					continue;
				}

				// 上传文件名
				String originalName = imageFile.getOriginalFilename();
				// 存储用文件名取得
				String fileName = referenceId + getNowDateFormart() + i
						+ originalName.substring(originalName.lastIndexOf("."));

				//测试环境 使用 --start
				// 保存文件
				/*  FileUtils.copyInputStreamToFile(imageFile.getInputStream(),
						 new File(dir, fileName));*/
				//测试环境 使用 --end
				
				//正式环境 使用 --start
				 MoveFileUtil.fileCopybyFtp(imageFile.getInputStream(), bussinessType+"/"+fileName);
				//正式环境 使用 --end
				
				multipartFileDao.addOtherMultiFile(bussinessType, referenceId,
						relativePath, fileName, userId);
			}

		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			//System.out.println(e);
			return CommonConsts.PROCESS_STATUS_NG;
		}

		return processFlg;
	}
	/**
	 * 保存所有文件
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String processAllMultiNoPicture(BasicMutiFileModel mutiFileModel,
			String bussinessType, String referenceId, String userId,
			boolean isUpdate) {
		String processFlg = CommonConsts.PROCESS_STATUS_OK;
		if (mutiFileModel == null) {
			return processFlg;
		}
		MultipartFile[] allMultiFile = mutiFileModel.getAllMultiFile();

		MultipartFileDao multipartFileDao = new MultipartFileDao();

		MessageManager messageManager = MessageManager.getMessageManager();
		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		// 完整路径
		String fullPath = "";
		// 正式文件存放路径
		String relFilePath = messageManager.getSystemProperties(CommonConsts.PRO_KEY_STORAGE_REL_PATH);
		// 图片存放相对路径
		String relativePath = messageManager.getSystemProperties(bussinessType);
		fullPath = relFilePath + "/" + relativePath;

		String dir = request.getSession().getServletContext()
				.getRealPath(fullPath);
		List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(
				bussinessType, referenceId);

		try {
			File fileDir = new File(dir);
			if(!fileDir.exists()){
				fileDir.mkdir();
			}
			// 删除所有文件以及数据库的存储
			if (isUpdate && allMultiFileName != null
					&& allMultiFileName.size() > 0) {
				for (String fileName : allMultiFileName) {
					
					if (FileUtils.directoryContains(new File(dir), new File(
							dir, fileName))) {
						FileUtils.deleteQuietly(new File(dir, fileName));
					}
				}
				multipartFileDao.deleteAllMultiFile(bussinessType, referenceId);
			}

			/**
			 * 单文件
			 */
			if (mutiFileModel.getTitleMultiFile() != null
					&& mutiFileModel.getTitleMultiFile().getSize() > 0) {
				//
				// 保存标题图片
				//
				MultipartFile titleFile = mutiFileModel.getTitleMultiFile();

				// 上传文件名
				String originalName = titleFile.getOriginalFilename();

				// 存储用文件名取得
				String fileName = referenceId + getNowDateFormart()
						+ originalName.substring(originalName.lastIndexOf("."));

				
				multipartFileDao.addTitleMultiFile(bussinessType, referenceId,
						relativePath, fileName, userId);
			}
//			单文件处理完成
			if (allMultiFile == null || allMultiFile.length == 0) {
				return processFlg;
			}

			//是否含有多文件,多文件处理
			for (int i = 0; i < allMultiFile.length; i++) {
				MultipartFile imageFile = allMultiFile[i];

				if (imageFile == null || imageFile.getSize() == 0) {
					continue;
				}

				// 上传文件名
				String originalName = imageFile.getOriginalFilename();
				// 存储用文件名取得
				String fileName = referenceId + getNowDateFormart() + i
						+ originalName.substring(originalName.lastIndexOf("."));
				
				multipartFileDao.addOtherMultiFile(bussinessType, referenceId,
						relativePath, fileName, userId);
			}

		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
			return CommonConsts.PROCESS_STATUS_NG;
		}

		return processFlg;
	}

	/**
	 * 判断file是否上传了图片。 true-是 flase-否
	 * @param mutiFileModel
	 * @return
	 */
	public static boolean verifyImageIsNotNull(BasicMutiFileModel mutiFileModel){
		 boolean flage=true;
		MultipartFile[] allMultiFile = mutiFileModel.getAllMultiFile();
		for (int i = 0; i < allMultiFile.length; i++) {
			MultipartFile imageFile = allMultiFile[i];

			if (imageFile == null || imageFile.getSize() == 0) {
				flage=false;
				continue;
			}
		}
	
		return flage;
	}
	/**
	 * 保存所有文件（增加）
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String saveAllMulti(BasicMutiFileModel mutiFileModel,
			String bussinessType, String referenceId) {

		return saveAllMulti(mutiFileModel, bussinessType, referenceId, "system");
	}

	/**
	 * 保存所有文件（增加）
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String saveAllMulti(BasicMutiFileModel mutiFileModel,
			String bussinessType, String referenceId, String userId) {
		String processFlg = CommonConsts.PROCESS_STATUS_OK;
		if (mutiFileModel == null) {
			return CommonConsts.PROCESS_STATUS_NG;
		}
		processFlg = processAllMulti(mutiFileModel, bussinessType, referenceId,
				userId, false);
		return processFlg;
	}
	/**
	 * 只保存数据库信息，不生成图片
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String saveAllMultiNoPicture(BasicMutiFileModel mutiFileModel,
			String bussinessType, String referenceId) {

		return saveAllMultiNoPicture(mutiFileModel, bussinessType, referenceId, "system");
	}
	/**
	 * 只保存数据库信息，不生成图片
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String saveAllMultiNoPicture(BasicMutiFileModel mutiFileModel,
			String bussinessType, String referenceId, String userId) {
		String processFlg = CommonConsts.PROCESS_STATUS_OK;
		if (mutiFileModel == null) {
			return CommonConsts.PROCESS_STATUS_NG;
		}
		processFlg = processAllMultiNoPicture(mutiFileModel, bussinessType, referenceId,
				userId, false);
		return processFlg;
	}
	/**
	 * 保存所有文件（更新）
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String updateAllMulti(BasicMutiFileModel mutiFileModel,
			String bussinessType, String referenceId) {

		return updateAllMulti(mutiFileModel, bussinessType, referenceId,
				"system");
	}

	/**
	 * 保存所有文件（更新）
	 *
	 * @param type
	 *            图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String updateAllMulti(BasicMutiFileModel mutiFileModel,
			String bussinessType, String referenceId, String userId) {
		String processFlg = CommonConsts.PROCESS_STATUS_OK;
		if (mutiFileModel == null) {
			return CommonConsts.PROCESS_STATUS_NG;
		}
		processFlg = processAllMulti(mutiFileModel, bussinessType, referenceId,
				userId, true);
		return processFlg;
	}

	public static void main(String args[]) {
		List<String> urlList = MultipartFileUtil.getAllMultiUrl("signIn",
				"00001");
		for (String url : urlList) {
			System.out.println(url);
		}
	}

	public static String getNowDateFormart() {
		SimpleDateFormat dateformat = new SimpleDateFormat("yyMMddHHmmss");
		String date = dateformat.format(new Date());
		return date;

	}

	/**
	 * 保存所有文件
	 *
	 * @param bussinessType
	 *            图片业务分类
	 * @param fileName
	 *            图片文件名称
	 * @return 所有文件url
	 */
	public static String getFullPathByType(String bussinessType, String fileName) {

		MessageManager messageManager = MessageManager.getMessageManager();
		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();

		// 完整路径
		String fullPath = "";
		// 正式文件存放路径
		String relFilePath = messageManager
				.getSystemProperties(CommonConsts.PRO_KEY_STORAGE_REL_PATH);
		// 图片存放相对路径
		String relativePath = messageManager.getSystemProperties(bussinessType);
		fullPath = relFilePath + "/" + relativePath + "/" + fileName;

		String dir = request.getSession().getServletContext()
				.getRealPath(fullPath);

		return dir;
	}

	/**
	 * 保存图片信息到数据库
	 *
	 * @param bussinessType
	 *            业务分类
	 * @param referenceId
	 *            关联ID
	 * @param fileName
	 *            文件名
	 * @param isTitle
	 *            是否标题图片
	 * @return boolean 处理结果
	 */
	public static boolean saveMutiFileByUrl(String bussinessType,
			String referenceId, String fileName, boolean isTitle, String userId) {

		MessageManager messageManager = MessageManager.getMessageManager();
		// 图片存放相对路径
		String relativePath = messageManager.getSystemProperties(bussinessType);

		MultipartFileDao multipartFileDao = new MultipartFileDao();
		if (StringUtil.isEmpty(userId)) {
			userId = "system";
		}

		if (isTitle) {

			multipartFileDao.addTitleMultiFile(bussinessType, referenceId,
					relativePath, fileName, userId);
		} else {
			multipartFileDao.addOtherMultiFile(bussinessType, referenceId,
					relativePath, fileName, userId);
		}
		return true;
	}




	/**
	 * 单文件保存
	 *
	 * 图片业务分类
	 * @param referenceId
	 *            关联ID
	 * @return 所有文件url
	 */
	public static String uploadSingleFile(BasicMutiFileModel mutiFileModel,String bussinessType, String referenceId,String userId,boolean isUpdate) {
		if (mutiFileModel == null || mutiFileModel.getTitleMultiFile() == null || mutiFileModel.getTitleMultiFile().getSize()<=0) return null;
		MultipartFileDao multipartFileDao = new MultipartFileDao();
		MessageManager messageManager = MessageManager.getMessageManager();
		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();
		// 正式文件存放路径
		String relFilePath = messageManager.getSystemProperties(CommonConsts.PRO_KEY_STORAGE_REL_PATH);
		// relative path
		String relativePath = messageManager.getSystemProperties(bussinessType);
        // absolute path
        String fullPath = relFilePath + "/" + relativePath;
        String dir = request.getSession().getServletContext().getRealPath(fullPath);
		try {
			// 删除所有文件以及数据库的存储
            if(isUpdate){
                List<String> allMultiFileName = multipartFileDao.getAllMultiFileName(bussinessType, referenceId);
                if (allMultiFileName != null&&allMultiFileName.size() > 0) {

                    for (String fileName : allMultiFileName) {
                        if (FileUtils.directoryContains(new File(dir), new File(dir, fileName))) {
                            //删除整个文件夹下面的文件?
                            FileUtils.deleteQuietly(new File(dir, fileName));
                        }
                    }
                    multipartFileDao.deleteAllMultiFile(bussinessType, referenceId);
                }
            }
            // 保存标题图片
            MultipartFile titleFile = mutiFileModel.getTitleMultiFile();
            // 上传文件名
            String originalName = titleFile.getOriginalFilename();
            // 生成新文件名
            String fileName = referenceId + getNowDateFormart() + originalName.substring(originalName.lastIndexOf("."));
            // 保存文件至服务器
            FileUtils.copyInputStreamToFile(titleFile.getInputStream(),new File(dir, fileName));
            // 保存文件信息至DB
            multipartFileDao.addTitleMultiFile(bussinessType, referenceId,relativePath, fileName, userId);
            //返回图片路径
            return fullPath +"/"+fileName;
		} catch (IOException e) {
			log.error(e.getLocalizedMessage());
			return null;
		}
	}


    public static String uploadSingleFile(BasicMutiFileModel mutiFileModel,String bussinessType, String referenceId,boolean isUpdate) {
        return uploadSingleFile(mutiFileModel,bussinessType,referenceId,"system",isUpdate);
    }

    public static String uploadSingleFile(BasicMutiFileModel mutiFileModel,String bussinessType, String referenceId) {
        return uploadSingleFile(mutiFileModel,bussinessType,referenceId,false);
    }
    
    /**
     * 获取原文件File 及图片存储地址
     * @param mutiFileModel
     * @param bussinessType
     * @param referenceId
     * @return
     */
    public static Map<String,Object> getTargetFilePath(BasicMutiFileModel mutiFileModel,String bussinessType,String referenceId){
    	
		MessageManager messageManager = MessageManager.getMessageManager();
		HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();
		//01 获取相对路径
    	// 正式文件存放路径
		String relFilePath = messageManager.getSystemProperties(CommonConsts.PRO_KEY_STORAGE_REL_PATH);
		// 图片存放相对路径
		String relativePath = messageManager.getSystemProperties(bussinessType);
		String fullPath = relFilePath + "/" + relativePath;

		String dir = request.getSession().getServletContext()
				.getRealPath(fullPath);
		
		//02获取图片名称
		MultipartFile[] allMultiFile = mutiFileModel.getAllMultiFile();
		MultipartFile imageFile = allMultiFile[0];
		// 上传文件名
		String originalName = imageFile.getOriginalFilename();
		if(!StringUtils.isEmpty(originalName)){
			// 存储用文件名取得
			String fileName = referenceId + getNowDateFormart() + 0
					+ originalName.substring(originalName.lastIndexOf("."));
		    Map<String,Object> fileInfo=new HashMap<String,Object>();
		    fileInfo.put("fileName", fileName);
		    fileInfo.put("filePath", dir+"\\"+fileName);
			return  fileInfo;
			
		}else{
			return null;
		}
    }
    
    /**
     * 获取原文件File
     * @param mutiFileModel
     * @param bussinessType
     * @param referenceId
     * @return
     */
    public static File getSourceFilePath(BasicMutiFileModel mutiFileModel,String bussinessType,String referenceId){
    	
		//MessageManager messageManager = MessageManager.getMessageManager();
		//HttpServletRequest request = HttpServletRequestUtil.getHttpRequest();
		//01 获取相对路径
    	// 正式文件存放路径
		//String relFilePath = messageManager.getSystemProperties(CommonConsts.PRO_KEY_STORAGE_REL_PATH);
		// 图片存放相对路径
		//String relativePath = messageManager.getSystemProperties(bussinessType);
		//String fullPath = relFilePath + "/" + relativePath;

		/*String dir = request.getSession().getServletContext()
				.getRealPath(fullPath);*/
		//02获取图片名称
		MultipartFile[] allMultiFile = mutiFileModel.getAllMultiFile();
		MultipartFile imageFile = allMultiFile[0];
		 
		CommonsMultipartFile cf= (CommonsMultipartFile)imageFile; 
	    DiskFileItem fi = (DiskFileItem)cf.getFileItem(); 
	    File f = fi.getStoreLocation();
	    
		return  f ;
    }
}
