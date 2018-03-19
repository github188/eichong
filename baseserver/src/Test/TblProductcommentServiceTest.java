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
import com.wanma.dubbox.model.TblProductcomment;
import com.wanma.dubbox.service.TblProductcommentService;

/**
 * 评论数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblProductcommentServiceTest {
	@Resource
	TblProductcommentService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblProductcomment model = new TblProductcomment();
		model.setTxt("123");
		model.setPrdctId(1);
		model.setUid(1);
		model.setUname("1");
		model.setStar(1d);
		model.setPic("123");
		model.setOdrId(1);
		model.setType(1);
		model.setSts(1);
		model.setCtime(new Date());
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblProductcomment model = new TblProductcomment();
		model.setId("1");
		model.setTxt("123");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblProductcomment model = new TblProductcomment();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestGetCount(){
		TblProductcomment model = new TblProductcomment();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblProductcomment model = new TblProductcomment();
		model.setRtColumns("id");
		service.getList(model);
	}
}