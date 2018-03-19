package com.wanma.web.controller;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import com.wanma.model.WebList;
import com.wanma.web.dao.WebListMapper;

public class TestDao {

  private static SqlSessionFactory sqlSessionFactory;  

	@Test
	public void test() {
		
		    SqlSession sqlSession = sqlSessionFactory.openSession();  
		        try  
		        {  
		            WebListMapper mapper = sqlSession.getMapper(WebListMapper.class);  
	                List<WebList>  list = mapper.findByType("2");
	                list.toString();
	                System.out.println(list);
		       } finally  
		        {  
		            sqlSession.close();  
		        } 

	}

}
