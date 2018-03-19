package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblCompany;
import com.wanma.dubbox.service.TblCompanyService;

/**
 * 车型数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblCompanyServiceTest {
	@Resource
	TblCompanyService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblCompany model = new TblCompany();
		model.setAdr("dfsfads");
		model.setAtrName("fdafas");
		model.setAtrCid("1");
		model.setName("fdasfsa");
		model.setMail("fdasf");
		model.setPostCd(123);
		model.setScope("123");
		model.setAtrPhone("123");
		model.setBuslic("123");
		model.setOrgCd("123");
		model.setDjz("123");
		model.setSqz("123");
		model.setMaiAdr("123");
		model.setPicTp("123");
		model.setComNum("123");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblCompany model = new TblCompany();
		model.setId("1");
		model.setAdr("dfsfads");
		service.update(model);
	}
	
	@Test
	public void TestDelete(){
		TblCompany model = new TblCompany();
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
		TblCompany model = new TblCompany();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblCompany model = new TblCompany();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblCompany model = new TblCompany();
		model.setRtColumns("id");
		service.getList(model);
	}
}