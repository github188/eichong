/**
 * FileName:IDSequenceUtil.java
 * Author: Administrator
 * Create: 2014年8月11日
 * Last Modified: 2014年8月11日
 * Version: V1.0 
 */
package com.bluemobi.product.utils;

import com.alibaba.druid.util.StringUtils;
import com.bluemobi.product.common.dao.SquenceManagerDao;
import com.wanma.app.util.DateUtil;
import com.wanma.common.ApplicationConsts;

/**
 * 序号生产工具类（只针对固定规则生成的序号)
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年8月11日
 */
public class IDSequenceUtil {

	public static final int DEFAULT_PLACE_LONG = 10;
	public static final int DEFAULT_PLACE = 8;
	public static final int DEFAULT_PLACE_SHORT = 6;

	/**
	 * 生成用户序号
	 * 
	 * @param userType
	 *            用户分类
	 * @return 用户ID
	 */
	/*public static String createUserId(String userType) {
		String userId = "";
		userId = createNomal(ApplicationConsts.SEQUENCE_USER, userType);
		return userId;

	}*/

	/**
	 * 生成长序号
	 * 
	 * @param userType
	 *            用户分类
	 * @return 用户ID
	 */
	/*public static String createNomal(String squenceId) {
		String squence = "";
		String squencePre = "";

		if (StringUtils
				.equals(squenceId, ApplicationConsts.SEQUENCE_FBACK)) {
			squencePre = "F";
		} 
		squence = getSequence(squenceId, DEFAULT_PLACE);
		squence = squencePre + "-" + DateUtil.getCurrentYear() + "-" + squence;
		return squence;

	}*/

	/**
	 * 生成长序号
	 * 
	 * @param userType
	 *            用户分类
	 * @return 用户ID
	 */
	/*public static String createNomal(String squenceId, String squencePre) {
		String squence = "";

	
		squence = getSequence(squenceId, DEFAULT_PLACE);
		squence = squencePre + "-" + DateUtil.getCurrentYear() + "-" + squence;
		return squence;

	}*/

	/**
	 * 生成短序号
	 * 
	 * @param userType
	 *            用户分类
	 * @return 用户ID
	 */
	/*public static String createNomalPure(String squenceId) {
		String squence = "";
		squence = getSequence(squenceId, DEFAULT_PLACE_LONG);
		squence = DateUtil.getCurrentYear() + squence;
		return squence;

	}*/

	/**
	 * 生成短序号
	 * 
	 * @param userType
	 *            用户分类
	 * @return 用户ID
	 */
	/*public static String createShort(String squenceId) {
		String squencePre = "";

		if (StringUtils.equals(squenceId,
				ApplicationConsts.SEQUENCE_COMP)) {
			squencePre = "comp";
		}
		String squence = "";
		squence = getSequence(squenceId, DEFAULT_PLACE_SHORT);
		squence = squencePre + DateUtil.getCurrentYear() + squence;
		return squence;

	}
*/
	/**
	 * 
	 * @param squenceId
	 * @param place
	 * @param isResetByYear
	 * @return
	 */
	/*public static String getSequence(String squenceId, int place) {
		return getSequence(squenceId, place, true);
	}*/

	/**
	 * 
	 * @param squenceId
	 * @param place
	 * @param isResetByYear
	 * @return
	 */
	/*public static String getSequence(String squenceId, int place,
			boolean isResetByYear) {
		// 新的序号
		long newSquence = 0;
		SquenceManagerDao dao = new SquenceManagerDao();
		newSquence = dao.getSequence(squenceId, isResetByYear);

		return FormartUtil.addPreZero(place, newSquence);
	}*/

}
