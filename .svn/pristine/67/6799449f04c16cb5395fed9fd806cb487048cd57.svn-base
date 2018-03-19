package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblCity;
import com.wanma.dubbox.service.TblCityService;

/**
 * 城市数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblCityServiceTest {
	@Resource
	TblCityService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
//		TblCity model = new TblCity();
//		model.setName("dfsfads");
//		model.setCreUser(1);
//		model.setLuUser(1);
//		model.setJdEty(new BigDecimal(1));
//		model.setFdEty(new BigDecimal(1));
//		model.setPdEty(new BigDecimal(1));
//		model.setGdEty(new BigDecimal(1));
//		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblCity model = new TblCity();
		model.setId("1");
		model.setName("dfsfads");
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblCity model = new TblCity();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblCity model = new TblCity();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblCity model = new TblCity();
		model.setRtColumns("id");
		service.getList(model);
	}
}