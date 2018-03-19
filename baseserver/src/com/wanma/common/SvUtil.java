package com.wanma.common;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.wanma.dubbox.model.TblPowerStation;
import com.wanma.dubbox.model.common.BasicModel;

public class SvUtil {
	
	public static <T extends BasicModel> void setCasheDeleteIds(T t1,List<T> t) {
		String ids = "";
		for(BasicModel model:t)
			ids += model.getId()+",";
		ids=StringUtils.removeEnd(ids, ",");
		((BasicModel)t1).setId(ids);
	}

	public static <T extends BasicModel> void initDeleteIds(T t) throws Exception {
		if (t.getPkIds() == null && t.getId() == null) {
			throw new Exception("缺少过滤字段值，请至少指定一个字段！");
		} else if (t.getId() != null) {
			t.setId(null);
			t.setPkIds(new String[] { t.getId() });
		}
	}


}
