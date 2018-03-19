package com.netCore.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 所有应用中的对象都应该继承该类
 * @author haojian
 * Apr 18, 2012 7:06:32 PM
 * 
 * @Id
	@Column
	@GeneratedValue(strategy=GenerationType.AUTO)
 * 
 * 
 * @Column
 * 
 * @Transient
 * 
 */
public abstract class GameObject implements Serializable, Cloneable {
	
	public static final long serialVersionUID = 1L;

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
