package com.bluemobi.product.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class QueryUtil {

	
	public static Map<String, Object> getParams(Map<String, Object> params)
	{
		
		
		if (params != null && params.size() > 0)
		{
			Map<String, Object> param = new HashMap<String, Object>();
			
			for (Entry<String, Object> entry : params.entrySet()) 
			{
				if ( !(entry.getValue() == null || entry.getValue().toString().length() == 0 ) )
				{
					param.put(entry.getKey(), entry.getValue());
				}
			}
			
			return param;
		}
		return params;
	}
	
}
