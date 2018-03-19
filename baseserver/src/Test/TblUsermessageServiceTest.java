package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblUsermessage;
import com.wanma.dubbox.service.TblUsermessageService;

/**
 * 电桩数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblUsermessageServiceTest {
	@Resource
	TblUsermessageService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblUsermessage model = new TblUsermessage();
		model.setUid(1);
		model.setFroUid(1);
		model.setFroUname("1");
		model.setTtl("1");
		model.setTxt("123");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblUsermessage model = new TblUsermessage();
		model.setId("1");
		model.setUid(1);
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblUsermessage model = new TblUsermessage();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblUsermessage model = new TblUsermessage();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblUsermessage model = new TblUsermessage();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblUsermessage model = new TblUsermessage();
		model.setRtColumns("id");
		service.getList(model);
	}
}