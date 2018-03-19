package Test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblConfigContent;
import com.wanma.dubbox.service.TblConfigContentService;

/**
 * 参数配置数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblConfigContentServiceTest {
	@Resource
	TblConfigContentService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblConfigContent model = new TblConfigContent();
		model.setPramid(1);
		model.setCtt("1");
		model.setSts(1);
		model.setMem("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblConfigContent model = new TblConfigContent();
		model.setMem("1");
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblConfigContent model = new TblConfigContent();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblConfigContent model = new TblConfigContent();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblConfigContent model = new TblConfigContent();
		model.setRtColumns("id");
		service.getList(model);
	}
}