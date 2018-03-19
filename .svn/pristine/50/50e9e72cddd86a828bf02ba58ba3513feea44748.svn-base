package Test;



import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblUserCard;
import com.wanma.dubbox.service.TblUserCardService;

/**
 * 充点卡数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblUserCardServiceTest {
	@Resource
	TblUserCardService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblUserCard model = new TblUserCard();
		model.setSts(1);
		model.setInum("1");
		model.setExtNum("1");
		model.setBlc(1d);
		model.setMd(1);
		model.setSts(1);
		model.setComNum(1);
		model.setUid("1");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblUserCard model = new TblUserCard();
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
		TblUserCard model = new TblUserCard();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblUserCard model = new TblUserCard();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblUserCard model = new TblUserCard();
		model.setRtColumns("id");
		service.getList(model);
	}
}