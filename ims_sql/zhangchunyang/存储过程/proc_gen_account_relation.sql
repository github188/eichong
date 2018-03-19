BEGIN
		DECLARE userId	    BIGINT(11);				#用户Id
		DECLARE newUserId	  BIGINT(11);				#新用户Id
		DECLARE num BIGINT  DEFAULT 0;				#数量
		DECLARE i INT;												#偏移量
		DECLARE resUserId   BIGINT(11);				#最终用户Id

		#用户游标
		DECLARE user_cursor CURSOR FOR SELECT user_id FROM tbl_user_normal WHERE cpy_id = cpyId;

		#卡游标
		DECLARE card_cursor CURSOR FOR SELECT user_Id, new_user_id FROM tbl_usercard WHERE cpy_id = cpyId;

		#渠道用户游标
		DECLARE userCompany_cursor CURSOR FOR SELECT user_id FROM tbl_user_company WHERE cpy_id = cpyId;

		SET i = 0;
		SELECT COUNT(1) INTO num FROM tbl_user_normal WHERE cpy_id = cpyId;
		OPEN user_cursor;
			WHILE i < num DO
				fetch user_cursor into userId;
					insert into fin_account_relation VALUES(null, userId, 1, accountId, 1, 0, '', '', NOW(), NOW());
					set i = i + 1;
			END WHILE;
		CLOSE user_cursor;
		
		SET resUserId = 0;
		SET i = 0;
		SELECT COUNT(1) INTO num FROM tbl_usercard WHERE cpy_id = cpyId;
		OPEN card_cursor;
			WHILE i < num DO
				fetch card_cursor into userId, newUserId;
					IF newUserId != 0 THEN
						SET resUserId = newUserId;
					ELSE
						SET resUserId = userId;
					END IF;

					insert into fin_account_relation VALUES(null, resUserId, 1, accountId, 1, 0, '', '', NOW(), NOW());
					set i = i + 1;
			END WHILE;
		CLOSE card_cursor;

		SET i = 0;
		SELECT COUNT(1) INTO num FROM tbl_user_company WHERE cpy_id = cpyId;
		OPEN userCompany_cursor;
			WHILE i < num DO
				fetch userCompany_cursor into userId;
					insert into fin_account_relation VALUES(null, userId, 1, accountId, 1, 0, '', '', NOW(), NOW());
					set i = i + 1;
			END WHILE;
		CLOSE userCompany_cursor;
END