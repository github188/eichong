<%@page import="com.sun.org.apache.bcel.internal.generic.SIPUSH"%>
<%@page import="java.text.SimpleDateFormat,java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>个人中心</title>
<jsp:include page="../common/header-css.jsp" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	Date d=new Date();
	String endDate=sdf.format(d);
	d=new Date(d.getTime()-7*24*3600*1000);
	String startDate= sdf.format(d);
%>

</head>
<body style="background:#fff;">
<input type="hidden" id="lb" value="${lb}"/>
	<div id="wrapper">
		<!--//header -->
		<jsp:include page="../common/header2.jsp" />

		<!-- start content -->
		<div class="detail">
  			<div class="ChannelContent pad_top50 pad_bot50">
				<div class="divGlobal">
					<div class="UserLeft">
					    <dl>
					      <dt class="UserLeftTitle" style="cursor:pointer;">个人中心</dt>
					      <dd class="UserLeftDdNav UserLeftDd"  data-id="modifyUser"><a>我的资料</a></dd>
					      <dd class="UserLeftDdNav" data-id="wallet"><a>我的钱包</a></dd>
					      <dd class="UserLeftDdNav" data-id="myMsg" id="changeColor"><a>我的消息</a></dd>
					      <dd class="UserLeftDdNav" data-id="collect"><a>我的收藏</a></dd>
					      <!-- <dd class="UserLeftDdNav"><a>我的申请</a></dd> -->
					      <!-- <dd class="UserLeftDdNav"><a>修改密码</a></dd> -->
					      <!--下边三个空的留着--> 
					      <dd class="UserLeftDdNav2" ></dd>
					      <dd class="UserLeftDdNav2" ></dd> 
					      <dd class="UserLeftDdNav2" ></dd>        
					    </dl>
					  </div>

					<div class="UserRight" >
					<!--个人中心主页面start -->
					<div class="UserRightWarp" id="maininfo" style="display: none;">
				      <div class="Username">
				        <dl><dt><span class="UserHeadImg"><img id="fileImg10" class="userImg" src="" onerror="javascript:this.src='<%=basePath%>/static/images/user/2.png';" width="200" height="200" />
				        </span>				        	
				        </dt>
				          <dd class="UsernameLeft">
				            <div class="UsernameMein userName"><span><a class="logoutAll">注销</a></span></div>
				            <div style="margin-top: 10px;"><a href="/apply.html" target="_Blank">申请建桩</a></div>
				          </dd>
				        </dl>
				        <dl class="CC">
				          <dd><a  href="#" class="recharge" >充值</a>
				            <u>小充充：</u><span><t class="normAccountBalance">${user.normAccountBalance}</t></span><u>元</u></dd>
				        </dl>          
				      </div>
