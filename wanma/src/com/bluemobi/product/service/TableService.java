/**
 * FileName:TableService.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.bluemobi.product.service;

import java.util.List;

import com.bluemobi.product.model.TableModel;

/**
 * 数据库表定义业务处理接口
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public interface TableService {

	/**
	 * 取得数据库表信息
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param tableId
	 *            数据库表ID
	 * @return 无
	 * @throws 无
	 */
	public TableModel findTable(String tableId);

	/**
	 * 取得数据库表一览
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @return List
	 *         <Table>
	 *         数据库表一览
	 * @throws 无
	 */
	public List<TableModel> getTableList();

	/**
	 * 添加数据库表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param TableModel
	 *            数据库表信息
	 * @return Table 数据库表信息
	 * @throws 无
	 */
	public TableModel addTable(TableModel tableModel);

	/**
	 * 编辑数据库表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param TableModel
	 *            数据库表信息
	 * @return String 处理结果标识
	 * @throws 无
	 */
	public String modifyTable(TableModel tableModel);

	/**
	 * 删除数据库表
	 * 
	 * @author yangweir
	 * @since Version 1.0
	 * @param tableId
	 *            数据库表ID
	 * @return 无
	 * @throws 无
	 */
	public void removeTable(String tableId);

}
