package com.wanma.app.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bluemobi.product.utils.MultipartFileUtil;
import com.bluemobi.product.utils.ObjectUtil;
import com.wanma.app.dao.AppProductcommentMapper;
import com.wanma.app.service.AppProductCommentService;
import com.wanma.common.WanmaConstants;
import com.wanma.model.TblProductcomment;

/**
 * @Description:产品评论业务处理类
 * @author songjf
 * @createTime：2015-3-16 下午03:34:08
 * @updator：
 * @updateTime：
 * @version：V1.0
 */
@Service
public class AppProductCommentServiceImpl implements AppProductCommentService {

	/* 产品评论表操作dao */
	@Autowired
	private AppProductcommentMapper productcommentMapper;

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
		List<TblProductcomment> commentList = productcommentMapper
				.findProComments(params);
		if (ObjectUtil.isNotEmpty(commentList)) {
			for (TblProductcomment productcomment : commentList) {
				productcomment.setCommPicList(MultipartFileUtil.getAllMultiUrl(
						WanmaConstants.MULTI_TYPE_PRODUCT_COMMENT_IMAGE,
						productcomment.getPkProductcomment() + ""));

			}
		}
		return commentList;
	}
	
	/**
	 * @Title: getMaxStar
	 * @Description: 查询最大星级数
	 * @param tblProductcomment
	 * @return
	 */
	@Override
	public Double getMaxStar(TblProductcomment tblProductcomment){
		Double star=productcommentMapper.getMaxStar(tblProductcomment);
		if(star==null){
			star=0.0;
		}
		return star;
	}
	
	/**
	 * @Title: getCommitCount
	 * @Description: 查询pinlun
	 * @param tblProductcomment
	 * @return
	 */
	@Override
	public long getCommitCount(TblProductcomment tblProductcomment){
		return productcommentMapper.searchIdCount(tblProductcomment);
	}

}
