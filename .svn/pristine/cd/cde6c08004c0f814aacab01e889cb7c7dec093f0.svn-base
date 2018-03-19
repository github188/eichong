BEGIN
		DECLARE cpyId				BIGINT(11);				#公司Id
		DECLARE accountId		BIGINT(11);				#资金账户Id
    DECLARE tradeType   INT;							#付费方式
		DECLARE num BIGINT DEFAULT 0;					#数量
		DECLARE i INT;												#偏移量

		#公司游标
		DECLARE cpy_cursor CURSOR FOR
				SELECT cpy_id, account_id
				FROM tbl_company;
				

		set i = 0;
		SELECT COUNT(1) INTO num FROM tbl_company where pay_rule = 1;
		OPEN cpy_cursor;
			WHILE i < num DO
				fetch cpy_cursor into cpyId, accountId;
				
				INSERT INTO fin_account_config_rela VALUES(null, cpyId, 1, 1, 1, '', '', NOW(), NOW());

				UPDATE tbl_user_normal set account_id = accountId where cpy_id = cpyId;

				UPDATE tbl_usercard set account_id = accountId where cpy_id = cpyId;

				CALL proc_gen_account_relation(cpyId, accountId);
				
				SET i = i + 1;
			END WHILE;
		CLOSE cpy_cursor;

END