<!-- 				      <div class="gonggao">
				        <span>公告</span>暂无公告module的定义
								名词
							each of a set of standardized parts or independent units that can be used to construct a more complex structure, such as an item of furniture or a building.
				      </div> -->
				      <div class="Module ">
				        <h1>我的消息</h1>
				        <dl id="msgLiTitle">
				        </dl>
				      </div>
				      <div class="Module ">
				       <h1>消费记录</h1>
				        <dl class="CCContents" style="margin-top:0;">
				          <!-- <dt>暂无消费记录</dt>-->
				          <dd class="TablesTitle"><span class="fenleiC">金额（单位：元）</span><span class="fenleiB">时间</span><span class="fenleiA">类型</span></dd>
				          <dd id="walletDivTitle"></dd>
				          
				          			          
				        </dl>
				      </div>          
				</div>
				<!-- 个人中心首页的消息详情 -->
						<div class="ModuleTwo UserRightWarp myMsgInfo" id="msgDetailDivTitle" style="display: none;">							
								<h2 id="msgTitleTitle" style="font-weight: bold;font-size: 1.5em;"></h2>
								<h3>
									时间：<span id="msgTimeTitle"></span> 发送者：<span id="msgFromTitle"></span>
								</h3>
								<p id="msgContentTitle"></p>
								
								<!-- <div style="margin:0 auto; width:80px;"><a  class="anniu" href="#">返回</a></div> -->
								<h3 style="margin:0 auto; width:80px;">
									<a class="anniu" href="#" id="backToTitle"> 返回</a>
								</h3>								
						</div>
					<!--个人中心主页面end -->
					<!--系统消息 start  -->						 
				         <div class="ModuleTwo UserRightWarp systemMsgInfo" id="systemMsgDetailDiv" style="display: none">							  									
										<h2 id="systemMsgTitle" style="font-weight: bold;font-size: 1.5em;"></h2>
										<h3>
											时间：<span id="systemMsgTime"></span> 发送者：<span id="systemMsgFrom"></span>
										</h3>
										<p id="systemMsgContent"></p>   
											<h3 style="margin:0 auto; width:80px;">
										    	<a  class="anniu" href="#" id="systemBackToMsg">返回</a>
										    </h3>      												
							</div> 
						
					
						<!--我的消息 start  -->
						<div class="UserRightWarp" id="myMsg" style="display: none;">
					      <div class="ModuleTwo myMsgDiv" id="myMsgDiv">
					        <h1>我的消息<!-- <span>（有<t id="unreadMsg"></t> 条未读）</span> --></h1>
					            <dl id="messageAllTab" class="ModuleTwoTitle">
						          <dd id="sysMsg" class="ModuleTwoNav"><a id="systemMessage" class="msgTab noSelect">系统通知</a></dd>
						          <dd id="perMsg" class="ModuleTwoNav"><a id="personMessage" class="msgTab select">我的私信</a></dd> 
						          <dd id="perFeedback"class="ModuleTwoNav"><a id="personFeedback" class="msgTab select">我的反馈</a></dd>
						        </dl>  
					        <dl id="systemMessage1" class="">
<!-- 					          <dd class="TextOne zhihui"><span>2015/7/24 下午1:08:16</span><a href="#">《杭州充电桩地图》想出炉，请大家来帮忙</a></dd>
					          <dd class="TextOne"><span>2015/7/24 下午1:08:16</span><a href="#">《杭州充电桩地图》想出炉，请大家来帮忙</a></dd>				          
 -->					    </dl>
					        <ul id="personMessage1" class="sixin " style="display: none;">
							          						         
        					 </ul>					      
					         
					          <ul  id="personFeedback1" style="display: none;" class="sixin  ModuleTwoTitle">
							          <!-- <li>
							          <span class="sixinTime">2015/7/24 下午1:08:16<span class="sixinJieguo Red"><b>未受理</b></span></span>
							          <span class="sixinUserName"><b>反馈的标题呀</b></span>
							          <span class="sixinText">技巧
							如果用户希望延长电池的有效使用时间，除了充电器的质量要有保证外</span>
							          </li>   -->       
       						  </ul>
					       </div> 					
					    </div>					   
