package com.wanma.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.base.common.WanmaConstants;
import com.base.util.JudgeNullUtils;
import com.base.util.MultiFileUtil;
import com.base.util.ObjectUtil;
import com.wanma.dao.TblCompanyMapper;
import com.wanma.model.TblCompany;
import com.wanma.service.CompanyManagerService;

@Service
public class CompanyManagerServiceImpl implements CompanyManagerService {

	@Autowired
	private TblCompanyMapper tblCompanyMapper;

	@Override
	public List<TblCompany> getCompanyList(TblCompany tblCompany) {
		return tblCompanyMapper.find(tblCompany);
	}

	@Override
	public void addCompany(TblCompany tblCompany,
			MultipartFile[] IdUnitCardImage, MultipartFile[] LicenseImage,
			MultipartFile[] AffairsImage, MultipartFile[] AccreditImage) {
		// TODO Auto-generated method stub
		tblCompany.setCpyAuthorizedcard("");
		tblCompany.setCpyBusinesslicence("");
		tblCompany.setCpyTorontohospital("");
		tblCompany.setCpyAuthorization("");
		tblCompany.setCpyCreatedate(new Date());
		tblCompany.setCpyUpdatedate(new Date());
		tblCompany.setCpyPictype("1");
		tblCompanyMapper.insert(tblCompany);

		// 身份证
		MultiFileUtil.saveFile(IdUnitCardImage,
				WanmaConstants.MULTI_UNITTYPE_ID_CARD_IMAGE,
				JudgeNullUtils.nvlStr(tblCompany.getPkCompanyid()));
		// 营业执照
		MultiFileUtil.saveFile(LicenseImage,
				WanmaConstants.MULTI_TYPE_LICENSE_IMAGE,
				JudgeNullUtils.nvlStr(tblCompany.getPkCompanyid()));
		// 税务登记证
		MultiFileUtil.saveFile(AffairsImage,
				WanmaConstants.MULTI_TYPE_AFFAIRS_IMAGE,
				JudgeNullUtils.nvlStr(tblCompany.getPkCompanyid()));
		// 授权证明
		MultiFileUtil.saveFile(AccreditImage,
				WanmaConstants.MULTI_TYPE_ACCREDIT_IMAGE,
				JudgeNullUtils.nvlStr(tblCompany.getPkCompanyid()));

	}

	@Override
	public TblCompany getCompanyById(int pkCompanyid) {
		// TODO Auto-generated method stub
		return tblCompanyMapper.get(pkCompanyid);
	}

	@Override
	public int countByCompanyName(String companyName) {
		return tblCompanyMapper.countByCompanyName(companyName);
	}

