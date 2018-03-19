package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblWebRelease;
import com.wanma.dubbox.service.TblWebReleaseService;

/**
 * 电桩数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblWebReleaseServiceTest {
	@Resource
	TblWebReleaseService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblWebRelease model = new TblWebRelease();
		model.setFlg(1);
		model.setTtl("123");
		model.setTxt("123");
		model.setType("123");
		model.setType("1");
		model.setImg(1);
		model.setOrd(1);
		model.setIndct("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblWebRelease model = new TblWebRelease();
		model.setId("1");
		model.setFlg(1);
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblWebRelease model = new TblWebRelease();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblWebRelease model = new TblWebRelease();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblWebRelease model = new TblWebRelease();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblWebRelease model = new TblWebRelease();
		model.setRtColumns("id");
		service.getList(model);
	}
}