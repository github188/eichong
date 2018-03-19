package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblHomepage;
import com.wanma.dubbox.service.TblHomepageService;

/**
 * 广告数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblHomepageServiceTest {
	@Resource
	TblHomepageService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblHomepage model = new TblHomepage();
		model.setName("1");
		model.setSts(1);
		model.setSeq(1);
		model.setImg("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblHomepage model = new TblHomepage();
		model.setId("1");
		model.setName("1");
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblHomepage model = new TblHomepage();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblHomepage model = new TblHomepage();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblHomepage model = new TblHomepage();
		model.setRtColumns("id");
		service.getList(model);
	}
}