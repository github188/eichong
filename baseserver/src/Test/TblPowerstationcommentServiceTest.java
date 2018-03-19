package Test;




import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblPowerstationcomment;
import com.wanma.dubbox.service.TblPowerstationcommentService;

/**
 * 站评论数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblPowerstationcommentServiceTest {
	@Resource
	TblPowerstationcommentService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblPowerstationcomment model = new TblPowerstationcomment();
		model.setTxt("123");
		model.setUid(1);
		model.setUname("1");
		model.setPic("123");
		model.setType(1);
		model.setSts(1);
		model.setPid(1);
		model.setPrsCt(1);
		model.setRpyCt(1);
		model.setUpId(1);
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblPowerstationcomment model = new TblPowerstationcomment();
		model.setId("1");
		model.setTxt("123");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblPowerstationcomment model = new TblPowerstationcomment();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestGetCount(){
		TblPowerstationcomment model = new TblPowerstationcomment();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblPowerstationcomment model = new TblPowerstationcomment();
		model.setRtColumns("id");
		service.getList(model);
	}
}