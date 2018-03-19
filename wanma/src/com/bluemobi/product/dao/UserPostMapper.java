/**
 * FileName:UserPostMapper.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.dao;

import com.bluemobi.product.model.UserPostModel;

/**
 * 用户职位表操作用DAO接口Mapper
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface UserPostMapper {

	/**
	 * 添加用户职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userPostModel
	 *            用户职位信息
	 * @return 无
	 * @throws 无
	 */
	public void addUserPost(UserPostModel userPostModel);

	/**
	 * 删除用户职位
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param userPostId
	 *            用户职位对象
	 * @return 无
	 * @throws 无
	 */
	public void removeUserPost(UserPostModel userPostModel);

}
