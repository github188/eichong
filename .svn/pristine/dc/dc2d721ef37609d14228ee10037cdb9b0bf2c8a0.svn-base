package com.wanma.web.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;



public class URLFilter implements Filter { 
    public void doFilter(ServletRequest servletRequest, ServletResponse     
                              servletResponse, FilterChain filterChain) throws  
                                        IOException,ServletException 
    { 
       HttpServletRequest request = (HttpServletRequest) servletRequest; 
       HttpServletResponse response = (HttpServletResponse) servletResponse; 
       String realPath 
                    =request.getSession().getServletContext().getRealPath("/"); 

       String fileName = realPath + "WEB-INF/urlRewrite.xml"; 
       String uri = request.getServletPath(); 
       //uri=new String(uri.getBytes("ISO-8859-1"),"utf-8");
       String rewriteUrl = getRewriteUrl(uri, fileName); 
       rewriteUrl=new String(rewriteUrl.getBytes("utf-8"),"ISO-8859-1");
       if (null != rewriteUrl) { 
    	   response.sendRedirect(rewriteUrl);
           //request.getRequestDispatcher(rewriteUrl).forward(request, response); 
           return; 
       } 
       filterChain.doFilter(servletRequest, servletResponse); 

    } 

    private String getRewriteUrl(String url, String fileName) { 
       DocumentBuilderFactory f = DocumentBuilderFactory.newInstance(); 
       try { 
           DocumentBuilder builder = f.newDocumentBuilder(); 
           Document document = builder.parse(fileName); 
           NodeList list = document.getElementsByTagName("rule"); 
           for (int i = 0; i < list.getLength(); i++) { 
              Element elemnt = (Element) list.item(i); 
              NodeList list2 = elemnt.getElementsByTagName("from"); 
              Element element = (Element) list2.item(0); 
              String fromValue = element.getFirstChild().getNodeValue(); 
              NodeList list3 = elemnt.getElementsByTagName("to"); 
              Element element2 = (Element) list3.item(0); 
              String type = element2.getAttribute("type"); 
              String toValue = element2.getFirstChild().getNodeValue(); 
              String rewriteUrl = url.replaceAll(fromValue, toValue); 
              if (url != null && !"".equals(url.trim()) && 
                                                    !url.equals(rewriteUrl)) { 
                  return rewriteUrl; 
              } 
           } 
       } catch (Exception ex) { 
           ex.printStackTrace(); 
       } 
       return null; 
    }

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	} 
	
	
	public static void main(String[] args) {
		String s="/reurl_act_appShare_ip_10.9.3.231:8080_web_120.134564|30.279709|赛特康聚电1498号充电桩|杭州市西湖区三浜路469号|2|1.shtml";
		String fileName =  "D:\\program\\workspace\\webeichong\\webroot\\WEB-INF\\urlrewrite.xml"; 
		URLFilter filter=new URLFilter();
		String rewriteUrl = filter.getRewriteUrl(s, fileName); 
		String fromValue="^/reurl_act_(.+)_ip_(.+)_(.+)_(.+)\\.shtml$";
		String toValue="/$1.jsp?$2=$3";
		System.out.println(s.replaceAll(fromValue, "http://$2/$1.jsp?$3=$4"));
		
	}
} 
