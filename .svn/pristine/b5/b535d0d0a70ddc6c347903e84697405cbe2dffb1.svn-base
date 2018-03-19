package com.bluemobi.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bluemobi.product.model.CodeDetail;
import com.bluemobi.product.service.GroupDetailService;
import com.bluemobi.product.utils.JsonList;

@Controller
@RequestMapping("/codegroup")
public class CodeGroupInterface {
	@Autowired
	private GroupDetailService groupDetailService;

	@RequestMapping(value = "/codeList")
	@ResponseBody
	public String categoryList(@RequestParam("codeGroupId") String groupId) {
		String reJsonString = "";

		List<CodeDetail> codeList = groupDetailService
				.getCodeDetailList(groupId);

		if (codeList != null && codeList.size() > 0) {
			reJsonString = new JsonList(codeList).toString();
		}

		return reJsonString;
	}
}
