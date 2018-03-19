package com.wanma.web.controller;

import java.io.File;
import java.sql.Timestamp;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bluemobi.product.common.MessageManager;
import com.bluemobi.product.utils.AccessErrorResult;
import com.bluemobi.product.utils.AccessSuccessResult;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblPublishEp;
import com.wanma.web.service.impl.WebPublishEpServiceImpl;
import com.wanma.web.support.utils.PathUtil;

@Controller
@RequestMapping("/web/publishEp")
public class WebPublishEpController {
	private static Log log = LogFactory.getLog(WebPublishEpController.class);
	@Autowired
	WebPublishEpServiceImpl webPublishEpService;

	/**
	 * 添加分享
	 * 
	 * @param params
	 *            img 图片 address 地址 longitude 经度 latitude 维度 parameter_note 参数说明
	 *            note 备注
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public String addShareEp(TblPublishEp tblPublishEp, HttpServletRequest req) {
		MultipartHttpServletRequest request = (MultipartHttpServletRequest) req;
		List<MultipartFile> files = request.getFiles("file1");
		MultipartFile[] array=new MultipartFile[files.size()];
		for(int i=0;i<files.size();i++){
			array[i]=files.get(i);
		}
		if (files.isEmpty()) {
			return new AccessErrorResult("error.msg.invalid.file")
					.toString();
		}
		tblPublishEp.setAllMultiFile(array);
		if (StringUtils.isEmpty(tblPublishEp.getAddress())
				|| StringUtils.isEmpty(tblPublishEp.getLongitude())
				|| StringUtils.isEmpty(tblPublishEp.getLatitude())) {
			log.error("参数不完整！WebPublishEpController：addShareEp");
			return new AccessErrorResult("error.msg.invalid.parameter")
					.toString();
		}
		tblPublishEp.setEdittime(new Timestamp(System.currentTimeMillis()));
		try {
			webPublishEpService.addShareEp(tblPublishEp);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
			log.error("获取产品信息错误", e);
			return new AccessErrorResult("error.msg.invalid.product")
					.toString();
		}
		return new AccessSuccessResult().toString();
	}
}
