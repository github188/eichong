package com.wanma.app.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


@Controller
public class BaseController {
	@InitBinder
	protected void initBinder(WebDataBinder binder) {
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				new SimpleDateFormat("yyyy-MM-dd"), true));
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
		
//		binder.registerCustomEditor(Date.class, new CustomDateEditor(
//				new SimpleDateFormat("yyyy-MM-dd"), true));
//		binder.registerCustomEditor(int.class, new CustomNumberEditor(
//				int.class, true));
//		binder.registerCustomEditor(int.class, new LongEditor());
/*		binder.registerCustomEditor(long.class, new CustomNumberEditor(
				long.class, true));*/
//		binder.registerCustomEditor(long.class, new LongEditor());
//		binder.registerCustomEditor(double.class, new DoubleEditor());
//		binder.registerCustomEditor(float.class, new FloatEditor());
	}
}
