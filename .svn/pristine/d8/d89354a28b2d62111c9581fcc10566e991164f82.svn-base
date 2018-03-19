package Test;


import javax.annotation.Resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wanma.common.InitDeployUrlServelt;
import com.wanma.dubbox.model.TblCommitLog;
import com.wanma.dubbox.service.TblCommitLogService;

/**
 * 日志数据服务单元测试
 * @author lhy
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/spring/applicationContext.xml")
@Transactional
public class TblCommitLogServiceTest {
	@Resource
	TblCommitLogService service;
	
	@Before
	public void init(){
		new InitDeployUrlServelt().initColumnsMap();
	}
	
	@Test
	public void TestInsert(){
		TblCommitLog model = new TblCommitLog();
		model.setTxt("1");
		model.setSts("1");
		model.setName("1");
		model.setIp("1");
		model.setUid("1");
		service.insert(model);
	}
	
	@Test
	public void TestDelete(){
		TblCommitLog model = new TblCommitLog();
		String[] pkIds = new String[3];
		pkIds[0] = "1";
		pkIds[1] = "2";
		pkIds[2] = "3";
		model.setPkIds(pkIds);
		service.delete(model);
	}
	
	@Test
	public void TestSelectOne(){
		TblCommitLog model = new TblCommitLog();
		model.setRtColumns("id");
		model.setId("1");
		service.selectOne(model);
	}
	
	@Test
	public void TestGetCount(){
		TblCommitLog model = new TblCommitLog();
		service.getCount(model);
	}
	
	@Test
	public void TestGetList(){
		TblCommitLog model = new TblCommitLog();
		model.setRtColumns("id");
		service.getList(model);
	}
}