package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblInvoice;
import com.wanma.dubbox.service.TblInvoiceService;

/**
 * 开票数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblInvoiceServiceTest {
	@Resource
	TblInvoiceService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
//	@Test
//	public void TestInsert(){
//		TblInvoice model = new TblInvoice();
//		model.setNum("123");
//		service.insert(model);
//	}
//	
	@Test
	public void TestUpdate(){
		TblInvoice model = new TblInvoice();
		model.setId("1");
		model.setNum("123");
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblInvoice model = new TblInvoice();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblInvoice model = new TblInvoice();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblInvoice model = new TblInvoice();
		model.setRtColumns("id");
		service.getList(model);
	}
}