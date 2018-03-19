package com.bluemobi.product.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bluemobi.product.utils.StringUtil;

public class ContentLimitTag extends SimpleTagSupport {
	private Object value;
	private Integer length;
	// 是否忽略HTML 默认是忽�?
	private boolean escapeHtml = true;

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public Integer getLength() {
		return length;
	}

	public void setLength(Integer length) {
		this.length = length;
	}

	/**
	 * 获得是否忽略HTML
	 * 
	 * @return 是否忽略HTML true：忽�?false：不忽略
	 */
	public boolean isEscapeHtml() {
		return escapeHtml;
	}

	/**
	 * 设置是否忽略HTML
	 * 
	 * @param escapeHtml
	 *            是否忽略HTML true：忽�?false：不忽略
	 */
	public void setEscapeHtml(boolean escapeHtml) {
		this.escapeHtml = escapeHtml;
	}

	@Override
	public void doTag() throws JspException, IOException {
		if (getValue() == null) {
			getJspContext().getOut().print("");
			return;
		}
		String str = StringUtil.htmlEscape(getValue().toString());
		if (str.length() > getLength()) {
			str = str.substring(0, getLength());
			StringBuffer sb = new StringBuffer(str);
			StringBuffer sb2 = new StringBuffer(str);
			int sum = 0;
			for (int i = 0; i < sb.length(); i++) {
				char ch = sb.charAt(i);
				if (ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z') {
					sb2.insert(i + 1 + sum, ' ');
					sum++;
				}
			}
			str = sb2.append("...").toString();
		}

		if (escapeHtml) {
			getJspContext().getOut().print(str);
		} else {
			getJspContext().getOut().print(str);
		}

	}

}
