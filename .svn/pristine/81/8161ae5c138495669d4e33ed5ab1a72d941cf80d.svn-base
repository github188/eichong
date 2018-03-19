package Test;



import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblCardapplicationform;
import com.wanma.dubbox.service.TblCardapplicationformService;

/**
 * 充点卡数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblCardapplicationformServiceTest {
	@Resource
	TblCardapplicationformService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
//	@Test
//	public void TestInsert(){
//		TblCardapplicationform model = new TblCardapplicationform();
//		model.setSts(1);
//		model.setUid(1l);
//		service.insert(model);
//	}
	
	@Test
	public void TestUpdate(){
		TblCardapplicationform model = new TblCardapplicationform();
		model.setId("1");
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblCardapplicationform model = new TblCardapplicationform();
		model.setRtColumns("id,uid");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblCardapplicationform model = new TblCardapplicationform();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblCardapplicationform model = new TblCardapplicationform();
		model.setRtColumns("id");
		service.getList(model);
	}
}