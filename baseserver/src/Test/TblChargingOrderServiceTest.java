package Test;



import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblChargingOrder;
import com.wanma.dubbox.service.TblChargingOrderService;

/**
 * 消费订单数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblChargingOrderServiceTest {
	@Resource
	TblChargingOrderService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblChargingOrder model = new TblChargingOrder();
		model.setCode("123");
		model.setApotmcd("123");
		model.setPilenber("123");
		model.setuId(1);
		model.setType(1);
		model.setTqtum("123");
		model.setTranum("123");
		model.setTstype(1);
		model.setBtime("123");
		model.setEtime("123");
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblChargingOrder model = new TblChargingOrder();
		model.setId("1");
		model.setCode("123");
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblChargingOrder model = new TblChargingOrder();
		model.setId("1");
		model.setRtColumns("id");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblChargingOrder model = new TblChargingOrder();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblChargingOrder model = new TblChargingOrder();
		model.setRtColumns("id");
		service.getList(model);
	}
}