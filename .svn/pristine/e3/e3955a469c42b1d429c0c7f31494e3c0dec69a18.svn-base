/**
 * FileName:MessageTag.java
 * Author: Administrator
 * Create: 2014年7月2日
 * Last Modified: 2014年7月2日
 * Version: V1.0 
 */
package com.base.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.base.common.GlobalSystem;

/**
 * 消息输出标签
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年7月2日
 */
public class MessageTag extends CommonTag {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 4877778629625122635L;

	/** 信息内容关键字 */
	private String messageKey;

	public int doEndTag() throws JspException {

		try {

			String label = "";


			// 取得key
			String key = this.getMessageKey();
			// key对应的值取得
			label = GlobalSystem.getConfig(key);

			JspWriter out = pageContext.getOut();

			out.println(label);

		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("Failed to get Message");

		}

		return EVAL_PAGE;
	}

	/**
	 * 信息内容关键字的取得。
	 * 
	 * @return 信息内容关键字
	 */
	public String getMessageKey() {
		return messageKey;
	}

	/**
	 * 信息内容关键字的设定。
	 * 
	 * @param pMessageKey
	 *            信息内容关键字
	 */
	public void setMessageKey(String pMessageKey) {
		this.messageKey = pMessageKey;
	}

}
