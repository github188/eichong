package com.wanma.app.util;

public class PayConfig {
	//支付宝商户PID 合作身份者id，以2088开头的16位纯数字
	public static final String ALI_PARTNER = "2088911121467992";
	//支付宝商户收款账号
	public static final String ALI_SELLER = "eichongyf@wanmagroup.com";
	//支付宝商户私钥，pkcs8格式  即rsa_private_key.pem
	public static final String ALI_RSA_PRIVATE = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAL/QZvV4wS1IRM6X+Eqf2ZBOtu2NqNeESgyjGi1d7wTl0jPXuuY+TMz3oM/FT3QLoRVrN5Ir7YnBVoZBo1uK81NANUDD6PMfxHAVmIS6MmCqXL9DXkf6//0+qVo0+p8DovHvy02PnPNYK+btFgakatQVIncEFNrcHwVo4opoPBydAgMBAAECgYAJkVahZe2U2ab8+x2ac1DB3vtG7CsA3Qtoh/Y1TxsuDlym01ryQydHjg6Kbpgg/LeDPv7dnq1RksBDICEK3up2JWXQpL5lRNwWw2GSzFettcfv2MmonZWVI3yVlxHrSGqQdtysA6225PEoyIAOzqVuqiiHOqCfScpxyvF9Zw63YQJBAPSX5T9XH7ti8gY/X66icbdEnvMZttMU2CzfgUJgSyNdBMrBh2XIdKrLk1xwL7/qXwThdLgWurIijbfYLUtiMEUCQQDIwmVDVUo/s/venLah9gwfrltNuOf7+GawZAlfBjaPs+KKDJpcIJGTw6WVb+/lqF8rGYJ6/b2drE8smACBeNx5AkBAyFJxulOvP/+2Y+alzGSuE8C9xpvDwIuZJwGHYRXFogd45FVGQheUfSLkotoizKKqGkTd1TdRoxFCFkG466K5AkBhTXbvD6ORBJcK6kHKdgqiQEuYfApUIa0DyTiRid1gAPvHlRwrks18JNNYfoxzbwvRQ+ft3JXOmfk1z+ZO2llhAkAGvTNZxMldVyelYj/kXH0qrEx4TnSq9DYb+DAsl5L3d20zOIAYw9DX+tmnccxY4P9JBcxwqFzhghwMTN2HHcp0";
	
	//微信初始化
	public static String WX_APP_ID = "wx6f19e4001b2c467a";//对应的微信公众号：爱充网
	public static String WX_APP_SECRET = "fad1e3cfaa3b823bde4b84afcc332944";//应用对应的凭证
	//微信应用对应的密钥
	//public static String WX_APP_KEY = "L8LrMqqeGRxST5reouB0K66CaYAWpqhAVsq7ggKkxHCOastWksvuX1uvmvQclxaHoYd3ElNBrNO2DHnnzgfVG9Qs473M3DTOZug5er46FhuGofumV8H2FVR9qkjSlC5K";
	public static String WX_PARTNER = "1235355502";//财付通商户号
	public static String WX_PARTNER_KEY = "1234563210qweasdzxcopoipoipoiooo";//商户号对应的密钥
	public static String WX_TOKENURL = "https://api.weixin.qq.com/cgi-bin/token";//获取access_token对应的url
	public static String WX_GRANT_TYPE = "client_credential";//常量固定值 
	public static String WX_EXPIRE_ERRCODE = "42001";//access_token失效后请求返回的errcode
	public static String WX_FAIL_ERRCODE = "40001";//重复获取导致上一次获取的access_token失效,返回错误码
	//public static String WX_GATEURL = "https://api.weixin.qq.com/pay/genprepay?access_token=";//获取预支付id的接口url
	public static String WX_GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	public static String WX_ACCESS_TOKEN = "access_token";//access_token常量值
	public static String WX_ERRORCODE = "errcode";//用来判断access_token是否失效的值
	//public static String WX_SIGN_METHOD = "sha1";//签名算法常量值
	//package常量值
	//public static String WX_packageValue = "bank_type=WX&body=%B2%E2%CA%D4&fee_type=1&input_charset=GBK&notify_url=http%3A%2F%2F127.0.0.1%3A8180%2Ftenpay_api_b2c%2FpayNotifyUrl.jsp&out_trade_no=2051571832&partner=1900000109&sign=10DA99BCB3F63EF23E4981B331B0A3EF&spbill_create_ip=127.0.0.1&time_expire=20131222091010&total_fee=1";
	//public static String WX_traceid = "testtraceid001";//测试用户id
	
	public static String ECH_PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC3nIh4nA2/M99ByXHMSJHRzTcl9nl213kjWWZrYA6/azJeg0Mp7Y1rB97zsZEl3e2LWUJ7naRh1IIaPGBWLdOSsfzRyQ9Hlnz/XfYgGm4gaj9KP3z2kR6l1R59U8vP+5ElcGfwSgVBLNGqb3RQ5Etg2PSzqi8+L7ryCHLnTXejjQIDAQAB";
	public static String ECH_PRIVATE_KEY = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALeciHicDb8z30HJccxIkdHNNyX2eXbXeSNZZmtgDr9rMl6DQyntjWsH3vOxkSXd7YtZQnudpGHUgho8YFYt05Kx/NHJD0eWfP9d9iAabiBqP0o/fPaRHqXVHn1Ty8/7kSVwZ/BKBUEs0apvdFDkS2DY9LOqLz4vuvIIcudNd6ONAgMBAAECgYA3zlTQS6YF+i8eIKr8ywW+/Z+IY0xxqXYBH3f4pL52wY8SmNS+FpyH7mF+MfcKylQbqWqChdxje6J7dORW7SDDjM5ll2ik/3rhJRX2hSjwP5Qw+raT73/QPDcJiLoARxzF3slpD6ew0XRFe5gHmIG6DluvX2P/KlVTF34ZqpprRQJBAP+cU+/iR4Bk5vixzmYJchODeXZ9uKGKyMJdJUbWMD5zs/ft6VFrmI66TMUqo74rjx92QGBVHEcJEH5N+tKpHJcCQQC35CFKVcZQkpE7Iu9BoaVZO/F+refj79YU1XKhtw9lWQNB0DPCeHf1M4nTmb4BAOCKuomCOrIUOzZquzn38jF7AkAXRdWCntihwQ4ikJXZRnzjd/IELtgzpR44TCDLvU6xMPmu1jOLHT/Lpvy8avBzrSWf2LVtz/H+Kb4dsd3ndkxLAkBiq4EFXuLrRsmwnn0cjTyoldZtr6iWkJEhWqqIhS44pjtuK+Yp+fP72KyiooLmDO9u4Mp52z7ERkVbnZxsI0NTAkEAv6NYzWiRu/G9cy57lcRneS3FfrRGGfvMzdhsM51J9N4ggb6bBQdakAO95vCbaXa3gHqmRufc95DtbM8lRoxXxw==";
}
