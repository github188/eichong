package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblUserAdmin;
import com.wanma.dubbox.service.TblUserAdminService;

/**
 * 电桩数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblUserAdminServiceTest {
	@Resource
	TblUserAdminService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblUserAdmin model = new TblUserAdmin();
		model.setId("1");
		model.setName("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblUserAdmin model = new TblUserAdmin();
		model.setId("1");
		model.setName("1");
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblUserAdmin model = new TblUserAdmin();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
}