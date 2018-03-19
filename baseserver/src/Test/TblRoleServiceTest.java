package Test;


import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblRole;
import com.wanma.dubbox.service.TblRoleService;

/**
 * 电动车品牌数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblRoleServiceTest {
	@Resource
	TblRoleService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblRole model = new TblRole();
		model.setId("9999999");
		model.setName("dfsfads");
		model.setNt("1");
		model.setCtgry("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblRole model = new TblRole();
		model.setName("dfsfads");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblRole model = new TblRole();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblRole model = new TblRole();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblRole model = new TblRole();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblRole model = new TblRole();
		model.setRtColumns("id");
		service.getList(model);
	}
}