<!-- 						<div class="UserRightWarp myMsgInfo" id="msgDetailDiv" >
								<h4 id="msgTitle"></h4>
								<h5>
									时间：<span id="msgTime"></span> 发送者：<span id="msgFrom"></span>
								</h5>
								<p id="msgContent"></p>
								<h3 style="margin-top: 30px;">
									<a id="backToMsg"> 返回</a>
								</h3>
						</div> -->
						
				
					
										      
					
						<!--我的消息 end  -->
						<!--我的钱包 start  -->
						<div class="UserRightWarp" id="wallet" style="display: none;padding-bottom:1px;">
					      <div class="ModuleFour">        
					        <dl class="CC H80">
					          <dt>我的钱包</dt>
					          <dd><a class ="recharge" href="#">充值</a><u>小充充：</u><span>${user.normAccountBalance}</span><u>元</u></dd>
					        </dl>
					        <dl class="CCTitle">
					          <dd class="CCNav" ><a class="walletTab select" data-value="consumeType">消费记录</a></dd>
					          <!-- <dd class="CCNav"><a class="walletTab unselect" data-value="earnType">我的收益</a></dd> -->
					        </dl>
					        <dl class="CCTitle">
					          <dt><span style="float:right;">
					          <input class="w120rili" type="text" name="textfield" id="walletStartDate"  value="<%=startDate%>" 
					          onfocus="WdatePicker({el:'walletStartDate',onpicked:loadWallets(1,10),minDate:'2015-01-01',maxDate:'#F{$dp.$D(\'walletEndDate\')}'})" readonly/>
					          		至
					          <input class="w120rili" type="text" name="textfield" id="walletEndDate"  value="<%=endDate%>"  
					          onfocus="WdatePicker({el:'walletEndDate',onpicked:loadWallets(1,10),minDate:'#F{$dp.$D(\'walletStartDate\')}'})" readonly/></span>
					          <select class="selectStyle" id="consumeType" onchange="loadWallets()">
									<option value="0">所有消费</option>
									<option value="1">充电</option>
									<option value="2">预约</option>
									<option value="3">购物</option>
									<option value="4">充值</option>
					            </select></dt>
					        </dl>
					        <dl class="CCContents">
					         <!-- <dt>当前没有消费记录</dt>-->
					          <dd class="TablesTitle"><span class="fenleiC">金额（单位：元）</span><span class="fenleiB">时间</span><span class="fenleiA">类型</span></dd>
					          <t id="walletDiv">
							  </t>
					        </dl>                                                     
					      </div>
						</div>
						<!--我的钱包 end  -->
						<!--我的收藏 start  -->
		<div class="UserRightWarp" id="collect" style="display: none;">
		<div class="ModuleFives">
        <h1>我的收藏</h1>
        <c:if test = "${collectListSize != 0}"> 
        <c:forEach items="${collectList}" var="collect">       
	           <dl class="SC">
			          <dt><a target="_blank" href="<%=basePath%>/web/<c:if test="${collect.uscoType==2}">powerstation</c:if><c:if test="${collect.uscoType==3||collect.uscoType==4}">electricpile</c:if>/detail.do?eid=${collect.uscoObjectid}">
							${collect.objName}</a></dt>
			          <dd class="SCadd">地址:${collect.objAddress}</dd>
			          <dd class="SCtime"><span class="SCtimeA">收藏于：<fmt:formatDate value="${collect.uscoAddtime}" type="both"/></span>
			          <span class="SCtimeB">
			          <a class="collect-cancel"  data-id="${collect.pkUsercollect}">取消收藏</a></span></dd>
			          <dd></dd>
			          <dd></dd>
	        	</dl> 
        </c:forEach> 
        	</c:if>        	
        	<c:if test = "${collectListSize == 0}">
        	  <dl class="SC">			          
			           <dd class="SCtime">暂 无 收 藏</dd>	         
	        	</dl>
        	</c:if>                                                                               
	      </div>
		</div>
						<!--我的收藏 end  -->
						<!--我的评论 start  -->
			<!-- 			<div class="UserRightWarp" id="comment" style="display: none;">
							<h3>我的评论</h3>
							<div id="comments">
							</div>							
						</div> -->
						<!--我的评论 end  -->
						<!--桩体分享 start  
				<%-- 		<div class="UserRightWarp" id="pileShare"
							style="display: none;">
							<form name="pileShareForm" id="pileShareForm"
								enctype="multipart/form-data" method="post"
								action="<%=basePath%>/web/publishEp/add.do">
								<input type="hidden" name="longitude" value="0.0" /> <input
									type="hidden" name="latitude" value="0.0" /> <input
									type="hidden" id="img" name="img" value="" /> <input
									type="hidden" id="parameter_note" name="parameter_note"
									value="" />
								<h3>桩体分享</h3>
								<p>
									<input name="address" type="text" class="UserProfileA"
										placeholder="输入电桩的地址信息"><b>*</b><br />
									添加桩体照片：(建议图片长宽比2:1，例如600px×300px)<br /> <a
										onclick="file1.click()"><img id="fileImg1" class="tianjia"
										src="<%=basePath%>/static/images/prettyPhoto/user_imgadd.png"
										width="100" height="50" alt="添加图片" /></a> <a
										onclick="file2.click()"><img id="fileImg2" class="tianjia"
										src="<%=basePath%>/static/images/prettyPhoto/user_imgadd.png"
										width="100" height="50" alt="添加图片" /></a> <a
										onclick="file3.click()"><img id="fileImg3" class="tianjia"
										src="<%=basePath%>/static/images/prettyPhoto/user_imgadd.png"
										width="100" height="50" alt="添加图片" /></a> <a
										onclick="file4.click()"><img id="fileImg4" class="tianjia"
										src="<%=basePath%>/static/images/prettyPhoto/user_imgadd.png"
										width="100" height="50" alt="添加图片" /></a> <a
										onclick="file5.click()"><img id="fileImg5" class="tianjia"
										src="<%=basePath%>/static/images/prettyPhoto/user_imgadd.png"
										width="100" height="50" alt="添加图片" /></a> <input type="file"
										id="file1" name="file1" size="1" class="filehidden" /> <input
										type="file" id="file2" name="file1" size="1"
										class="filehidden" /> <input type="file" id="file3"
										name="file1" size="1" class="filehidden" /> <input
										type="file" id="file4" name="file1" size="1"
										class="filehidden" /> <input type="file" id="file5"
										name="file1" size="1" class="filehidden" /> 
									<select name="maker" id="select" class="UserProfileB">
										<option value="">电桩的制造厂商</option>
										<c:forEach var="item" items="${param11}"> 
											<option value="${item.key}">${item.value.coCo_Content}</option>
										</c:forEach>
									</select><b>*</b> <br /> <select name="chargingMode" id="select"
										class="UserProfileB">
										<option value="">请选择充电方式</option>
										<c:forEach var="item" items="${param3}"> 
											<option value="${item.key}">${item.value.coCo_Content}</option>
										</c:forEach>
									</select><b>*</b> <br /> <select name="powerInterface" id="select"
										class="UserProfileB">
										<option value="">请选择接口方式</option>
										<c:forEach var="item" items="${param5}"> 
											<option value="${item.key}">${item.value.coCo_Content}</option>
										</c:forEach>
									</select><b>*</b> <br /> 
									<select name="powerSize" id="select" class="UserProfileB">
										<option value="">请选择额定功率</option>
										<c:forEach var="item" items="${param4}"> 
											<option value="${item.key}">${item.value.coCo_Content}</option>
										</c:forEach>
									</select><b>*</b> <br />

									<textarea name="note" class="UserProfileA"
										placeholder="输入经纬度坐标及其他相关信息"></textarea>
									<br /> <input class="submitANDreset" type="button"
										name="button" id="pileShareBtn" value="提交" /><input
										class="submitANDreset" type="reset" name="button" id="button"
										value="重置" />
								</p>
							</form>
						</div>
						桩体分享 end  -->
						<!--我的预约 start  -->
						<div class="UserRightWarp" id="besp" style="display: none;">
							<form name="yuyueForm" id="yuyueForm">
								<h3>我的预约</h3>
