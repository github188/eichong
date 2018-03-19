delete from tbl_usercard_old where uc_UserId = 0;

UPDATE tbl_user_normal_old n
SET n.apply_card = 2
WHERE
	n.user_id IN (
		SELECT
			u.uc_UserId
		FROM
			tbl_usercard_old u

	)
AND n.apply_card = 0;

INSERT INTO tbl_usercard (
	uc_id,
	uc_InternalCardNumber,
	uc_ExternalCardNumber,
	uc_Status,
	uc_type,
	user_Id,
	new_user_id,
	cpy_id,
	account_id,
	is_valid,
	is_app,
	is_credit,
	creator,
	modifier,
	gmt_create,
	gmt_modified
) SELECT
	pk_UserCard,
	uc_InternalCardNumber,
	uc_ExternalCardNumber,
	uc_Status,
	uc_pay_mode,
	0,
	(
		CASE
		WHEN o.uc_UserId IN (
			SELECT
				u.user_id
			FROM
				tbl_user u,
				tbl_usercard_old c,
				tbl_user_normal_old n
			WHERE
				u.user_id = c.uc_UserId
			AND n.user_id = u.user_id
			AND LENGTH(u.user_account) = 11
			AND user_status != 3
			AND n.apply_card != 0
		) THEN
			o.uc_UserId
		ELSE
			0
		END
	) AS uc_UserId,
	uc_CompanyNumber,
	0,
	0,
	(
		CASE
		WHEN o.uc_UserId IN (
			SELECT
				u.user_id
			FROM
				tbl_user u,
				tbl_usercard_old c,
				tbl_user_normal_old n
			WHERE
				u.user_id = c.uc_UserId
			AND n.user_id = u.user_id
			AND LENGTH(u.user_account) = 11
			AND user_status != 3
			AND n.apply_card != 0
		) THEN
			1
		ELSE
			0
		END
	) AS is_app,
	0,
	'',
	'',
	uc_Createdate,
	uc_Updatedate
FROM
	tbl_usercard_old o;


ALTER TABLE `tbl_usercard`
MODIFY COLUMN `uc_id`  bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键' FIRST ;


#删除公司标识为0,1001的卡
delete from tbl_usercard where cpy_id = 0;
delete from tbl_usercard where cpy_id = 1001;


UPDATE tbl_usercard card,tbl_company cpy
SET card.cpy_id = cpy.cpy_id
WHERE card.cpy_id = cpy.cpy_number;