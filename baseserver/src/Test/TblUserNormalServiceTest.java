package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblUserNormal;
import com.wanma.dubbox.service.TblUserNormalService;

/**
 * 电桩数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext.xml","/spring/spring-redis.xml"})
@Transactional
public class TblUserNormalServiceTest {
	@Resource
	TblUserNormalService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblUserNormal model = new TblUserNormal();
		model.setName("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblUserNormal model = new TblUserNormal();
		model.setId("1");
		model.setDrvLce("1");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblUserNormal model = new TblUserNormal();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblUserNormal model = new TblUserNormal();
		model.setId("6");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblUserNormal model = new TblUserNormal();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblUserNormal model = new TblUserNormal();
		model.setRtColumns("id");
		service.getList(model);
	}
}