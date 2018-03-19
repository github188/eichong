package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblMenu;
import com.wanma.dubbox.service.TblMenuService;

/**
 * 电桩数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblMenuServiceTest {
	@Resource
	TblMenuService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblMenu model = new TblMenu();
		model.setId("123");
		model.setType("1");
		model.setTxt("1");
		model.setStNum(1l);
		model.setCreUs("1");
		model.setUptUs("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblMenu model = new TblMenu();
		model.setId("123");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblMenu model = new TblMenu();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		try {
			service.delete(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void TestSelectOne(){
		TblMenu model = new TblMenu();
		model.setRtColumns("id");
		model.setId("123");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblMenu model = new TblMenu();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblMenu model = new TblMenu();
		model.setRtColumns("id");
		service.getList(model);
	}
}