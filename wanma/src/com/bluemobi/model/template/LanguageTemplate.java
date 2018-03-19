package com.bluemobi.model.template;

/**
 * 国际化语言配置模板
 * @author xujianxin
 * @time Jun 20, 2013 1:22:50 PM
 */
public class LanguageTemplate {
	public static final long serialVersionUID = 1L;

	private String key;
	private String language;

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
}
