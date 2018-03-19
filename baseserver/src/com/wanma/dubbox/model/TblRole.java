/**
 * FileName:RoleModel.java
 * Author: Administrator
 * Create: 2014年6月26日
 * Last Modified: 2014年6月26日
 * Version: V1.0 
 */
package com.wanma.dubbox.model;

import com.wanma.dubbox.model.common.BasicModel;

/**
 * 角色实体数据模型
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月26日
 */
public class TblRole extends BasicModel {
	private static final long serialVersionUID = 4896055021710137409L;

	private String name;// 名称

	private String ctgry;// 角色分类 1:超级管理员，2：普通管理员，3：纯商家，4子商家，5个体商家

	private String nt;// 备注

	private Integer creUs;// 创建人

	private Integer updUs;// 修改人

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCtgry() {
		return ctgry;
	}

	public void setCtgry(String ctgry) {
		this.ctgry = ctgry;
	}

	public String getNt() {
		return nt;
	}

	public void setNt(String nt) {
		this.nt = nt;
	}

	public Integer getCreUs() {
		return creUs;
	}

	public void setCreUs(Integer creUs) {
		this.creUs = creUs;
	}

	public Integer getUpdUs() {
		return updUs;
	}

	public void setUpdUs(Integer updUs) {
		this.updUs = updUs;
	}
}
