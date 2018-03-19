package com.wanma.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wanma.dao.CmsCommonQuestionsMapper;
import com.wanma.model.CommonQuestions;
import com.wanma.service.CmsCommonQuestionsService;

@Service
public class CmsCommonQuestionsServiceImpl implements CmsCommonQuestionsService{

	/** 常见问题作用Dao */
	@Autowired
	private CmsCommonQuestionsMapper questionsDao;
	
	/**
	 * 取得常见问题表一览
	 * 
	 */
	@Override
	public List<CommonQuestions> getQuestionList() {
		// 常见问题表一览
		List<CommonQuestions> listQuestion;
		
		//取得常见问题表一览
		listQuestion = questionsDao.getQuestionList();
		
		//返回常见问题表一览
		return listQuestion;
	}
	
	/**
	 * 查询咨询个数
	 * 
	 */
	public long searchQuestionCount(CommonQuestions commonQuestions) {
		// 咨询个数
		long dataCount;

		// 取得咨询个数
		dataCount = questionsDao.searchQuestionCount(commonQuestions);

		// 返回咨询个数
		return dataCount;

	}
	

	/**
	 * 查询咨询表一览
	 * 
	 */
	@Override
	public List<CommonQuestions> searchQuestionList(CommonQuestions commonQuestions) {
		// 常见问题表一览
		List<CommonQuestions> listQuestion;
				
		//取得常见问题表一览
		listQuestion = questionsDao.searchQuestionList(commonQuestions);
				
		//返回常见问题表一览
		return listQuestion;
	}

	/**
	 * 添加常见问题
	  * 
	 * @param consult
	 * @return
	 */
	@Override
	public void addQuestion(CommonQuestions commonQuestions) {
		// TODO Auto-generated method stub
		questionsDao.insertQuestion(commonQuestions);
	}
	
	/**
	 * 删除常见问题
	 * 
	 * @param consult
	 * @return
	 */
	@Override
	public void deleteQuestion(CommonQuestions commonQuestions) {
		// TODO Auto-generated method stub
		questionsDao.removeQuestion(commonQuestions);
	}
}
