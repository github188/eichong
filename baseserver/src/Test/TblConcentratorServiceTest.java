package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblConcentrator;
import com.wanma.dubbox.service.TblConcentratorService;

/**
 * 集中器数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblConcentratorServiceTest {
	@Resource
	TblConcentratorService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblConcentrator model = new TblConcentrator();
		model.setName("1");
		model.setSimMac("1");
		model.setSimCd("1");
		model.setSimTp(1);
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblConcentrator model = new TblConcentrator();
		model.setId("1");
		model.setName("1");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblConcentrator model = new TblConcentrator();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblConcentrator model = new TblConcentrator();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblConcentrator model = new TblConcentrator();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblConcentrator model = new TblConcentrator();
		model.setRtColumns("id");
		service.getList(model);
	}
}