package com.wanma.dao;

import java.util.HashMap;




public interface PileFilterMapper {

	long checkPileIsOk(HashMap<String, String> map);
	long checkOk(HashMap<String, String> map);

	
	
}
