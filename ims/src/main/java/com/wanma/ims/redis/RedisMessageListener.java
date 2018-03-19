package com.wanma.ims.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;

public class RedisMessageListener implements MessageListener {

	@SuppressWarnings("rawtypes")
	@Autowired
	RedisTemplate redisTemplate;

	
	@Override
	public void onMessage(Message message, byte[] pattern) {
		byte[] body = message.getBody();// 请使用valueSerializer
		byte[] channel = message.getChannel();
		// 请参考配置文件，本例中key，value的序列化方式均为string。
		// 其中key必须为stringSerializer。和redisTemplate.convertAndSend对应
		//redisTemplate.convertAndSend(topic, msg);
		String msg = (String) redisTemplate.getValueSerializer()
				.deserialize(body);
		String topic = (String) redisTemplate.getStringSerializer()
				.deserialize(channel);
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("topic:"+topic);
		System.out.println("msg:"+msg);
	}
}
