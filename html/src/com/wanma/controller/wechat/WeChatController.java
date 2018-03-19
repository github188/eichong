package com.wanma.controller.wechat;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.wanma.model.Message;
import com.wanma.service.WeChatService;
import com.wanma.support.utils.SignUtil;
import com.wanma.support.utils.Tools;


@Controller
@RequestMapping("/WeChat")
public class WeChatController {

	@Resource
	private WeChatService weChatService;

	@RequestMapping(method = RequestMethod.GET)
	public void get(HttpServletRequest request, HttpServletResponse response) {
		// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		// 随机字符串
		String echostr = request.getParameter("echostr");

		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {
				out.print(echostr);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
			out = null;
		}
	}

	@RequestMapping(method = RequestMethod.POST)
	public void post(HttpServletRequest request, HttpServletResponse response) {
		// 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
		String signature = request.getParameter("signature");
		// 时间戳
		String timestamp = request.getParameter("timestamp");
		// 随机数
		String nonce = request.getParameter("nonce");
		PrintWriter out = null;
		Message postData = null;
		String BakXml = "success";

		try { 
			// 通过检验signature对请求进行校验，若校验成功则原样返回echostr，否则接入失败
			if (SignUtil.checkSignature(signature, timestamp, nonce)) {

				ServletInputStream in = request.getInputStream();
				String xmlMsg = Tools.inputStream2String(in);

				XStream xs = new XStream(new DomDriver());
				xs.alias("xml", Message.class);
				postData = (Message) xs.fromXML(xmlMsg);
                //事件方法处理
				if ("event".equals(postData.getMsgType())) {

					BakXml = weChatService.toEvent(postData);

				}
				//消息方法处理
				else if("text".equals(postData.getMsgType())){
					
					BakXml = weChatService.toText(postData);
				}
				out = response.getWriter();
				// 返回文本消息
				// out.print("<xml><ToUserName><![CDATA[oDM6h0ftcMz_ZwKCTZO0J2WsB3pM]]></ToUserName><FromUserName><![CDATA[gh_c03472f7a1c8]]></FromUserName><CreateTime>1491535643</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[关于]]></Content><MsgId>6406096807945649155</MsgId></xml>");
				// 返回图文消息
				// out.print("<xml><ToUserName><![CDATA[oDM6h0ftcMz_ZwKCTZO0J2WsB3pM]]></ToUserName><FromUserName><![CDATA[gh_c03472f7a1c8]]></FromUserName><CreateTime>1491535643</CreateTime><MsgType><![CDATA[news]]></MsgType><ArticleCount>1</ArticleCount><Articles><item><Title><![CDATA[简单操作 快乐充电]]></Title><Description><![CDATA[爱充网络，努力为您提供更好的充电服务。]]></Description><PicUrl><![CDATA[http://odh44ol8b.bkt.clouddn.com/cbd.jpg]]></PicUrl><Url><![CDATA[http://www.baidu.com/]]></Url></item></Articles></xml>");

				out.print(BakXml);
			}
		} catch (IOException e) {
			
			e.printStackTrace();
			
		} finally {
			out.close();
			out = null;
		}
	}

}
