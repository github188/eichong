package com.wanma.controller;

import com.bluemobi.product.model.common.DwzPagerMySQL;
import com.wanma.dao.TblOffLineInfoDao;
import com.wanma.model.TblOffLineInfo;
import com.wanma.web.support.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 电装断网记录
 * Created by zangyaoyi on 2017/8/1.
 */
@Controller
@RequestMapping("/admin/offLine/")
public class CmsOffLineInfoController {
    private static final Logger LOGGER = LoggerFactory.getLogger(CmsOffLineInfoController.class);
    @Autowired
    private TblOffLineInfoDao tblOffLineInfoDao;

    @RequestMapping("list")
    public String getSimCardList(@ModelAttribute("pager") DwzPagerMySQL pager,
                                 @ModelAttribute TblOffLineInfo tblOffLineInfo, Model model, HttpServletRequest request) {
        List<TblOffLineInfo> tblOffLineInfos = tblOffLineInfoDao.getTblOffLineInfoList(tblOffLineInfo);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date begin;
        Date end;
        for (TblOffLineInfo offLineInfo : tblOffLineInfos) {
            begin = DateUtil.parse(offLineInfo.getBeginOfflineTime());
            end = DateUtil.parse(offLineInfo.getEndOfflineTime());
            long diff=end.getTime()-begin.getTime();
            offLineInfo.setInterval(diff/(1000*60));
        }
        long total = 0;
        if (tblOffLineInfos != null) {
            total = tblOffLineInfos.size();
        }
        if (total <= pager.getOffset()) {
            pager.setPageNum(1L);
        }
        tblOffLineInfo.setPager(pager);
        pager.setTotal(total);
        model.addAttribute("infos", tblOffLineInfos);
        model.addAttribute("tblOffLineInfo", tblOffLineInfo);
        model.addAttribute("pager", pager);
        return "backstage/offLineInfo/offLineInfo";
    }
}
