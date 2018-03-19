package com.wanma.ims.service;

import com.wanma.ims.common.domain.UserDO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by xyc on 2017/6/26.
 * 图片文件逻辑处理接口
 */
public interface MultipartFileService {

    /**
     * 根据业务类型和业务ID取得图片文件url
     */
    List<String> getAllMultiUrl(String businessType, String referenceId);

    /**
     * 保存图片文件
     */
    String saveMultiFile(MultipartFile[] allMultiFile, String businessType, String referenceId, boolean izZip, UserDO loginUser);

    /**
     * 删除图片文件
     */
    String deleteMulti(List<String> allMultiFile, String businessType, String referenceId, UserDO loginUser);
}
