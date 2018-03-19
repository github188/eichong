package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblUserBusiness;
import com.wanma.dubbox.service.TblUserBusinessService;

/**
 * 纯商家服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblUserBusinessServiceTest {
	@Resource
	TblUserBusinessService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblUserBusiness model = new TblUserBusiness();
		model.setName("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblUserBusiness model = new TblUserBusiness();
		model.setId("1");
		model.setName("1");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblUserBusiness model = new TblUserBusiness();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblUserBusiness model = new TblUserBusiness();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblUserBusiness model = new TblUserBusiness();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblUserBusiness model = new TblUserBusiness();
		model.setRtColumns("id");
		service.getList(model);
	}
}