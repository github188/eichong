package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblElectricPileHead;
import com.wanma.dubbox.service.TblElectricPileHeadService;

/**
 * 枪头数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext.xml","/spring/spring-redis.xml"})
@Transactional
public class TblElectricPileHeadServiceTest {
	@Resource
	TblElectricPileHeadService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblElectricPileHead model = new TblElectricPileHead();
		model.setName("1");
		model.setId("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblElectricPileHead model = new TblElectricPileHead();
		model.setId("1");
		model.setName("1");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblElectricPileHead model = new TblElectricPileHead();
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
		TblElectricPileHead model = new TblElectricPileHead();
		model.setId("4057");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblElectricPileHead model = new TblElectricPileHead();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblElectricPileHead model = new TblElectricPileHead();
		model.setRtColumns("id");
		service.getList(model);
	}
}