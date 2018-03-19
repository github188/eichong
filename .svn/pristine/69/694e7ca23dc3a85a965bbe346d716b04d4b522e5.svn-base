package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblElectricPile;
import com.wanma.dubbox.service.TblElectricPileService;

/**
 * 电桩数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext.xml","/spring/spring-redis.xml"})
@Transactional
public class TblElectricPileServiceTest {
	@Resource
	TblElectricPileService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblElectricPile model = new TblElectricPile();
		model.setName("1");
		model.setCid("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblElectricPile model = new TblElectricPile();
		model.setId("1");
		model.setName("1");
		service.update(model);
	}
	
	@Test
	public void TestUpdateMore() throws Exception{
		TblElectricPile model = new TblElectricPile();
		model.setPowId("252");
		model.setIsBd(0);
		service.updateMore(model);
	}
	
	
	@Test
	public void TestDelete(){
		TblElectricPile model = new TblElectricPile();
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
		TblElectricPile model = new TblElectricPile();
		model.setId("15463");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblElectricPile model = new TblElectricPile();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblElectricPile model = new TblElectricPile();
		model.setRtColumns("id");
		service.getList(model);
	}
}