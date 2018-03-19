<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no, minimum-scale=1.0, maximum-scale=1.0"/> 
<title>Insert title here</title>
<style type="text/css">
.big{
     font-size:18px;
     color:#000000;
     font-weight:bold;
   }
.b{
	 font-size:15px;
     color:#FFFFFF;
	 background-color:red;
 }
td{
	 font-size:14px;
     color:#6B6B6B;
  }
  hr{
  color:#BFBFBF;
  }
</style>
</head>
<body>
  <center><table border="0">
  	<c:if test="${state==1}">
  		<tr><td width="400" height="300" align="center"><img width="400" height="300" src="${sharePowerstation.postPic}"></td></tr>
  		<tr><td height="30" align="center" class="big">${sharePowerstation.postName}</td></tr>
  		<tr><td height="30" align="center">${star}星&nbsp;&nbsp;${commentcount}评论</td></tr>
  		<tr><td height="3" align="center"><hr></td></tr>
  		<tr><td height="20" align="center">地址：${sharePowerstation.postAddress}</td></tr>
  		<tr><td height="3" align="center"><hr></td></tr>
  		<tr><td height="20" align="center">联系电话：${sharePowerstation.postPhone}</td></tr>
  		<tr><td height="3" align="center"><hr></td></tr>
  		<tr><td height="20" align="center">${electricpilecount}个充电桩</td></tr>
  	</c:if>
  	<c:if test="${state==2}">
  		<tr><td width="400" height="300" align="center"><img src="${newTblElectricpile.elpiImage}"></td></tr>
  		<tr><td height="30" align="center" class="big">${newTblElectricpile.elpiElectricpilename}</td></tr>
  		<tr><td height="30" align="center">${star}星&nbsp;&nbsp;${commentcount}评论</td></tr>
  		<tr><td height="3" align="center"><hr></td></tr>
  		<tr><td height="20" align="center">地址：${newTblElectricpile.elpiElectricpileaddress}</td></tr>
  		<tr><td height="3" align="center"><hr></td></tr>
  		<tr><td height="20" align="center">联系电话：${newTblElectricpile.elPi_Tell}</td></tr>
  		<tr><td height="3" align="center"><hr></td></tr>
  		<tr><td height="20" align="center">${newTblElectricpile.elpiPowernumber}个枪口</td></tr>
  	</c:if>
  	<c:if test="${state==3}">
  		<tr><td width="400" height="300" align="center"><img src="${newtblProduct.prodProductimage}"></td></tr>
  		<tr><td height="30" align="center" class="big">${newtblProduct.prodProductname}</td></tr>
  		<tr><td height="30" align="center">${star}星&nbsp;&nbsp;${commentcount}评论</td></tr>
  		<tr><td height="3" align="center"><hr></td></tr>
  		<tr><td height="20" align="center">价格：${newtblProduct.prodProductprice}</td></tr>
  		<tr><td height="3" align="center"><hr></td></tr>
  		<tr><td height="20" align="center">已售出：${newtblProduct.prodSoldquantity}</td></tr>
  		<tr><td height="3" align="center"><hr></td></tr>
  		<tr><td height="20" align="center">浏览量：${newtblProduct.prodBrowsenum}</td></tr>
  	</c:if>
  	<c:if test="${type==1}">
  		<tr><td height="35" align="center" class="b">下载app</td></tr>
  		</c:if>
  		<c:if test="${type==2}">
  		<tr><td height="35" align="center" class="b">下载app</td></tr>
  	</c:if>
  </table></center>
</body>
</html>