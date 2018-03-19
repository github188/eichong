package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblPilemaker;
import com.wanma.dubbox.service.TblPilemakerService;

/**
 * 电桩制造厂商数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblPilemakerServiceTest {
	@Resource
	TblPilemakerService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblPilemaker model = new TblPilemaker();
		model.setName("123");
		model.setRemark("123");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblPilemaker model = new TblPilemaker();
		model.setId("1");
		model.setName("dfsfads");
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblPilemaker model = new TblPilemaker();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblPilemaker model = new TblPilemaker();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblPilemaker model = new TblPilemaker();
		model.setRtColumns("id");
		service.getList(model);
	}
}