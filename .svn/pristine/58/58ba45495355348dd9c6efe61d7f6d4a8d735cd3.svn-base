package Test;




import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblElectricpilecomment;
import com.wanma.dubbox.service.TblElectricpilecommentService;

/**
 * 桩评论数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblElectricpilecommentServiceTest {
	@Resource
	TblElectricpilecommentService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblElectricpilecomment model = new TblElectricpilecomment();
		model.setTxt("123");
		model.setUid(1);
		model.setUname("1");
		model.setPic("123");
		model.setType(1);
		model.setSts(1);
		model.setEid(1);
		model.setPrsCt(1);
		model.setRpyCt(1);
		model.setUpId(1);
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblElectricpilecomment model = new TblElectricpilecomment();
		model.setId("1");
		model.setTxt("123");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblElectricpilecomment model = new TblElectricpilecomment();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestGetCount(){
		TblElectricpilecomment model = new TblElectricpilecomment();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblElectricpilecomment model = new TblElectricpilecomment();
		model.setRtColumns("id");
		service.getList(model);
	}
}