package com.wanma.ims.controller.company;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wanma.ims.common.domain.PartnerDO;
import com.wanma.ims.common.domain.base.Pager;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.controller.BaseController;
import com.wanma.ims.controller.result.JsonException;
import com.wanma.ims.controller.result.JsonResult;
import com.wanma.ims.redis.RedisUtil;
import com.wanma.ims.service.PartnerService;
import com.wanma.ims.util.RandomNumer;

/**
 * 公司主页-密钥
 */
@Controller
@RequestMapping("/manage/company/partner")
public class PartnerController extends BaseController {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PartnerService partnerService;

	@Autowired
	private RedisUtil redisService;

	/**
	 * <p>Description: 获取公司密钥</p>
	 * @param
	 * @author bingo
	 * @date 2017-10-17
	 */
	@RequestMapping(value = "/getPartnerList")
	@ResponseBody
	public void getPartnerList(@ModelAttribute("pager") Pager pager,
									  @ModelAttribute PartnerDO partner, Model model, HttpServletRequest request){
		JsonResult batchResult = new JsonResult();
		partner.setValid(1);
		Long total = partnerService.getPartnerListCount(partner);
		if (total <= pager.getOffset()) {
			pager.setPageNo(1L);
		}
		pager.setTotal(total);
		partner.setPager(pager);

		List<PartnerDO> partnerList = partnerService.getPartnerList(partner);
		if (partnerList == null) {
			partnerList = new ArrayList<PartnerDO>();
		}
		batchResult.setDataObject(partnerList);
		batchResult.setPager(pager);
		responseJson(batchResult);
	}


	 /**
	 * <p>Description: 新增公司密钥</p>
	 * @param
	 * @author bingo
	 * @date 2017-10-17
	 */
	@RequestMapping(value = "/addPartner")
	@ResponseBody
	public void addPartner(@ModelAttribute PartnerDO partner, HttpServletRequest request) {
		if(partner == null){
			log.error(this.getClass() + ".addPartner() error : 密钥不允许为空");
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, "密钥不允许为空"));
			return;
		}

		//cpy_name
		if(partner.getPartnerName() == null){
			log.error(this.getClass() + ".addPartner() error : 公司名字不允许为空");
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, "公司名字不允许为空"));
			return;
		}

		//cpy_number
		if(partner.getPartnerKey() == null){
			log.error(this.getClass() + ".addPartner() error : 公司标识不允许为空");
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, "公司标识不允许为空"));
			return;
		}

		//trade_type
		if(partner.getPaymod() == null){
			log.error(this.getClass() + ".addPartner() error : 公司付费策略不允许为空");
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, "公司付费策略不允许为空"));
			return;
		}

		try {
			//如果存在无效的密钥，那么置成有效
			List<PartnerDO> partnerList = partnerService.getPartnerList(partner);
			if(partnerList != null && partnerList.size() > 0){
				PartnerDO resultPartnerDO = partnerList.get(0);
				resultPartnerDO.setValid(1);
				partnerService.updatePartnerKeyById(resultPartnerDO);
			}else {
				String partnerKey = partner.getPartnerKey();

				//随机产生10位字符串（包括特殊字符）
				String partnerToken = RandomNumer.getUpperMd5Number(partnerKey, 10);
				partner.setPartnerToken(partnerToken);

				partnerService.addPartner(partner);
				//缓存第三方身份信息
				redisService.strSet("app:org:" + partnerKey, partnerToken + ":" + partner.getPaymod());
			}
			responseJson(new JsonResult());
		} catch (Exception e) {
			log.debug(this.getClass() + ".addPartner() error : ", e);
			responseJson(new JsonException(ResultCodeConstants.ERROR_MSG_ERROR_ADD));
		}
	}

	/**
	 * <p>Description: 删除公司密钥</p>
	 * @param
	 * @author bingo
	 * @date 2017-10-17
	 */
	@RequestMapping(value = "/removePartner")
	@ResponseBody
	public void removePartner(@ModelAttribute PartnerDO partner, Model model, HttpServletRequest request) {
		if(partner == null || partner.getPartnerId() == null){
			log.error(this.getClass() + ".addPartner() error : 公司密钥不允许为空");
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, "公司密钥不允许为空"));
			return;
		}

		try {
			//partnerService.deletePartnerById(partner.getPartnerId());

			//改物理删为逻辑删除
			partner.setValid(0);
			partnerService.updatePartnerKeyById(partner);

			redisService.strRemove("app:org:" + partner.getPartnerKey());

			responseJson(new JsonResult());
		} catch (Exception e) {
			log.error(this.getClass() + ".removePartner() error : ", e);
			responseJson(new JsonResult(false, ResultCodeConstants.FAILED, ResultCodeConstants.ERROR_MSG_ERROR_REMOVE));
		}
	}
}
