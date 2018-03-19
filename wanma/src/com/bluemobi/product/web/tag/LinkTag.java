/**
 * FileName:LinkTag.java
 * Author: Administrator
 * Create: 2014年6月30日
 * Last Modified: 2014年6月30日
 * Version: V1.0 
 */
package com.bluemobi.product.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import org.apache.commons.lang.StringUtils;

import com.bluemobi.product.common.MessageManager;

/**
 * 链接标签
 * 
 * @version V1.0
 * @author Administrator
 * @date 2014年6月30日
 */
public class LinkTag extends CommonTagSupport {

	/**
	 * serial version ID
	 */
	private static final long serialVersionUID = 3304989777218544309L;

	/** 链接 */
	private String href;
	/** 链接有其他用的时候，权限通过authUrl判断 */
	private String authUrl;

	/** 显示目标标签 */
	private String target;

	/** 关联组件 */
	private String rel;

	/** 信息内容关键字 */
	private String messageKey;

	/** 按钮按下处理 */
	private String onclick;

	/** 查找组 */
	private String lookupGroup;

	/** 查找主键 */
	private String lookupPk;

	/** 提示信息key */
	private String altKey = "";

	/** 样式 */
	private String style = "";

	/** 目标分类 */
	private String targetType = "";
	private String postType = "";

	public int doEndTag() throws JspException {

		try {

			String label = "";
			String visible = TagConst.BTN_CLASS_NORMAL;
			// 实例化 MessageManager
			MessageManager messageManager = MessageManager.getMessageManager();
			// 如果设定了message key
			if (StringUtils.isNotBlank(this.getMessageKey())) {
				// 取得key
				String key = this.getMessageKey();
				// key对应的值取得
				label = messageManager.getMessage(key);

			}
			// 权限验证
			if (this.getIsAuth()) {
				String authUrl = StringUtils.isNotBlank(this.getHref()) ? this
						.getHref() : this.getAuthUrl();
				// 如果当前用户没有该按钮的操作权限
				if (!checkAuthByUrl(authUrl)) {
					// 当前按钮设置成不可用
					visible = TagConst.BTN_CLASS_DISABLE;
				}
			}
			JspWriter out = pageContext.getOut();
			StringBuilder sb = new StringBuilder();
			sb.append("<a ");
			if (TagConst.BTN_CLASS_DISABLE.equals(visible)) {
				sb.append("style='display:none' ");
			} else {
				if (StringUtils.isNotBlank(this.getTarget())) {
					sb.append("target=\"" + this.getTarget() + "\" ");
				}
				if (StringUtils.isNotBlank(this.getTargetType())) {
					sb.append("targetType=\"" + this.getTargetType() + "\" ");
				}
				if (StringUtils.isNotBlank(this.getRel())) {
					sb.append("rel=\"" + this.getRel() + "\" ");
				}
				if (StringUtils.isNotBlank(this.getHref())) {
					sb.append("href=\"" + this.getHref() + "\" ");
				}
				if (StringUtils.isNotBlank(this.getId())) {
					sb.append("id=\"" + this.getId() + "\" ");
				}
				if (StringUtils.isNotBlank(this.getDwzClass())) {
					sb.append("class=\"" + this.getDwzClass() + "\" ");
				}
				if (StringUtils.isNotBlank(this.getPostType())) {
					sb.append("postType=\"" + this.getPostType() + "\" ");
				}
				if (StringUtils.isNotBlank(this.getOnclick())) {
					sb.append("onclick=\"" + this.getOnclick() + "\"  ");
				}
				if (StringUtils.isNotBlank(this.getAltKey())) {
					sb.append("title=\""
							+ messageManager.getMessage(this.getAltKey())
							+ "\" ");
				}
				if (StringUtils.isNotBlank(getLookupGroup())) {
					sb.append("lookupGroup=" + getLookupGroup() + " ");
				}
				if (StringUtils.isNotBlank(getLookupPk())) {
					sb.append("lookupPk=\"" + getLookupPk() + "\" ");
				}
				if (StringUtils.isNotBlank(this.getStyle())) {
					sb.append("style=\"" + this.getStyle() + "\" ");
				}
			}
			sb.append(">");
			sb.append("<span>" + label + "</span>");
			sb.append("</a>");
			out.println(sb.toString());
		} catch (IOException e) {
			e.printStackTrace();
			throw new JspException("Failed to insert Button");

		}

		return EVAL_PAGE;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getAuthUrl() {
		return authUrl;
	}

	public void setAuthUrl(String authUrl) {
		this.authUrl = authUrl;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getMessageKey() {
		return messageKey;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public String getOnclick() {
		return onclick;
	}

	public void setOnclick(String onclick) {
		this.onclick = onclick;
	}

	public String getLookupGroup() {
		return lookupGroup;
	}

	public void setLookupGroup(String lookupGroup) {
		this.lookupGroup = lookupGroup;
	}

	public String getLookupPk() {
		return lookupPk;
	}

	public void setLookupPk(String lookupPk) {
		this.lookupPk = lookupPk;
	}

	public String getAltKey() {
		return altKey;
	}

	public void setAltKey(String altKey) {
		this.altKey = altKey;
	}

	public String getStyle() {
		return style;
	}

	public void setStyle(String style) {
		this.style = style;
	}

	public String getTargetType() {
		return targetType;
	}

	public void setTargetType(String targetType) {
		this.targetType = targetType;
	}

	public String getPostType() {
		return postType;
	}

	public void setPostType(String postType) {
		this.postType = postType;
	}

}
