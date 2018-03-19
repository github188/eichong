/**
 * FileName:PeaceLiveCommon.java
 * Author: Administrator
 * Create: 2014年8月12日
 * Last Modified: 2014年8月12日
 * Version: V1.0 
 */
package com.wanma.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.alibaba.druid.util.StringUtils;
import com.bluemobi.product.common.BluemobiCommon;
import com.bluemobi.product.model.common.BasicListAndMutiFile;
import com.bluemobi.product.model.common.BasicListModel;
import com.bluemobi.product.model.common.BasicModel;
import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.bluemobi.product.utils.StringUtil;

/**
 * 乐居生活公共类
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月12日
 */
public class ApplicationCommon extends BluemobiCommon {

	/** 日志文件生成器 */
	private static Logger log = Logger.getLogger(ApplicationCommon.class);

	/** 分页默认每页数据数 */
	public static int NUM_PER_PAGE = 10;

	/** 分页默认每页数据数 */
	public static long LNG_PER_PAGE = 10L;

	/** 短信模板映射属性:用户名 */
	public static final String MSG_KEY_USER_NAME = "userName";
	/** 短信模板映射属性:订单号 */
	public static final String MSG_KEY_BOOK_ID = "bookId";
	/** 短信模板映射属性:订单号 */
	public static final String MSG_KEY_TRADE_CODE = "tradeCode";
	/** 短信模板映射属性:日期 */
	public static final String MSG_KEY_DATE = "date";
	/** 短信模板映射属性:金额 */
	public static final String MSG_KEY_AMOUNT = "amount";
	/** 短信模板映射属性:购买/下载方式 */
	public static final String MSG_KEY_GETTYPE = "getType";
	/** 短信模板映射属性:券分类 */
	public static final String MSG_KEY_TICKETTYPE = "ticketType";
	/** 短信模板映射属性:券名称 */
	public static final String MSG_KEY_ETICKETNAME = "eticketName";
	/** 短信模板映射属性:开始时间 */
	public static final String MSG_KEY_STARTDATE = "startDate";
	/** 短信模板映射属性:结束时间 */
	public static final String MSG_KEY_ENDDATE = "endDate";

	/**
	 * 设置分页信息
	 * 
	 * @param basicListModel
	 *            对象
	 * @param pageNumber
	 *            页码
	 */
	public static void setPagerData(BasicListAndMutiFile basicListModel,
			String pageNumber) {
		// 分页对象
		DwzPagerMySQL pPager = new DwzPagerMySQL();

		long lngNumber = 0;
		if (basicListModel == null || StringUtils.isEmpty(pageNumber)) {
			return;
		}

		lngNumber = getPageNumber(pageNumber);

		pPager.setPageNum(lngNumber);
		pPager.setNumPerPage(LNG_PER_PAGE);
		basicListModel.setPager(pPager);

	}

	/**
	 * 设置分页信息
	 * 
	 * @param basicListModel
	 *            对象
	 * @param pageNumber
	 *            页码
	 */
	public static void setPagerData(BasicListModel basicListModel,
			String pageNumber) {
		// 分页对象
		DwzPagerMySQL pPager = new DwzPagerMySQL();

		long lngNumber = 0;
		if (basicListModel == null || StringUtils.isEmpty(pageNumber)) {
			return;
		}

		lngNumber = getPageNumber(pageNumber);

		pPager.setPageNum(lngNumber);
		pPager.setNumPerPage(LNG_PER_PAGE);
		basicListModel.setPager(pPager);

	}

	/**
	 * 取得页码
	 * 
	 * @param pageNumber
	 *            String
	 * @return
	 */
	public static int getPageNumber(String pageNumber) {
		int lngNumber = 0;
		if (StringUtils.isEmpty(pageNumber)) {
			return 0;
		}

		try {
			lngNumber = Integer.parseInt(pageNumber);
		} catch (Exception e) {
			log.equals(e.getLocalizedMessage());
		}

		return lngNumber;
	}

	/**
	 * 用Map数据替换对应的变量
	 * 
	 * @param template
	 *            短信模板
	 * @param paramMap
	 *            参数对象
	 * @return String 替换后的短信
	 */
	public static String createMsgDetail(String template,
			Map<String, String> paramMap) {

		if (template == null) {
			return "";
		} else if (paramMap == null) {
			return template;
		}

		int m = 0, n = 0;
		int count = 0;
		ArrayList<String> word = new ArrayList<String>();

		for (int i = 0; i < template.length(); i++) {
			if (template.charAt(i) == '{') {
				if (count == 0) {
					m = i;
				}
				count++;
			}
			if (template.charAt(i) == '}') {
				count--;
				if (count == 0) {
					n = i;
					word.add(template.substring(m + 1, n));
				}
			}

		}

		for (String key : word) {
			// 替换用对象
			String model = "\\{" + key + "\\}";
			String value = StringUtil.nullToString(paramMap.get(key));
			// 替换内容
			template = template.replaceAll(model, value);

		}

		// 返回短信详情
		return template;
	}

	/**
	 * 将区域化字段空值改变为null
	 * 
	 * @param basicModel
	 */
	public static void setLocalNull(BasicModel basicModel) {

		if (StringUtil.isEmpty(basicModel.getProvinceId())) {
			basicModel.setProvinceId(null);
		}

		if (StringUtil.isEmpty(basicModel.getCityId())) {
			basicModel.setCityId(null);
		}

		if (StringUtil.isEmpty(basicModel.getAreaId())) {
			basicModel.setAreaId(null);
		}

		if (StringUtil.isEmpty(basicModel.getCommunityId())) {
			basicModel.setCommunityId(null);
		}
	}
	

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<String, String>();
		String template = "";
		map.put("userName", "Lily");
		System.out.print(ApplicationCommon.createMsgDetail(template, map));
	}
}
