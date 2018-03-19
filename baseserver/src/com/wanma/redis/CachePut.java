package com.wanma.redis;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
public @interface CachePut {

	@SuppressWarnings("rawtypes")
	public Class clazz();// 对象类名

	public String key(); // 缓存key

	public String value();// 缓存对象属性名

	public int expire() default 0; // 缓存多少秒,默认无限期
}
