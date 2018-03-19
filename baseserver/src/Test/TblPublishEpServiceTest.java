package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblPublishEp;
import com.wanma.dubbox.service.TblPublishEpService;

/**
 * 电桩数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblPublishEpServiceTest {
	@Resource
	TblPublishEpService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblPublishEp model = new TblPublishEp();
		model.setAdr("123");
		model.setImg("1");
		model.setLgt("1");
		model.setLtt("1");
		model.setPrmNt("1");
		model.setNt("1");
		model.setSts(1);
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblPublishEp model = new TblPublishEp();
		model.setId("1");
		model.setImg("1");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblPublishEp model = new TblPublishEp();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblPublishEp model = new TblPublishEp();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblPublishEp model = new TblPublishEp();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblPublishEp model = new TblPublishEp();
		model.setRtColumns("id");
		service.getList(model);
	}
}