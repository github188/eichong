package com.pub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.activmq.QueueSender;
import com.base.activmq.TopicSender;

/**
 * 
 * @author liang
 * @description controller测试
 */
@Controller
@RequestMapping("/activemq")
public class ActivemqController {
	
//	@Resource 
	QueueSender queueSender;
//	@Resource 
	TopicSender topicSender;
	
	/**
	 * 发送消息到队列
	 * Queue队列：仅有一个订阅者会收到消息，消息一旦被处理就不会存在队列中
	 * @param message
	 * @return String
	 */
//	@ResponseBody
//	@RequestMapping("queueSender")
//	public String queueSender(@RequestParam("message")String message){
//		String opt="";
//		long t1=System.currentTimeMillis();
//		try {
//			for(int i=0;i<100;i++){
//				TblUser user=new TblUser();
//				user.setId(i+"");
//				user.setUsAcc("queue____wbc"+i);
//				queueSender.send("test.queue", user);
//			}
//			opt = "suc";
//		} catch (Exception e) {
//			opt = e.getCause().toString();
//		}
//		long t2=System.currentTimeMillis();
//		System.out.println(t2-t1);
//		return opt;
//	}
//	
//	/**
//	 * 发送消息到主题
//	 * Topic主题 ：放入一个消息，所有订阅者都会收到 
//	 * 这个是主题目的地是一对多的
//	 * @param message
//	 * @return String
//	 */
//	@ResponseBody
//	@RequestMapping("topicSender")
//	public String topicSender(@RequestParam("message")String message){
//		String opt = "";
//		try {
//			for(int i=0;i<100;i++){
//				TblUser user=new TblUser();
//				user.setId(i+"");
//				user.setUsAcc("topic____wbc"+i);
//				topicSender.send("test.topic", user);
//			}
//			topicSender.send("test.topic", message);
//			opt = "suc";
//		} catch (Exception e) {
//			opt = e.getCause().toString();
//		}
//		return opt;
//	}
	
}