<!-- 								<span id="aaaa"></span> -->
								<dl class="yydq bespCurrent">
								</dl>
								<dl class="yygq bespFinish">
								</dl>
							</form>
						</div>
						<!--我的预约 end  -->
						<!--我的反馈 start  -->
						<div class="UserRightWarp" id="feedBack"
							style="display: none;">
							<form name="feedBackForm" id="feedBackForm">
								<h3>我的反馈</h3>
								<p>
									<textarea name="content" class="UserProfileA"
										placeholder="填写反馈信息"></textarea>
									<br /> <input class="submitANDreset" type="button"
										name="button" id="feedBackBtn" value="提交" /><input
										class="submitANDreset" type="reset" name="button" id="button"
										value="重置" />
								</p>
								<div id="loadfeedbacks">
							
								</div>
							</form>
						</div> --%>
						<!--我的反馈 end  -->
						<!--修改账号 start  -->
						<div class="UserRightWarpTwo" id="modifyUser"
							style="display: none;">
							<form name="modifyUserForm" id="modifyUserForm"
								enctype="multipart/form-data" method="post"
								action="<%=basePath%>/web/user/updateUserInfo.do">
								 <div class="ModuleThree">
        							<h1>我的资料<span>（完善资料，爱充可以更好的为您服务，比如快速为您的爱车匹配充电点等）</span></h1>
									<dl>
								          <dd>
								            <div class="ModuleBoxLeft">用户昵称</div>
								            <div class="ModuleBoxRight"><span class="ModuleName">${user.normName}</span></div>
								          </dd>
								          <dd>
								            <div class="ModuleBoxLeft">手机号码</div>
								            <div class="ModuleBoxRight"><input class="w160" type="text" style="border:0px;background-color:#eee;font-size: 18px;font-weight: bold;color: #333;margin-right: 20px;" name="userAccount" value="${user.userAccount}" placeholder="手机号码" readonly /></div>
								          </dd> 
								          <dd>
								            <div class="ModuleBoxLeft">显示头像</div>
								            <div class="ModuleBoxRight">
												<img id="fileImg6" class="userImg" src="${user.userImage}"
												onerror="javascript:this.src='<%=basePath%>/static/images/user/2.png';"
												width="200" height="200" />
												<a href="javascript:void(0)" class="anniu" onclick="file6.click()">更换头像</a>
												<input type="file" id="file6" name="file" size="1" class="filehidden" />
											</div>
								          </dd>
								          
								          <dd>
								            <div class="ModuleBoxLeft">账号密码</div>
								            <div class="ModuleBoxRight"><a href="javascript:void(0)" id="modifyPwd"  class="anniu" >修改密码</a></div>
								          </dd>
								          <dd>
								            <div class="ModuleBoxLeft">真实姓名</div>
								            <div class="ModuleBoxRight">					
								                <input class="w160" type="text" name="normRealName"  value="${user. normRealName}"/>
								            </div>
								          </dd>
								          
										   <dd>
								            <div class="ModuleBoxLeft">用户性别</div>
								            <div class="ModuleBoxRight">
									              <form  style=" list-style:none; margin:0; padding:0; width:200px;" id="form1" name="form1" method="post" action="">		
									                  <label>
									                  	<input type="radio" name="normSex" value="0"   checked />
									              	            保密
									                    </label>						
									                  	<label>
									                    <input type="radio" name="normSex" value="2"  <c:if test="${user.normSex=='2'}">checked</c:if> />
									                 	   女</label>								
									                  	<label>
									                    <input type="radio" name="normSex" value="1" <c:if test="${user.normSex=='1'}">checked</c:if> />
									                  	  男</label>														
									              </form>
								              </div>
								          </dd>
								           
								          
								          <dd>
								            <div class="ModuleBoxLeft">出生日期</div>
								            <div class="ModuleBoxRight">
								            <input class="w120rili" type="text" name="normBirthday"  
								            	value="${user.normBirthday}" onClick="WdatePicker()"  />
								            </div>	
								          </dd>
								          
								          <dd>
								            <div class="ModuleBoxLeft">电子邮箱</div>
								            <div class="ModuleBoxRight">
								            <input class="w160" type="text" name="normEmail"  value="${user.normEmail}"/></div>
								          </dd>
								          <dd>
								            <div class="ModuleBoxLeft">家庭地址</div>
								            <div class="ModuleBoxRight">
								            <input class="w400" type="text" name="normAddress" value="${user.normAddress}"  /></div>
								          </dd>  
								          <dd>
								            <div class="ModuleBoxLeft">IC卡号</div>
								            <div class="ModuleBoxRight">
								            <input class="w160" type="text" name="normIdCard"  value="${user.normIdCard}"/></div>
								          </dd>  
								          <dd>
								            <div class="ModuleBoxLeft">驾驶证号</div>
								            <div class="ModuleBoxRight">
								            <input class="w160" type="text" name="normDrivingLicence" value="${user.normDrivingLicence}" /></div>
								          </dd>
								           <dd>
								            <div class="ModuleBoxLeft">汽车品牌</div>
								            <div class="ModuleBoxRight">
								            <select class="selectStyle" name="normCarCompanyId"
								            data-value="${user.normCarCompanyId}" id="qcpp" placeholder="汽车品牌"
											onchange="getmodels()">
								             <option></option>
								            </select></div>
								          </dd>
								          <dd>
								            <div class="ModuleBoxLeft">品牌车型</div>
								            <div class="ModuleBoxRight">
								            <select class="selectStyle" name="normCarTypeId" data-value="${user.normCarTypeId}"
								            id="ppcx" placeholder="品牌车型">
								              <option></option>
								            </select></div>
								          </dd>
								          <dd style=" padding-top:15px; margin-top:20px; ">
									        <div class="tijiao"><a id="modifyUserBtn">提交</a></div>
								          </dd> 
									</dl>	
								 </div>
							</form>
						</div>
