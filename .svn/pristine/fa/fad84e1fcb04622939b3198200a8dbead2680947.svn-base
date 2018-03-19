package com.wanma.ims.service.impl;

import com.wanma.ims.common.domain.*;
import com.wanma.ims.common.dto.BaseResultDTO;
import com.wanma.ims.common.dto.BatchResultDTO;
import com.wanma.ims.constants.ResultCodeConstants;
import com.wanma.ims.constants.WanmaConstants;
import com.wanma.ims.mapper.CarVinRelaMapper;
import com.wanma.ims.mapper.CompanyMapper;
import com.wanma.ims.mapper.UserCardMapper;
import com.wanma.ims.mapper.UserVinRelaMapper;
import com.wanma.ims.service.CarVinRelaService;
import com.wanma.ims.util.ExcelImportUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Service
public class CarVinRelaServiceImpl implements CarVinRelaService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private CarVinRelaMapper carVinRelaMapper;
    @Autowired
    private CompanyMapper companyMapper;
    @Autowired
    private UserVinRelaMapper userVinRelaMapper;
    @Autowired
    private UserCardMapper userCardMapper;

    //excel中相同数据集
    StringBuffer repeatLicenseNumber = new StringBuffer();

    //重复数据集
    StringBuffer repeatVinCode = new StringBuffer();

    //错误数据集
    StringBuffer errorData = new StringBuffer();

    @Override
    @Transactional
    public BaseResultDTO addCarVinRela(CarVinRelaDO carVinRela) throws Exception {
        BaseResultDTO baseResultDTO = new BaseResultDTO();
        if (carVinRela == null) {
            log.error(this.getClass() + ".addCarVinRela() error : VIN码信息不能为空！");
            baseResultDTO.setSuccess(false);
            baseResultDTO.setErrorDetail("VIN码信息不能为空！");
            return baseResultDTO;
        }

        if (carVinRela.getCpyNumber() == null) {
            log.error(this.getClass() + ".addCarVinRela() error : 渠道不能为空！");
            baseResultDTO.setSuccess(false);
            baseResultDTO.setErrorDetail("渠道不能为空！");
            return baseResultDTO;
        }

        if (carVinRela.getCvVinCode() == null || "".equals(carVinRela.getCvVinCode())) {
            log.error(this.getClass() + ".addCarVinRela() error : VIN码不能为空！");
            baseResultDTO.setSuccess(false);
            baseResultDTO.setErrorDetail("VIN码不能为空！");
            return baseResultDTO;
        }

        Integer cpyNumber = carVinRela.getCpyNumber();
        CompanyDO company = new CompanyDO();
        company.setCpyNumber(cpyNumber);
        company = companyMapper.selectCompanyByCpyInfo(company);
        if (company == null || company.getCpyId() == null) {
            log.error(this.getClass() + ".addCarVinRela() error : 找不到" + cpyNumber + "对应的公司！");
            baseResultDTO.setSuccess(false);
            baseResultDTO.setErrorDetail("找不到" + cpyNumber + "对应的公司！");
            return baseResultDTO;
        }
        carVinRela.setCpyId(company.getCpyId());
        carVinRela.setIsUsed(0);

        CarVinRelaDO qryCarVinRelaDO = new CarVinRelaDO();
        qryCarVinRelaDO.setCpyId(carVinRela.getCpyId());
        qryCarVinRelaDO.setCvVinCode(carVinRela.getCvVinCode());
        Long count = carVinRelaMapper.getCarVinRelaCount(qryCarVinRelaDO);
        if (count > 0) {
            log.error(this.getClass() + ".addCarVinRela() error : " + cpyNumber + "已经绑定" + carVinRela.getCvVinCode() + "VIN码！");
            baseResultDTO.setSuccess(false);
            baseResultDTO.setErrorDetail(cpyNumber + "已经绑定" + carVinRela.getCvVinCode() + "VIN码！");
            return baseResultDTO;
        }

        try {
            Integer result = carVinRelaMapper.addCarVinRela(carVinRela);
            if (result == null || result == 0) {
                log.error(this.getClass() + ".addRateInfo() error : " + ResultCodeConstants.ERROR_MSG_ERROR_ADD);
                baseResultDTO.setSuccess(false);
                baseResultDTO.setErrorDetail(ResultCodeConstants.ERROR_MSG_ERROR_ADD);
            }
        } catch (Exception e) {
            log.error(this.getClass() + ".addRateInfo() error : 数据重复或异常！");
            baseResultDTO.setSuccess(false);
            baseResultDTO.setErrorDetail("数据重复或异常！");
        }

        return baseResultDTO;
    }

    @Override
    public Long getCarVinRelaCount(CarVinRelaDO carVinRela) throws Exception {
        return carVinRelaMapper.getCarVinRelaCount(carVinRela);
    }

    @Override
    public List<CarVinRelaDO> getCarVinRelaList(CarVinRelaDO carVinRela) {
        return carVinRelaMapper.getCarVinRelaList(carVinRela);
    }

    @Override
    @Transactional
    public Integer removeCarVinRela(CarVinRelaDO carVinRela) throws Exception {
        return carVinRelaMapper.removeCarVinRela(carVinRela);
    }

    @Override
    @Transactional
    public BaseResultDTO importCarVinRela(MultipartFile file, CarVinRelaDO carVinRela, UserDO loginUser) throws Exception {
        if (file == null || file.isEmpty()) {
            log.error(this.getClass() + ".importCarVinRela() error, file is null");
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "请导入正确的VIN码文件！");
        }

        if (!file.getOriginalFilename().contains("vin_demo")) {
            log.error(this.getClass() + ".importCarVinRela() error, 请选择'vin_demo'文件");
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "请选择'vin_demo'文件！");
        }

        try {
            BatchResultDTO<List<String>> result = ExcelImportUtil.importListFromExcel(file);
            if (result.isFailed()) {
                return result;
            }

            if (carVinRela.getCpyNumber() == null) {
                log.error(this.getClass() + ".addCarVinRela() error : 渠道不能为空！");
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "渠道不能为空！");
            }
            Integer cpyNumber = carVinRela.getCpyNumber();
            CompanyDO company = new CompanyDO();
            company.setCpyNumber(cpyNumber);
            company = companyMapper.selectCompanyByCpyInfo(company);
            if (company.getCpyId() == null) {
                log.error(this.getClass() + ".addCarVinRela() error : 找不到" + cpyNumber + "对应的公司！");
                return new BaseResultDTO(false, ResultCodeConstants.FAILED, "找不到" + cpyNumber + "对应的公司！");
            }
            carVinRela.setCpyId(company.getCpyId());

            processingData(result.getModule(), loginUser, carVinRela);
        } catch (Exception e) {
            log.error(this.getClass() + ".importCarVinRela() error|fileName={}", file.getOriginalFilename(), e);
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "导入VIN码失败，请刷新页面后重试！");
        }

        return new BaseResultDTO(false, ResultCodeConstants.FAILED,
                "导入VIN码成功！" + (repeatVinCode.length() > 0 ? ("  重复vin码：" + repeatVinCode.toString()) : ""
                        + (repeatLicenseNumber.length() > 0 ? (" 重复车牌号：" + repeatLicenseNumber.toString()) : ""))
                        + (errorData.length() > 0 ? (" 错误数据" + errorData.toString()) : ""));
    }

    @Override
    @Transactional
    public BaseResultDTO bindVinCodeForUser(UserVinRelaDO userVinRelaDO, String vinIds) {
        String[] vinIdArr = vinIds.split(",");
        int sum=vinIdArr.length;
        List<UserVinRelaDO> userVinRelaDOList = userVinRelaMapper.getVinInfoByUserId(userVinRelaDO.getUserId());
        for (int i=0;i<vinIdArr.length;i++){
            for (UserVinRelaDO vinRelaDO:userVinRelaDOList){
                if (vinRelaDO.getVinId().toString().equals(vinIdArr[i])){
                    sum--;
                }
            }
        }
        if ((sum+userVinRelaDOList.size())> WanmaConstants.VIN_LIMIT){
            return  new BaseResultDTO(false, ResultCodeConstants.FAILED, "用户的vin数超过5绑定失败");
        }else {
            for (String vinId : vinIdArr){
                userVinRelaDO.setVinId(Long.parseLong(vinId));
                userVinRelaDO.setUserId(userVinRelaDO.getUserId());
                int count  = userVinRelaMapper.checkUserVinRela(userVinRelaDO);
                if (count==0){
                    userVinRelaMapper.addUserVinRela(userVinRelaDO);
                }
                //如果是卡用户 修改盗刷校验字段
                UserCardDO userCard = userCardMapper.getUnBindCardInfo(userVinRelaDO.getUserId());
                if (userCard!=null) {
                	userCardMapper.updateIsValid(userCard.getUcId());
				}
            }
        }
        return new BaseResultDTO();
    }

    @Override
    public List<UserVinRelaDO> getVinInfoByUserId(Long userId) {
        return userVinRelaMapper.getVinInfoByUserId(userId);
    }

    @Override
    public boolean deleteUserVinRela(Long pkId) {
        return userVinRelaMapper.deleteUserVinRela(pkId)>0;
    }

    @Override
    @Transactional
    public BaseResultDTO updateUserVinRela(UserCardDO userCard, UserDO userDO) {
        List<UserVinRelaDO> userVinRelaDOList = userVinRelaMapper.getVinInfoByUserId(userDO.getUserId());
        List<UserVinRelaDO> cardVinRelaDOList = userVinRelaMapper.getVinInfoByUserId(userCard.getUcUserId());
        List<UserVinRelaDO> vinRelaDOList = cardVinRelaDOList;
        //先去重复看是否超过用户vin码限制数量
        int num = hashList(userVinRelaDOList,cardVinRelaDOList);
        if (num>WanmaConstants.VIN_LIMIT){
            return new BaseResultDTO(false, ResultCodeConstants.FAILED, "用户和卡vin大于5不能绑定");
        }
        for (UserVinRelaDO userVinRelaDO:userVinRelaDOList){
            for (UserVinRelaDO cardVinRelaDO:vinRelaDOList){
                if (cardVinRelaDO.getVinId().equals(userVinRelaDO.getVinId())){
                    userVinRelaMapper.deleteUserVinRela(cardVinRelaDO.getPkId());
                }
            }
        }
        userVinRelaMapper.updateUserVinRela(userDO.getUserId(), userCard.getUcUserId());
        return new BaseResultDTO();
    }
        private int hashList(List<UserVinRelaDO> userVinRelaDOList,List<UserVinRelaDO> cardVinRelaDOList){
            boolean flag = true;
            int num = cardVinRelaDOList.size();
            for (UserVinRelaDO userVinRelaDO:userVinRelaDOList){
                for (int i=0;i<cardVinRelaDOList.size();i++){
                    if (cardVinRelaDOList.get(i).getVinId().equals(userVinRelaDO.getVinId())){
                        flag = false;
                    }
                }
                if (flag==true){
                    num++;
                }
                flag = true;
            }
            return num;
        }


    private void processingData(List<List<String>> data, UserDO loginUser, CarVinRelaDO carVinRela) {
        String vinCode = "";
        String licenseNumber = "";
        repeatVinCode = new StringBuffer();
        repeatLicenseNumber = new StringBuffer();
        errorData = new StringBuffer();
        for (int i = 1; i < data.size(); i++) {
            List<String> line = data.get(i);
            try {
                CarVinRelaDO carVinRelaDO = new CarVinRelaDO();
                vinCode = line.get(0);
                licenseNumber = line.get(1);
                carVinRelaDO.setCvVinCode(vinCode);
                carVinRelaDO.setCpyId(carVinRela.getCpyId());
                Long count = carVinRelaMapper.getCarVinRelaCount(carVinRelaDO);
                if (count > 0) {
                    log.error(this.getClass() + ".processingData() repeat VIN code|VIN码={}", vinCode);
                    repeatVinCode.append(vinCode).append("、");
                    continue;
                }
                carVinRelaDO.setCvVinCode(null);
                carVinRelaDO.setCvLicenseNumber(licenseNumber);
                count = carVinRelaMapper.getCarVinRelaCount(carVinRelaDO);
                if (count > 0) {
                    log.error(this.getClass() + ".processingData() repeat licenseNumber|车牌号={}", licenseNumber);
                    repeatLicenseNumber.append(licenseNumber).append("、");
                    continue;
                }

                carVinRelaDO.setCvVinCode(vinCode);
                carVinRelaDO.setIsUsed(0);
                carVinRelaDO.setCreatorId(loginUser.getUserId());
                carVinRelaMapper.addCarVinRela(carVinRelaDO);
            } catch (Exception e) {
                log.error(this.getClass() + ".processingData() error|VIN码={}|车牌号={}", vinCode, licenseNumber);
                errorData.append(vinCode).append(",").append(licenseNumber).append("、");
            }
        }

        if (repeatVinCode.length() > 0) {
            String repeatVinCodes = repeatVinCode.substring(0, (repeatVinCode.length() - 1));
            repeatVinCode = new StringBuffer(repeatVinCodes);
        }

        if (repeatLicenseNumber.length() > 0) {
            String repeatLicenseNumbers = repeatLicenseNumber.substring(0, (repeatLicenseNumber.length() - 1));
            repeatLicenseNumber = new StringBuffer(repeatLicenseNumbers);
        }

        if (errorData.length() > 0) {
            String errorDatas = errorData.substring(0, (errorData.length() - 1));
            errorData = new StringBuffer(errorDatas);
        }
    }
}
