BEGIN
		DECLARE userId	    BIGINT(11);				
		DECLARE	pwd					VARCHAR(32);			
		DECLARE balance     DECIMAL(10, 2);		
		DECLARE warnMoney   DECIMAL(10, 4);		
		DECLARE ucId				BIGINT(11);				
		DECLARE cpyId				BIGINT(11);				
		DECLARE accountId		BIGINT(11);				
		DECLARE accountNo		VARCHAR(32);			
		DECLARE tradeType   INT;							
		DECLARE num BIGINT DEFAULT 0;					
		DECLARE i INT;												
		DECLARE cnt BIGINT DEFAULT 0;	
		
		DECLARE user_cursor CURSOR FOR
				SELECT user_id, norm_pay_password, norm_account_balance
				FROM tbl_user_normal_old;
		
		DECLARE card_cursor CURSOR FOR
				SELECT pk_UserCard, uc_UserId
				FROM tbl_usercard_old where uc_Status = 0;

		
		set i = 0;
		SELECT COUNT(1) INTO num FROM tbl_user_normal_old;
		OPEN user_cursor;
			WHILE i < num DO
				fetch user_cursor into userId, pwd, balance;

				SELECT trade_type INTO tradeType FROM tbl_company WHERE cpy_number = 1000;

				SELECT fun_build_accountno(1, userId) INTO accountNo;

				INSERT INTO fin_account
				VALUES(null, accountNo, pwd, balance, tradeType, 0, 1, 0, '', '', NOW(), NOW());

				select @@identity INTO accountId;

				insert into fin_account_relation VALUES(null, userId, 1, accountId, 1, 0, '', '', NOW(), NOW());

				update tbl_user_normal set account_id = accountId where user_id = userId;
				SET i = i + 1;
			END WHILE;
		CLOSE user_cursor;

		
		set i = 0;
		SELECT COUNT(1) INTO num FROM tbl_usercard_old;
		OPEN card_cursor;
			WHILE i < num DO
				fetch card_cursor into ucId, userId;
				SELECT cpy_id INTO cpyId FROM tbl_usercard WHERE uc_id = ucId;

				SELECT pay_rule INTO tradeType FROM tbl_company WHERE cpy_id = cpyId;
				
				IF(tradeType = 2 or tradeType = 3) THEN
					SELECT count(1) INTO cnt FROM fin_account_relation WHERE user_id = userId;
					IF(cnt < 1) THEN
						SELECT user_password INTO pwd FROM tbl_user WHERE user_id = userId;
						
						SELECT fun_build_accountno(3, ucId) INTO accountNo;

						INSERT INTO fin_account
						VALUES(null, accountNo, pwd, 0, tradeType, 0, 1, 0, '', '', NOW(), NOW());

						select @@identity INTO accountId;

						insert into fin_account_relation VALUES(null, userId, 1, accountId, 1, 0, '', '', NOW(), NOW());

						update tbl_usercard set account_id = accountId where uc_id = ucId;
					END IF;
				END IF;
				SET i = i + 1;
			END WHILE;
		CLOSE card_cursor;
 END