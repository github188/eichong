/**     
 * @Title:  RealtimeUtil.java   
 * @Package com.wanma.hbase   
 * @Description:    TODO  
 * @author: Android_Robot     
 * @date:   2016年2月24日 下午4:49:44   
 * @version V1.0     
 */
package com.wanma.hbase;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;

import com.epcentre.service.AnalyzeService;
import com.epcentre.service.RealData;

/**
 * @author bc
 *
 */
public class RealtimeUtil {
	private static RealtimeUtil instance;
	private static HBaseUtil hbase = null;
	private static String tablenameAc = "AcRealData";
	private static String tablenameDc = "DcRealData";
	private static String tablenameAcHistory = "AcRealDataHistory";
	private static String tablenameDcHistory = "DcRealDataHistory";
	private static String tablenameLog = "CommitLog";
	private static String columnFamily = "sx";
	private static SimpleDateFormat sdf = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	private static ArrayList<RealData> jlList=new ArrayList();
	private static ArrayList<RealData> jlHbaseList=new ArrayList();
	private static ArrayList<RealData> zlList=new ArrayList();
	private static ArrayList<RealData> zlHbaseList=new ArrayList();
	static {
		hbase = new HBaseUtil();
	}

	private RealtimeUtil() {
	}

	public static RealtimeUtil getInstance() {
		if (instance == null) {
			instance = new RealtimeUtil();
		}
		return instance;
	}

	public boolean createTable(String tablename,String... columnFamily) {
		return hbase.creat(tablename, columnFamily);
	}
	
	public boolean dropTable(String tablename) {
		return hbase.delete(tablename);
	}

	public static boolean addRealtimeData(Object object) {
		RealData data=(RealData) object;
		if (data.getEpType()==5) {// 直流
			zlList.add(data);
			return true;
		} else if (data.getEpType()==14) {// 交流
			jlList.add(data);
			return true;
		}
		return false;
	}
	
	public static boolean addLogData(CommitLog object) {
		return hbase.put(tablenameLog, System.currentTimeMillis()+"", columnFamily, object);
	}
	
	
	
	public void saveDatas(){
		 TimerTask task = new TimerTask() {  
	            @Override  
	            public void run() {  
	            	jlHbaseList=jlList;
	            	jlList=new ArrayList();
	        		zlHbaseList=zlList;
	            	zlList=new ArrayList();
	        		putBatch(tablenameAc, "epId", "sx", jlHbaseList,false);
	        		putBatch(tablenameAcHistory, "epId", "sx", jlHbaseList,true);
	        		putBatch(tablenameDc, "epId", "sx", zlHbaseList,false);
	        		putBatch(tablenameDcHistory, "epId", "sx", zlHbaseList,true);
	        		jlHbaseList=null;
	        		zlHbaseList=null;
	            }  
	        };  
	        Timer timer = new Timer();  
	        long delay = 0;  
	        long intevalPeriod = 5 * 1000;  
	        // schedules the task to be run in an interval  
	        timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
	}
	
	public void checkTimeOut(){
		 TimerTask task = new TimerTask() {  
	            @Override  
	            public void run() {  
	            	AnalyzeService.checkTimeOut();
	            }  
	        };  
	        Timer timer = new Timer();  
	        long delay = 0;  
	        long intevalPeriod = 10 * 1000;  
	        // schedules the task to be run in an interval  
	        timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
	}
	
