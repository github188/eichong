/**
 * FileName:RoleModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.dubbox.model;

import java.util.Date;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 角色用户实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class TblMenuRole extends BasicModel {
	private static final long serialVersionUID = 4896055021710137409L;

	private String mid;// 菜单id
	private String rid;// 角色id

	private Integer creUs;// 创建人

	private Integer udtUs;// 修改人

	private String[] rolIds;

	public String getMid() {
		return mid;
	}

	public void setMid(String mid) {
		this.mid = mid;
	}

	public String getRid() {
		return rid;
	}

	public void setRid(String rid) {
		this.rid = rid;
	}

	public Integer getCreUs() {
		return creUs;
	}

	public void setCreUs(Integer creUs) {
		this.creUs = creUs;
	}

	public Integer getUdtUs() {
		return udtUs;
	}

	public void setUdtUs(Integer udtUs) {
		this.udtUs = udtUs;
	}

	public String[] getRolIds() {
		return rolIds;
	}

	public void setRolIds(String[] rolIds) {
		this.rolIds = rolIds;
	}
}