	@Override
	public void modifyCompany(TblCompany tblCompany,
			MultipartFile[] IdUnitCardImage, MultipartFile[] LicenseImage,
			MultipartFile[] AffairsImage, MultipartFile[] AccreditImage) {
		// TODO Auto-generated method stub
		TblCompany tblCompany1 = tblCompanyMapper.get(tblCompany
				.getPkCompanyid());

		tblCompany1.setCpyCompanyname(tblCompany.getCpyCompanyname());
		tblCompany1.setCpyCompanyaddress(tblCompany.getCpyCompanyaddress());
		tblCompany1.setCpyCompanyemail(tblCompany.getCpyCompanyemail());
		tblCompany1.setCpyPostcode(tblCompany.getCpyPostcode());
		tblCompany1.setCpyScopebusiness(tblCompany.getCpyScopebusiness());
		tblCompany1.setCpyAuthorizedname(tblCompany.getCpyAuthorizedname());
		tblCompany1.setCpyAuthorizedphone(tblCompany.getCpyAuthorizedphone());
		tblCompany1.setCpyOrganizationcode(tblCompany.getCpyOrganizationcode());
		tblCompany1.setCpyMailingaddress(tblCompany.getCpyMailingaddress());

		tblCompany1.setCpyAuthorizedcard("");
		tblCompany1.setCpyBusinesslicence("");
		tblCompany1.setCpyTorontohospital("");
		tblCompany1.setCpyAuthorization("");
		tblCompany1.setCpyUpdatedate(new Date());
		tblCompany1.setCpyPictype("1");
		tblCompanyMapper.update(tblCompany1);

		if (ObjectUtil.isNotEmpty(IdUnitCardImage)) {
			if (MultiFileUtil.hasFiles(IdUnitCardImage)) {
				MultiFileUtil.delelteFile(
						WanmaConstants.MULTI_UNITTYPE_ID_CARD_IMAGE,
						tblCompany.getPkCompanyid() + "");
				// 身份证
				MultiFileUtil.saveFile(IdUnitCardImage,
						WanmaConstants.MULTI_UNITTYPE_ID_CARD_IMAGE,
						JudgeNullUtils.nvlStr(tblCompany.getPkCompanyid()));
			}
		}
		if (ObjectUtil.isNotEmpty(LicenseImage)) {
			if (MultiFileUtil.hasFiles(LicenseImage)) {
				MultiFileUtil.delelteFile(
						WanmaConstants.MULTI_TYPE_LICENSE_IMAGE,
						tblCompany.getPkCompanyid() + "");
				// 营业执照
				MultiFileUtil.saveFile(LicenseImage,
						WanmaConstants.MULTI_TYPE_LICENSE_IMAGE,
						JudgeNullUtils.nvlStr(tblCompany.getPkCompanyid()));
			}
		}
		if (ObjectUtil.isNotEmpty(AffairsImage)) {
			if (MultiFileUtil.hasFiles(AffairsImage)) {
				MultiFileUtil.delelteFile(
						WanmaConstants.MULTI_TYPE_AFFAIRS_IMAGE,
						tblCompany.getPkCompanyid() + "");

				// 税务登记证
				MultiFileUtil.saveFile(AffairsImage,
						WanmaConstants.MULTI_TYPE_AFFAIRS_IMAGE,
						JudgeNullUtils.nvlStr(tblCompany.getPkCompanyid()));
			}
		}
		if (ObjectUtil.isNotEmpty(AccreditImage)) {
			if (MultiFileUtil.hasFiles(AccreditImage)) {
				MultiFileUtil.delelteFile(
						WanmaConstants.MULTI_TYPE_ACCREDIT_IMAGE,
						tblCompany.getPkCompanyid() + "");
				// 授权证明
				MultiFileUtil.saveFile(AccreditImage,
						WanmaConstants.MULTI_TYPE_ACCREDIT_IMAGE,
						JudgeNullUtils.nvlStr(tblCompany.getPkCompanyid()));
			}
		}
	}

	@Override
	public void deleteCompany(String pkCompanyid) {
		tblCompanyMapper.delete(JudgeNullUtils.nvlInteger(pkCompanyid));
		// if(pkCompanyid.lastIndexOf(",")>0){
		// String gateIds[]=pkCompanyid.split(",");
		// for (int i = 0; i < gateIds.length; i++) {
		// tblCompanyMapper.delete(JudgeNullUtils.nvlInteget(gateIds[i]));
		// }
		// }else{
		// tblCompanyMapper.delete(JudgeNullUtils.nvlInteget(pkCompanyid));
		// }
	}

	@Override
	public long getCompanyCount(TblCompany tblCompany) {
		// TODO Auto-generated method stub
		return tblCompanyMapper.findCount(tblCompany);
	}

	@Override
	public List<TblCompany> getUnSelectCompanyList(TblCompany company) {
		return tblCompanyMapper.getUnSelectCompanyList(company);
	}

	@Override
	public void modifyCompanyFlag(TblCompany tblCompany) {
		tblCompanyMapper.updateFlag(tblCompany);
	}

	@Override
	public void deleteFlag(String ids) {
		tblCompanyMapper.deleteFlag(makeIdsMap(ids));
	}

	private Map<String, String[]> makeIdsMap(String ids) {
		Map<String, String[]> idsMap = new HashMap<String, String[]>();
		idsMap.put("ids", ids.split(","));
		return idsMap;
	}

	@Override
	public boolean isFlagBeUsed(String ids) {
		return tblCompanyMapper.getUsedCompanyFlagCount(makeIdsMap(ids)) > 0;
	}

	@Override
	public List<TblCompany> findCompanyFlagList(TblCompany tblCompany) {
		return tblCompanyMapper.findCompanyFlagList(tblCompany);
	}

	@Override
	public long findCompanyFlagListCount(TblCompany tblCompany) {
		return tblCompanyMapper.findCompanyFlagListCount(tblCompany);
	}

	@Override
	public boolean checkCpyCompanyNumber(TblCompany tblCompany) {
		return tblCompanyMapper
				.findCompanyFlagListCountByCompanyNumber(tblCompany) == 0;
	}

}
