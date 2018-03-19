package Test;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblConfigparameter;
import com.wanma.dubbox.service.TblConfigparameterService;

/**
 * 参数配置数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblConfigparameterServiceTest {
	@Resource
	TblConfigparameterService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblConfigparameter model = new TblConfigparameter();
		model.setName("1");
		model.setMemo("1");
		model.setType(1);
		model.setSts(1);
		service.insert(model);
	}
	
	@Test
	public void TestUpdate(){
		TblConfigparameter model = new TblConfigparameter();
		model.setName("1");
		service.update(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblConfigparameter model = new TblConfigparameter();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblConfigparameter model = new TblConfigparameter();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblConfigparameter model = new TblConfigparameter();
		model.setRtColumns("id");
		service.getList(model);
	}
}