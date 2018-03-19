package com.wanma.ims.core;

import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.wanma.ims.common.domain.CompanyDO;
import com.wanma.ims.common.domain.FinAccountSplitDetailsDO;
import com.wanma.ims.mapper.FinAccountSplitDetailsMapper;
import com.wanma.ims.service.FinAccountSplitDetailsService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.*;

/**
 * 初始化数据
 * @author bingo
 * @date 2018-1-4
 */
public class InitAccountSplitDetails implements InitializingBean, ServletContextAware {
    @Autowired
    private FinAccountSplitDetailsMapper finAccountSplitDetailsMapper;

    //分账数据集
    public static Set<String> accountSplitDetailsSet = new HashSet<String>();

    //标识是否已经在批量处理数据
    public static Boolean batchFlag = false;

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    public static void setData(Set<String> accountSplitDetailsSet){
        accountSplitDetailsSet = accountSplitDetailsSet;
    }

    @Override
    public void setServletContext(ServletContext arg0) {
        List<String> detailsDOList = finAccountSplitDetailsMapper.getFinAccountSplitDetailsForBatch(new FinAccountSplitDetailsDO());
        accountSplitDetailsSet.addAll(detailsDOList);
    }
}
