package Test;


import java.util.Date;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblBespoke;
import com.wanma.dubbox.service.TblBespokeService;

/**
 * 预约数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblBespokeServiceTest {
	@Resource
	TblBespokeService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblBespoke model = new TblBespoke();
		model.seteId(1);
		model.setTime("123");
		model.setSts(1);
		model.setHid(1);
		model.setUser(1);
		model.setBegTim(new Date());
		model.setEndTim(new Date());
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblBespoke model = new TblBespoke();
		model.setId("1");
		model.seteId(1);
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblBespoke model = new TblBespoke();
		model.setId("1");
		model.setRtColumns("id");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblBespoke model = new TblBespoke();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblBespoke model = new TblBespoke();
		model.setRtColumns("id");
		service.getList(model);
	}
}