<!-- 						    <div class="UserRightWarpTwo">
							      <div class="ModuleThree">
							        <h1>修改密码</h1>
							        <dl>
							          <dd>
							            <div class="ModuleBoxLeft">输入密码</div>
							            <div class="ModuleBoxRight">
							                <input class="w160" type="text" name="textfield" id="textfield" />
							            </div>
							          </dd> 
							          <dd>
							            <div class="ModuleBoxLeft">输入新密码</div>
							            <div class="ModuleBoxRight">
							                <input class="w160" type="text" name="textfield" id="textfield" />
							            </div>
							          </dd>          
							          <dd>
							            <div class="ModuleBoxLeft">再次输入新密码</div>
							            <div class="ModuleBoxRight">
							                <input class="w160" type="text" name="textfield" id="textfield" />
							            </div>
							          </dd>          
							          <dd style=" padding-top:15px; margin-top:20px; ">
							            <div class="tijiao"><a href="#">提交</a></div>
							          </dd>                   
							        </dl>
							      </div>                                                      
							    </div> -->												
						<!-- 支付宝充值 充充页面 start-->
						 <div class="UserRightWarp" id = 'recharge' style="display: none;">
							 <form id="chargeForm"  action= "<%=basePath%>/alipay/deposit.do" method="post" target="_blank">  	
								 <h3>充值</h3>
								 <p>   
								      选择充值方式：
								      <br /><br />
								 </p>								
								 <p>
								       <label>
								              <img style="vertical-align:middle;" src="<%=basePath%>/static/images/prettyPhoto/img-zhifu-alibaba.png" width="120" height="80" />支付宝<input type="radio" name="chargeType" value="1" checked />
								       </label>
								            <br />
								       <label>
								              <img style="vertical-align:middle;" src="<%=basePath%>/static/images/prettyPhoto/img-zhifu-yinlian.png" width="120" height="80" />银联<input type="radio" name="chargeType" value="2"  />
								       </label>
								            <br /><br />
								  <select  id="amount" name="amount" class="UserProfileB" style="width:150px;">
										  <option value="10">充值10元</option>
										  <option value="20">充值20元</option>
										  <option value="50">充值50元</option>
										  <option value="100">充值100元</option>
										  <option value="500">充值500元</option>
								   </select>
								  <br /><br>
								  <input class="submitANDreset" id="chargeButton" type="button" name="button"  value="立即充值" />
								 </p>
							</form>	
	  							    </div>
                        <!-- 支付宝充值 充充页面 end-->
						<!--修改账号 end  -->
						<!--修改密码 start  -->
						<div class="UserRightWarp" id="modifyPwd"
							style="display: none;">
							<form name="modifyPwdForm" id="modifyPwdForm">
								<h3>修改密码</h3>
								<p>
									<input name="usinPassword" type="text" class="UserProfileA"
										value="请输入新密码"><br /> <input name="rePassword"
										type="text" class="UserProfileA" value="再次输入新密码"> <br />
									<input class="submitANDreset" type="button" name="button"
										id="modifyPwdBtn" value="提交" /><input class="submitANDreset"
										type="reset" name="button" id="button" value="重置" />
								</p>
							</form>
						</div>
						<!--修改密码 end  -->
						<div style="clear:both;"></div> 
						<div style="padding-bottom:100px;">
							<div class="floatL fanye" id="pageEle" style="padding:40px 0 0 70px;"></div>
						</div>
					</div>
				</div>				
  </div><div style="clear:both;"></div> 
  
  
