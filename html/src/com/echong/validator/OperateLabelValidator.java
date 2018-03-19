package com.echong.validator;

import com.google.common.base.Strings;
import com.echong.model.ElectricLabel;
import com.echong.model.OperateLabelModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by zangyaoyi on 2017/1/5.
 */
public class OperateLabelValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(OperateLabelValidator.class);

    public static Boolean ensureOperateLabel(OperateLabelModel model) {
        if (null == model) {
            LOGGER.debug("ensureOperateLabel is failed ,operateLabelModel is null");
            return false;
        }
        if (Strings.isNullOrEmpty(model.getApp_id()) || Strings.isNullOrEmpty(model.getSig())) {
            LOGGER.debug("ensureOperateLabel is failed ,app_id||sig is null;app_id={}|sig={}", model.getApp_id(), model.getSig());
            return false;
        }
        return ensureElectricLabel(model.getElectricLabel());
    }

    public static Boolean ensureElectricLabel(ElectricLabel model) {
        if (null == model) {
            LOGGER.debug("ensureElectricLabel is failed ,electricLabel is null");
            return false;
        }
        if (null == model.getSession_id() || null == model.getUser_id()) {
            LOGGER.debug("ensureElectricLabel is failed ,sessionId||userID is null;sessionId={}|userId={}", model.getSession_id(), model.getUser_id());
            return false;
        }
        if (Strings.isNullOrEmpty(model.getPile_code()) || null == model.getElect() || null == model.getVoltage() || null == model.getTime()) {
            LOGGER.debug("ensureElectricLabel is failed ,param is null;Pile_code={}|Elect={}|Voltage={}|Time={}", model.getPile_code(), model.getElect(), model.getVoltage(), model.getTime());
            return false;
        }
        if (model.getPile_code().length() != 18) {
            LOGGER.debug("ensureElectricLabel is failed, pile_code length !=18 Pile_code={}", model.getPile_code());
            return false;
        }
        convertElectricLabel(model);
        return true;
    }

    //e充网只有枪编号一个字段，格式为 桩编号+两位枪ID,下面转为两个字段。
    private static void convertElectricLabel(ElectricLabel model) {
        String pileCode = model.getPile_code().substring(0, 16);
        String interNo = model.getPile_code().substring(16, 18);
        model.setPile_code(pileCode);
        model.setInter_no(Integer.valueOf(interNo));
    }
}
