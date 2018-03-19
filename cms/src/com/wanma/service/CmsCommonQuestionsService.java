package com.wanma.service;

import java.util.List;

import com.wanma.model.CommonQuestions;

/**
 * 常见问题处理器
 * 
 * @author xiay
 *
 */
public interface CmsCommonQuestionsService {

	/**
	 * 取得常见问题列表
	 * 
	 * @return
	 */
	public List<CommonQuestions> getQuestionList();
	
	/**
	 * 查询常见问题个数
	 * 
	 * @param commonQuestions
	 * @return
	 */
	public long searchQuestionCount(CommonQuestions commonQuestions);
	
	/**
	 * 查询常见问题列表
	 * 
	 * @param consult
	 * @return
	 */
	public List<CommonQuestions> searchQuestionList(CommonQuestions commonQuestions);
	
	/**
	 * 添加常见问题
	  * 
	 * @param consult
	 * @return
	 */
	public void addQuestion(CommonQuestions commonQuestions);
	
	/**
	 * 删除常见问题
	 * 
	 * @param consult
	 * @return
	 */
	public void deleteQuestion(CommonQuestions commonQuestions);
}
