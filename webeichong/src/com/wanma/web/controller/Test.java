package com.wanma.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.stream.events.Comment;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.wanma.model.TblUsermessage;
import com.wanma.web.dao.WebUsermessageMapper;
@Controller
public class Test {
	    
	 private static SqlSessionFactory sqlSessionFactory;  

	@org.junit.Test
	public void getByUserId(){
		SqlSession sqlSession = sqlSessionFactory.openSession(); 
		
		WebUsermessageMapper usermessage = sqlSession.getMapper(WebUsermessageMapper.class);
		Map<String,Object> params = new HashMap();
		params.put("userId", 1);
		params.put("pageNumber", 1);
		params.put("pageNum", 1);
		List<TblUsermessage>  list = usermessage.getByUserId(params);		    
		     for (TblUsermessage comment : list){
		    	 System.out.println(comment); 
		     }		         
		     sqlSession.close();
	}
	

}
