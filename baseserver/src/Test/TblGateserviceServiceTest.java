package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblGateservice;
import com.wanma.dubbox.service.TblGateserviceService;

/**
 * gate服务器数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblGateserviceServiceTest {
	@Resource
	TblGateserviceService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblGateservice model = new TblGateservice();
		model.setName("1");
		model.setIp("1");
		model.setPort(1);
		model.setFaiCount(1);
		model.setSte(1);
		model.setCuserId(1);
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblGateservice model = new TblGateservice();
		model.setId("1");
		model.setPort(1);
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblGateservice model = new TblGateservice();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblGateservice model = new TblGateservice();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblGateservice model = new TblGateservice();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblGateservice model = new TblGateservice();
		model.setRtColumns("id");
		service.getList(model);
	}
}