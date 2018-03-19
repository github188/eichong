package com.wanma.service;

import javax.servlet.http.HttpServletResponse;


	/**
	 * excel导出接口
	 * 
	 * @author txd
	 *
	 */
	public interface CmsExcelService {
		 public void export(HttpServletResponse response,
				 Object paramsModel, String filename) throws Exception;
	}


