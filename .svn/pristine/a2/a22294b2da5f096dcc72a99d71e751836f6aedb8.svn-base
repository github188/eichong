package Test;


import java.math.BigDecimal;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblCarinfo;
import com.wanma.dubbox.service.TblCarinfoService;

/**
 * 电动车品牌数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblCarinfoServiceTest {
	@Resource
	TblCarinfoService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblCarinfo model = new TblCarinfo();
		model.setName("dfsfads");
		model.setMaxOdo(new BigDecimal(1));
		model.setBttCty(new BigDecimal(1));
		model.setName("fdasfsa");
		model.setRemark("fdasf");
		model.setIcon("123");
		model.setComId(1);
		model.setCgTim("123");
		model.setBttTp(1);
		model.setCgMod(1);
		model.setInf(1);
		model.setSts(1);
		model.setBrandName("123");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblCarinfo model = new TblCarinfo();
		model.setId("1");
		model.setName("dfsfads");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblCarinfo model = new TblCarinfo();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblCarinfo model = new TblCarinfo();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblCarinfo model = new TblCarinfo();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblCarinfo model = new TblCarinfo();
		model.setRtColumns("id");
		service.getList(model);
	}
}