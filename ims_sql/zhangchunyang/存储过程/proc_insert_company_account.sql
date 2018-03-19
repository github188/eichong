BEGIN
		DECLARE cpyNumber				BIGINT(11);				#公司Id
		DECLARE	pwd					VARCHAR(32);			#密码
		DECLARE balance     DECIMAL(10, 2);		#账户余额
		DECLARE warnMoney   DECIMAL(10, 4);		#预警金额
	  DECLARE accountId		BIGINT(11);				#资金账户Id
		DECLARE accountNo		VARCHAR(32);			#资金账号
		DECLARE tradeType   INT;							#付费方式
		DECLARE num BIGINT DEFAULT 0;					#数量
		DECLARE i INT;												#偏移量
		DECLARE count				int;
		DECLARE userId				BIGINT(11);

    #公司游标
    DECLARE company_cursor CURSOR FOR
				SELECT cpy_number,trade_type, account_no
				FROM tbl_company;

		#用户生成对应的资金账户
		set i = 0;
		SELECT COUNT(1) INTO num FROM tbl_company;
		OPEN company_cursor;
			WHILE i < num DO
				fetch company_cursor into cpyNumber, tradeType, accountNo;

				SELECT count(1)
					into count
					FROM
						tbl_user t,
						tbl_user_normal_old normal
					WHERE
						t.user_id = normal.user_id
						AND LENGTH(t.user_account) = 12 
						and t.user_account = accountNo;
				
				SELECT fun_build_accountno(2, cpyNumber) INTO accountNo;

				IF count < 1 THEN
						INSERT INTO fin_account
								VALUES(null, accountNo, 'e10adc3949ba59abbe56e057f20f883e', 0, tradeType, 0, 1, 0, '', '', NOW(), NOW());
				ELSE
						SELECT
							n.norm_account_balance,
							n.norm_pay_password
						INTO balance, pwd
						FROM
							tbl_user_normal_old n inner join tbl_user t on t.user_id = n.user_id
						WHERE
							LENGTH(t.user_account) = 12 
							and t.user_account = accountNo;

						IF pwd is null THEN
							SET pwd = 'e10adc3949ba59abbe56e057f20f883e';
						END IF;

						IF balance is null THEN
							SET balance = 0;
						END IF;

						INSERT INTO fin_account
							VALUES(null, accountNo, pwd, balance, tradeType, 0, 1, 0, '', '', NOW(), NOW());
				END IF;

				select @@identity INTO accountId;

				update tbl_company set account_id = accountId where cpy_number = cpyNumber;
				SET i = i + 1;
			END WHILE;
		CLOSE company_cursor;
 END