	public boolean putBatch(String tablename, String rowProperty,
			String columnFamily, List<RealData> list, boolean addRowVariable) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			HTable table = hbase.getTable(tablename);
			table.setAutoFlush(false);
			for (RealData realData : list) {
				Put p1 = null;
				String row=realData.getEpId()+ (addRowVariable ? "_"
						+ sdf.format(new Date()) : "");
				p1=new Put(Bytes.toBytes(row));
				p1.add(Bytes.toBytes(columnFamily),Bytes.toBytes("epId"),
						Bytes.toBytes(realData.getEpId()));
				p1.add(Bytes.toBytes(columnFamily),Bytes.toBytes("epCode"),
						Bytes.toBytes(realData.getEpCode()));
				p1.add(Bytes.toBytes(columnFamily),Bytes.toBytes("epGunNo"),
						Bytes.toBytes(realData.getEpGunNo()+""));
				p1.add(Bytes.toBytes(columnFamily),Bytes.toBytes("dataType"),
						Bytes.toBytes(realData.getDataType()+""));
				int dataType=realData.getDataType();
				for(Map.Entry<Integer, String> entry:realData.getPointMap().entrySet()){
					p1.add(Bytes.toBytes(columnFamily),Bytes.toBytes(dataType+"_"+entry.getKey()),
							Bytes.toBytes(entry.getValue()+""));
				}
				p1.add(Bytes.toBytes(columnFamily),Bytes.toBytes("epId"),
						Bytes.toBytes(realData.getEpId()));
				table.put(p1);
			}
			table.flushCommits();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	
	public static Map getData(String tablename,String row){
		return hbase.getDataWithRowKey(tablename, row);
	}
	
	public static List<Map<String, String>> getList(String tablename,List<Filter> arr,long minStamp,long maxStamp){
		return hbase.selectByFilter(tablename, arr,null,minStamp, maxStamp);
	}

	public static void main(String[] args) {
		RealtimeUtil util = RealtimeUtil.getInstance();
		Date d1=new Date();
		List list=new ArrayList();
//		for(int i=0;i<1;i++){
//			//
//			AcRealData data=new AcRealData();
//			data.setCar_Place_Lock_Status( 1);
//			data.setEp_code(UUID.randomUUID().toString());
//			data.setEp_gun_no(111);
//			list.add(data);
//			//System.out.println(util.addRealtimeData(data));
//		}
		//util.saveDatas();
//		util.dropTable("history");
//		util.dropTable("realtime");
//		util.dropTable("AcRealData");
//		util.dropTable("DcRealData");
//		util.dropTable("AcRealDataHistory");
//		util.dropTable("DcRealDataHistory");
//		util.createTable("AcRealData", "sx");
//		util.createTable("DcRealData", "sx");
//		util.createTable("AcRealDataHistory", "sx");
//		util.createTable("DcRealDataHistory", "sx");
//		util.createTable("CommitLog", "sx");
//		util.createTable("storm-hbase2", "cf");
//		util.createTable("WordCount2", "cf");
		/*util.dropTable();
		util.createTable();*/
		//util.addRealtimeDatas(list,"jl");
//		System.out.println(hbase.getDataWithColumnFamily(tablename, "3301021010000006"));
//		System.out.println("实时:"+hbase.scanWithColumnFamily(tablename));
//		System.out.println("历史:"+hbase.scanWithColumnFamily(tablenameHistory).size());
//		System.out.println(getData(RealtimeConstant.TABLE_REALTIME_ZL, "3301060017038202"));
//		System.out.println(System.currentTimeMillis()-d1.getTime());
		
		try {
			long t=System.currentTimeMillis();
			List<Filter> arr=new ArrayList<Filter>();  
			arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx", "epCode", "3205020010000002").buildFilter());  
			arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx", "3_1", "3").buildFilter());  
			List<Map<String, String>> chargingList=getList("DcRealDataHistory", arr, t-43200000, t);
			Map<String, String> startObj=null;
			if(!chargingList.isEmpty()){
				startObj=chargingList.get(chargingList.size()-1);
			}
			arr=new ArrayList<Filter>();
			arr.add(new FilterBuilder(RealtimeConstant.FITER_EQUAL, "sx", "epCode", "3205020010000002").buildFilter());  
			List<Map<String, String>> chargingList2=getList("DcRealDataHistory", arr, new Long(startObj.get("ts")), t);
			System.out.println(chargingList2.size());
			//System.out.println(hbase.QueryRealData("3301020010000001"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
