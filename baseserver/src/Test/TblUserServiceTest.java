package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblUser;
import com.wanma.dubbox.service.TblUserService;

/**
 * 电桩数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/spring/applicationContext.xml","/spring/spring-redis.xml"})
@Transactional
public class TblUserServiceTest {
	@Resource
	TblUserService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblUser model = new TblUser();
		model.setuAcc("1");
		model.setuPw("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblUser model = new TblUser();
		model.setId("1");
		model.setuAcc("1");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblUser model = new TblUser();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblUser model = new TblUser();
		model.setId("1");
		service.selectOne(model);
	}
}