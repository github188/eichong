package Test;


import java.math.BigDecimal;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblRateinFormation;
import com.wanma.dubbox.service.TblRateinFormationService;

/**
 * 费率数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblRateinFormationServiceTest {
	@Resource
	TblRateinFormationService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblRateinFormation model = new TblRateinFormation();
		model.setMdId(1);
		model.setEffDate(new Date());
		model.setExpDate(new Date());
		model.setFreeMny(new BigDecimal(1));
		model.setMinFreeMny(new BigDecimal(1));
		model.setQunDate("1");
		model.setSqunDate(new Date());
		model.setEndQunDate(new Date());
		model.setTimMker(1);
		model.setTptimTrff(new BigDecimal(1));
		model.setPekEctyPrc(new BigDecimal(1));
		model.setUslPrc(new BigDecimal(1));
		model.setVlyTimPrc(new BigDecimal(1));
		model.setRvtRat(new BigDecimal(1));
		model.setSviCg(new BigDecimal(1));
		model.setWrnMny(new BigDecimal(1));
		model.setUid("1");
		model.setAreaCd("1");
		model.setCityCd("1");
		model.setProCd("1");
		model.setRemark("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblRateinFormation model = new TblRateinFormation();
//		model.setAreaName("1");
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblRateinFormation model = new TblRateinFormation();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblRateinFormation model = new TblRateinFormation();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblRateinFormation model = new TblRateinFormation();
		model.setRtColumns("id");
		service.getList(model);
	}
}