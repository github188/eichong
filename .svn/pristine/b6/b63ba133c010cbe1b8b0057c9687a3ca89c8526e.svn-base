package com.wanma.web.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblProductcomment;
import com.wanma.web.dao.WebProductcommentMapper;
import com.wanma.web.service.WebProductCommentService;

/**
 * @Description:产品评论业务处理类
 * @author songjf
 * @createTime：2015-3-16 下午03:34:08
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class WebProductCommentServiceImpl implements WebProductCommentService {

	/* 产品评论表操作dao */
	@Autowired
	private WebProductcommentMapper productcommentMapper;

	/**
	 * @Title: insertProCommnet
	 * @Description: 新增产品描述
	 * @param productcomment
	 * @return
	 */
	@Override
	public void insertProCommnet(TblProductcomment productcomment) {
		productcomment.setPrcoCreatetime(new Date());
		productcomment.setPrcoCreatedate(new Date());
		productcomment.setPrcoUpdatedate(new Date());
		productcomment.setPrcoUtatus(0);
		productcommentMapper.insert(productcomment);

		if (ObjectUtil.isNotEmpty(productcomment.getAllMultiFile())) {
			MultipartFileUtil.saveAllMulti(productcomment,
					WanmaConstants.MULTI_TYPE_PRODUCT_COMMENT_IMAGE,
					productcomment.getPkProductcomment() + "");
		}

	}

	/**
	 * @Title: findProComments
	 * @Description: 获取产品评论
	 * @param params
	 * @return
	 */
	@Override
	public List<TblProductcomment> findProComments(Map<String, Object> params) {
		List<TblProductcomment> commentList = productcommentMapper.findProComments(params);
		if (ObjectUtil.isNotEmpty(commentList)) {
			for (TblProductcomment productcomment : commentList) {
				productcomment.setCommPicList(MultipartFileUtil.getAllMultiUrl(
						WanmaConstants.MULTI_TYPE_PRODUCT_COMMENT_IMAGE,
						productcomment.getPkProductcomment() + ""));

			}
		}
		return commentList;
	}
	@Override
	public long countProComments(Map<String, Object> params) {
		return productcommentMapper.countProComments(params);
	}


}
