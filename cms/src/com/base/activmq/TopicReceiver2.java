package com.base.activmq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.stereotype.Component;

/**
 * 
 * @author liang
 * @description  Topic消息监听器
 * 
 */
@Component
public class TopicReceiver2 implements MessageListener{


	@Override
	public void onMessage(Message message) {
		try {
			System.out.println("TopicReceiver2接收到消息:"+((ObjectMessage)message).getObject());
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
	
}
