package com.base.redis;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CacheAop {
	private final static Log log = LogFactory.getLog(CacheAop.class);
	@SuppressWarnings("rawtypes")
	@Autowired
	private RedisTemplate redisTemplate;

	@Around("@annotation(com.base.redis.CacheGet)")
	public Object cacheGet(final ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Method method = getMethod(pjp);
		CacheGet cache = method.getAnnotation(CacheGet.class);
		String key = parseKey(cache.key(), method, args);
		String name = cache.clazz().getName();
		HashOperations valueOper = redisTemplate.opsForHash();
		Object value = valueOper.get(name, key); // 从缓存获取数据
		log.info("cacheGet执行,name值：" + name + ",key值：" + key);
		if (value != null) {
			log.info("缓存已存在,直接返回结果");
			return value; // 如果有数据,则直接返回
		} else {
			value = pjp.proceed(); // 跳过缓存,到后端查询数据
			valueOper.put(name, key, value);
			log.info("缓存不存在,设置缓存");
		}
		return value;
	}

	@Around("@annotation(com.base.redis.CachePut)")
	public Object CachePut(final ProceedingJoinPoint pjp) throws Throwable {
		Object value=pjp.proceed();
		Object[] args = pjp.getArgs();
		Method method = getMethod(pjp);
		CachePut cache = method.getAnnotation(CachePut.class);
		String key = parseKey(cache.key(), method, args);
		Object v = parseValue(cache.value(), method, args);
		String name = cache.clazz().getName();
		HashOperations valueOper = redisTemplate.opsForHash();
		valueOper.put(name, key, value);
		log.info("CachePut执行,name值：" + name + ",key值：" + key+ ",value值：" + v);
		return value;
	}

	@Around("@annotation(com.base.redis.CacheDelete)")
	public Object cacheDelete(final ProceedingJoinPoint pjp) throws Throwable {
		Object[] args = pjp.getArgs();
		Method method = getMethod(pjp);
		CacheDelete cache = method.getAnnotation(CacheDelete.class);
		String key = parseKey(cache.key(), method, args);
		String[] array = null;
		if (key.indexOf(",") > 0) {
			array = key.split(",");
		} else {
			array = new String[] { key };
		}
		String name = cache.clazz().getName();
		HashOperations valueOper = redisTemplate.opsForHash();
		Object value = pjp.proceed();
		valueOper.delete(name, array);
		log.info("cacheDelete执行,name值：" + name + ",key值：" + key);
		return value;
	}

	private Method getMethod(ProceedingJoinPoint pjp) {
		return ((MethodSignature) pjp.getSignature()).getMethod();
	}

	private CacheGet getCache(ProceedingJoinPoint pjp) {
		return getMethod(pjp).getAnnotation(CacheGet.class);
	}

	private String parseKey(String key, Method method, Object[] args) {

		// 获取被拦截方法参数名列表(使用Spring支持类库)
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = u.getParameterNames(method);

		// 使用SPEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();
		// SPEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		// 把方法参数放入SPEL上下文中
		for (int i = 0; i < paraNameArr.length; i++) {
			context.setVariable(paraNameArr[i], args[i]);
		}
		return parser.parseExpression(key).getValue(context, String.class);
	}

	private Object parseValue(String key, Method method, Object[] args) {

		// 获取被拦截方法参数名列表(使用Spring支持类库)
		LocalVariableTableParameterNameDiscoverer u = new LocalVariableTableParameterNameDiscoverer();
		String[] paraNameArr = u.getParameterNames(method);

		// 使用SPEL进行key的解析
		ExpressionParser parser = new SpelExpressionParser();
		// SPEL上下文
		StandardEvaluationContext context = new StandardEvaluationContext();
		// 把方法参数放入SPEL上下文中
		for (int i = 0; i < paraNameArr.length; i++) {
			context.setVariable(paraNameArr[i], args[i]);
		}
		return parser.parseExpression(key).getValue(context, Object.class);
	}
}
