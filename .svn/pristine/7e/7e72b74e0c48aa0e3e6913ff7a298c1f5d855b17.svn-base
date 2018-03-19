#drop 
DROP table tbl_company_data_sql;
alter table tbl_company drop account_no;
alter table tbl_company drop pay_rule;
DROP table tbl_company_old;
DROP table tbl_usercard_old;
DROP table tbl_user_normal_old;
DROP TABLE tbl_electric_data_sql;


#DELETE from tbl_user where length(user_account) = 12;
DELETE FROM tbl_purchasehistory WHERE puHi_Type = 2; #预约消费
DELETE FROM tbl_purchasehistory WHERE puHi_Type = 3; #购物消费
DELETE FROM tbl_purchasehistory WHERE puHi_Type = 7; #预约消费
DELETE FROM tbl_purchasehistory WHERE puHi_Type = 6; #预约消费


DROP FUNCTION fun_build_accountno;
DROP PROCEDURE proc_gen_account_config_rela;
DROP PROCEDURE proc_insert_company_account;
DROP PROCEDURE proc_insert_fin_account;
DROP PROCEDURE proc_insert_tuser;