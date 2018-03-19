package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblPowerStation;
import com.wanma.dubbox.service.TblPowerStationService;

/**
 * 充电点服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblPowerStationServiceTest {
	@Resource
	TblPowerStationService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblPowerStation model = new TblPowerStation();
		model.setName("1");
		model.setElids("1");
		model.setCuId(1);
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblPowerStation model = new TblPowerStation();
		model.setId("1");
		model.setName("1");
		try {
			service.update(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestDelete(){
		TblPowerStation model = new TblPowerStation();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		try {
			service.delete(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestSelectOne(){
		TblPowerStation model = new TblPowerStation();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblPowerStation model = new TblPowerStation();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblPowerStation model = new TblPowerStation();
		model.setRtColumns("id");
		service.getList(model);
	}
}