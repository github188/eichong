package Test;




import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblEquipmentrepair;
import com.wanma.dubbox.service.TblEquipmentrepairService;

/**
 * 桩评论数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblEquipmentrepairServiceTest {
	@Resource
	TblEquipmentrepairService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblEquipmentrepair model = new TblEquipmentrepair();
		model.setTxt("123");
		model.setUid(1);
		model.setWsts(1);
		model.setWtpId(1);
		model.setTxt("1");
		model.setSts(1);
		model.setEpid(1);
		model.setDeaRst("1");
		model.setDvcTp(1);
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblEquipmentrepair model = new TblEquipmentrepair();
		model.setId("1");
		model.setTxt("123");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblEquipmentrepair model = new TblEquipmentrepair();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestGetCount(){
		TblEquipmentrepair model = new TblEquipmentrepair();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblEquipmentrepair model = new TblEquipmentrepair();
		model.setRtColumns("id");
		service.getList(model);
	}
}