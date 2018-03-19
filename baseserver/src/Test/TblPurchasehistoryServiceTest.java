package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblPurchasehistory;
import com.wanma.dubbox.service.TblPurchasehistoryService;

/**
 * 消费记录数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblPurchasehistoryServiceTest {
	@Resource
	TblPurchasehistoryService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestGetCount(){
		TblPurchasehistory model = new TblPurchasehistory();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblPurchasehistory model = new TblPurchasehistory();
		model.setRtColumns("id");
		service.getList(model);
	}
}