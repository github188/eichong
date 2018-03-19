package Test;



import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblBomList;
import com.wanma.dubbox.service.TblBomListService;

/**
 * 充点卡数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblBomListServiceTest {
	@Resource
	TblBomListService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
//	@Test
//	public void TestInsert(){
//		TblBomList model = new TblBomList();
//		service.insert(model);
//	}
	
	@Test
	public void TestUpdate(){
		TblBomList model = new TblBomList();
		model.setId("1");
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		model.setId("1");
		model.setHdwNm("1");
		model.setHdwVs("1");
		model.setFwNm("1");
		model.setFwVs("1");
		model.setMd5("1");
		model.setfUpt(1);
		service.update(model);
	}
	
	@Test
	public void TestGetList(){
		TblBomList model = new TblBomList();
		model.setRtColumns("id");
		service.getList(model);
	}
}