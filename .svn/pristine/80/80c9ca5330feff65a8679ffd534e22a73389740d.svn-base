BEGIN
	DECLARE retAccountNo VARCHAR(32);     #返回的accountNo
  DECLARE strId 	 		 VARCHAR(50);     #id转换成字符串
	DECLARE strIdLen		 INT;							#id的长度
	DECLARE times				 VARCHAR(20);			#时间戳
	DECLARE randNum			 INT;							#随机三位数
	##########################################################################################################
	### 资金账户号规则:
	### FA + 标识位：1.用户  2.公司 3.卡 + 标识位对应的ID(需补足5位) + 年月日时分秒毫秒的时间戳 + 修改的版本号
	##########################################################################################################

	set strIdLen = 0;
	set retAccountNo = 'FA';
	set retAccountNo = CONCAT(retAccountNo, sysType);

	set strId = CAST(ids AS CHAR);
	set strIdLen = LENGTH(strId);

	IF(strIdLen = 1) then
		set strId = CONCAT('0000', strId);
	ELSEIF(strIdLen = 2) then
		set strId = CONCAT('000', strId);
	ELSEIF(strIdLen = 3) then
		set strId = CONCAT('00', strId);
	ELSEIF(strIdLen = 4) then
		set strId = CONCAT('0', strId);
	END IF;

	set retAccountNo = CONCAT(retAccountNo,strId);
	set randNum = CEILING(RAND()*500 + 500);
	set times = CONCAT(DATE_FORMAT(NOW(), '%Y%m%d%H%i%s'), randNum);
	set retAccountNo = CONCAT(retAccountNo, times);
  set retAccountNo = CONCAT(retAccountNo, '10');

  RETURN retAccountNo;
  
END