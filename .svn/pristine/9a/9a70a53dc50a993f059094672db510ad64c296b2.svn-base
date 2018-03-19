package Test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblApplyEp;
import com.wanma.dubbox.service.TblApplyEpService;

/**
 * 建桩管理数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblApplyEpServiceTest {
	@Resource
	TblApplyEpService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
//	@Test
//	public void TestInsert(){
//		TblApplyEp model = new TblApplyEp();
//		model.setSts(1);
//		model.setSts(1);
//		model.setUid(1l);
//		service.insert(model);
//	}
	
	@Test
	public void TestUpdate(){
		TblApplyEp model = new TblApplyEp();
		model.setId("1");
		model.setSts(1);
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblApplyEp model = new TblApplyEp();
		model.setRtColumns("id,uid");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblApplyEp model = new TblApplyEp();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblApplyEp model = new TblApplyEp();
		model.setRtColumns("id");
		service.getList(model);
	}
}