</div>
<!-- end content -->
<!-- <div class="LoginBox" id="yuyueWindow" style="display:none;">
<form id="bespForm" method="post" >
<input type="hidden" id="pkBespoke" name="pkBespoke" />
<input type="hidden" id="bespElectricpileid" name="bespElectricpileid" />
<input type="hidden" id="bespBespoketime" name="bespBespoketime"/>
<input type="hidden" id="bespElectricpilehead" name="bespElectricpilehead" />
<input type="hidden" id="bespUserinfo" name="bespUserinfo"/>
<input type="hidden" id="bespResepaymentcode" name="bespResepaymentcode" />
<input type="hidden" id="bespFrozenamt" name="bespFrozenamt"/>
<input type="hidden" id="bespBespokeprice" name="bespBespokeprice"/>
<input type="hidden" id="bespBeginTime" name="bespBeginTime"/>
<input type="hidden" id="bespEndTime" name="bespEndTime"/>
</form>
    <dl class="LoginBoxDengLu">
      <dt><a id="closeYuyue">×</a>续约</dt>
      <h5 class="yy-A"><span id="pileHeadName"></span>状态：<span id="pileHeadStatus"></span></h5>
      <h5 class="yy-B yy">当前时间：<span id="showTime">00:00:00</span></h5>
      <h5 class="yy-B yy">设置续约时间，最多可续约<span id="xuyueTime">6</span>小时</h5>
      <h5 class="yy-C yy">
		  <div id="jslider"></div>
	  </h5>
      <h5 class="yy-D yy">当前续约<span id="yuyueTime" style="display:inline-block;width:25px;">0</span>小时,费用<span id="yuyuePrice" style="display:inline-block;width:50px;">0</span>元</h5>
      <dd><input id="submitBesp" class="sub" type="button" name="type" value="提交续约"></dd>
      <h5 class="yy-E yy">续约须知</h5>
      <h5 class="yy-F">用户续约之后，可以买断从当前时间开始，到用户本人预计到达充电地点这段时间，保证用户到了后可以顺利充到电...用户提前（预约买断时间以30分钟为最小单位、最大预约6小时）</h5>
    </dl><div class="clear"> </div>
</div>	 -->	
<!--//footer-->
</div>
<div class="clear"> </div>
<script type="text/javascript">
var this_tag = "userCenter";
</script>
<jsp:include page="../common/footer2.jsp" />
<jsp:include page="../common/footer-js.jsp" />
<link rel="stylesheet" href="<%=basePath%>/static/lib/jquery-ui/css/jquery-ui.min.css" />
<script type="text/javascript" src="<%=basePath%>/static/lib/jquery-ui/js/jquery-ui.min.js?<%=Math.random()%>"></script>
<script type="text/javascript" src="<%=basePath%>/static/js/common/page.js" ></script>
<script type="text/javascript" src="<%=basePath%>/static/js/mycenter/my-center.js?<%=Math.random()%>"></script>
<script type="text/javascript" src="<%=basePath%>/static/lib/ajaxupload/jquery-form.js?<%=Math.random()%>"></script>
<script type="text/javascript" src="<%=basePath%>/static/lib/My97DatePicer/WdatePicker.js?<%=Math.random()%>"></script>
</body>
</html>
