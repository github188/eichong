package com.wanma.ims.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface CacheDelete {

	@SuppressWarnings("rawtypes")
	public Class clazz();// 对象类名

	public String key(); // 缓存key

}
