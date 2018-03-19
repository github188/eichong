package com.wanma.ims.mapper;


import java.util.List;
import java.util.Map;

import com.wanma.ims.common.domain.AppLoginRecord;
import com.wanma.ims.common.domain.FinAccountDO;
import com.wanma.ims.common.domain.UserCardDO;

/**
 * app登陆记录
 * @author mb
 */
public interface AppLoginRecordMapper {

	long getAppLoginRecordCount(AppLoginRecord appLoginRecord);

	List<AppLoginRecord> getAppLoginRecordList(AppLoginRecord appLoginRecord);
	
	
}