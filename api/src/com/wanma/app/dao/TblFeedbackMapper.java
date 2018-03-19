package com.wanma.app.dao;
import java.util.List;
import java.util.Map;

import com.wanma.model.TblFeedback;
import com.wanma.page.Page;

/**
 * 数据访问接口
 *
 */
public interface TblFeedbackMapper {    
    public final static String PREFIX = TblFeedbackMapper.class.getName();
    
	public TblFeedback get(java.lang.Integer pkFeedback);
	
	public <K, V> Map<K, V> findOne(java.lang.Integer pkFeedback);
	
	public <T, K, V> List<T> find(Map<K, V> params);
	
	public int insert(TblFeedback tblFeedback);
	
	public int update(TblFeedback tblFeedback);
	
	public int delete(java.lang.Integer pkFeedback );

	public <E, K, V> Page<E> page(Map<K, V> params, int current, int pagesize);
	
	public List<Map<String, Object>> getMyFeedbackList(Map<String, Object> params);

	public Map<String, Object> getMyFeedbackContent(int feedbackId);

}


