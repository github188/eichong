package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblFeedback;
import com.wanma.dubbox.service.TblFeedbackService;

/**
 * 电桩数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblFeedbackServiceTest {
	@Resource
	TblFeedbackService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblFeedback model = new TblFeedback();
		model.setTxt("1");
		model.setUid(1);
		model.setRsn("1");
		model.setSts("1");
		model.setUdtUid(1);
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblFeedback model = new TblFeedback();
		model.setId("1");
		model.setRsn("1");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblFeedback model = new TblFeedback();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblFeedback model = new TblFeedback();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblFeedback model = new TblFeedback();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblFeedback model = new TblFeedback();
		model.setRtColumns("id");
		service.getList(model);
	}
}