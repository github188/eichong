/**
 * FileName:ButtonTag.java
 * Author: Administrator
 * Create: 2014年6月30日
 * Last Modified: 2014年6月30日
 * Version: V1.0 
 */
package com.base.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.base.common.GlobalSystem;

/**
 * 按钮标签
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月30日
 */
public class ButtonTag extends CommonTag {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 3304989777218544309L;

	/** 信息内容关键字 */
	private String messageKey;

	/** 类型 */
	private String type;

	/** ID */
	private String id;

	/** 按钮按下处理 */
	private String onclick;

	/** 值 */
	private String value = null;

	/** 名称 */
	private String name = null;

	/** 只读 */
	private String disabled = "";

	/** 提示信息key */
	private String altKey = "";

	/** 样式 */
	private String style = "";

	public int doEndTag() throws JspException {

		String jsFunction = null;
		try {
			String label = "";
			String btnName = "";
			String visible = TagConst.BTN_CLASS_NORMAL;
			// 如果设定了message key
			if (StringUtils.isNotBlank(this.getMessageKey())) {
				// 取得key
				String key = this.getMessageKey();
				// key对应的值取得
				label = GlobalSystem.getConfig(key);
				// 按钮的名称设定
				btnName = key;
			} else {
				// 如果messagekey不存在的情况
				label = this.getValue();
				btnName = this.getName();
			}
			// 权限验证
			if (this.getIsAuth() && !this.hasAuth()) {
				visible = TagConst.BTN_CLASS_DISABLE;
			}
			// readonly
			if ("true".equals(this.getDisabled())) {
				visible = TagConst.BTN_CLASS_DISABLE;
			}
			// 按下时显示的信息
			String msg = "";
			if (StringUtils.isNotBlank(this.getAltKey())) {
				// 按下时显示的信息
				msg = GlobalSystem.getConfig(this.getAltKey());
			}
			if (StringUtils.isNotBlank(this.getOnclick())) {
				jsFunction = this.getOnclick();
			}
			JspWriter out = pageContext.getOut();
			StringBuffer sb = new StringBuffer();
			sb.append("<div class=\"" + visible + "\" id=\"" + this.getId()
					+ "_B\"> ");
			sb.append("<div class=\"" + TagConst.BUTTON_CLASS + "\">");
			sb.append("<button ");
			if (StringUtils.isNotBlank(this.getId())) {
				sb.append("id=\"" + this.getId() + "\"");
			}
			if (StringUtils.isNotBlank(this.getDwzClass())) {
				sb.append("class=\"" + this.getDwzClass() + "\"");
			}
			if (StringUtils.isNotBlank(jsFunction)) {
				sb.append("onclick=\"" + jsFunction + "\"");
			}
			if (StringUtils.isNotBlank(btnName)) {
				sb.append("name=\"" + btnName + "\"");
			}
			if (StringUtils.isNotBlank(msg)) {
				sb.append("alt=\"" + msg + "\"");
			}
			if (TagConst.BTN_CLASS_DISABLE.equals(visible)) {
				sb.append("disabled=\"true\"");
				this.setType("button");
			}
			if (StringUtils.isNotBlank(this.getType())) {
				sb.append("type=\"" + this.getType() + "\"");
			}
			if (StringUtils.isNotBlank(this.getStyle())) {
				sb.append("style=\"" + this.getStyle() + "\"");
			}
			sb.append(">");
			sb.append(label);
			sb.append("</button>");
			sb.append("</div></div>");
			out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("Failed to insert Button");

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

	/**
	 * 类型的取得。
	 * 
	 * @return 类型
	 */
	public String getType() {
		return type;
	}

	/**
	 * 类型的设定。
	 * 
	 * @param pType
	 *            类型
	 */
	public void setType(String pType) {
		this.type = pType;
	}

	/**
	 * ID的取得。
	 * 
	 * @return ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * ID的设定。
	 * 
	 * @param pId
	 *            ID
	 */
	public void setId(String pId) {
		this.id = pId;
	}

	/**
	 * 按钮按下处理的取得。
	 * 
	 * @return 按钮按下处理
	 */
	public String getOnclick() {
		return onclick;
	}

	/**
	 * 按钮按下处理的设定。
	 * 
	 * @param pOnclick
	 *            按钮按下处理
	 */
	public void setOnclick(String pOnclick) {
		this.onclick = pOnclick;
	}

	/**
	 * 值的取得。
	 * 
	 * @return 值
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 值的设定。
	 * 
	 * @param pValue
	 *            值
	 */
	public void setValue(String pValue) {
		this.value = pValue;
	}

	/**
	 * 名称的取得。
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 名称的设定。
	 * 
	 * @param pName
	 *            名称
	 */
	public void setName(String pName) {
		this.name = pName;
	}

	/**
	 * 只读的取得。
	 * 
	 * @return 只读
	 */
	public String getDisabled() {
		return disabled;
	}

	/**
	 * 只读的设定。
	 * 
	 * @param pDisabled
	 *            只读
	 */
	public void setDisabled(String pDisabled) {
		this.disabled = pDisabled;
	}

	/**
	 * 提示信息的取得。
	 * 
	 * @return 提示信息key
	 */
	public String getAltKey() {
		return altKey;
	}

	/**
	 * 提示信息的设定。
	 * 
	 * @param pAltKey
	 *            提示信息 key
	 */
	public void setAltKey(String pAltKey) {
		this.altKey = pAltKey;
	}

	/**
	 * 样式的取得。
	 *
	 * @return 样式
	 */
	public String getStyle() {
		return style;
	}

	/**
	 * 样式的设定。
	 *
	 * @param pStyle
	 *            样式
	 */
	public void setStyle(String pStyle) {
		this.style = pStyle;
	}

}
