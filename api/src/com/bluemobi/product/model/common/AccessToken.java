package com.bluemobi.product.model.common;

public class AccessToken {
	private String principal;   //主角
	private String role;		//角色
	private long expire;  		//有效时间�?seconds�?
	private String token; 		//令牌
	
	public String getPrincipal() {
		return principal;
	}
	public void setPrincipal(String principal) {
		this.principal = principal;
	}
	public long getExpire() {
		return expire;
	}
	public void setExpire(long expire) {
		this.expire = expire;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
}
