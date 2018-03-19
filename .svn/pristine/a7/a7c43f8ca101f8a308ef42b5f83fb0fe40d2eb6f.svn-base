package com.bluemobi.product.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.bluemobi.product.utils.StringUtil;

public class JsEscapTag extends SimpleTagSupport {
	private Object value;
	
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		if(getValue()==null){
			getJspContext().getOut().print("");
			return;
		}
		String plainText = StringUtil.javascriptEscape(value.toString());
		getJspContext().getOut().print(plainText);
	}

}
