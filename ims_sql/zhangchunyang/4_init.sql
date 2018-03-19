#初始化账单科目
insert into fin_bill_account VALUES(1, '100010', '充电消费', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(11, '100011', '充电消费退款', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(2, '100020', '停车费', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(12, '100021', '停车费退款', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(3, '100030', '信用还款', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(13, '100031', '信用还款退款', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(4, '100040', '充值', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(14, '100041', '充值退款', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(5, '100050', '发票快递费', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(15, '100051', '发票快递费退款', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(6, '100060', '溢缴款', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(16, '100061', '溢缴款领回', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(7, '100070', '资金划入', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(17, '100071', '资金划出', 0, 'admin', 'admin', NOW(), NOW());
insert into fin_bill_account VALUES(8, '100080', '授信', 0, 'admin', 'admin', NOW(), NOW());












#初始化等级
insert into tbl_level (level_id,level_seq,level_name,is_default,cpy_id,is_del,creator,modifier,gmt_create,gmt_modified)
SELECT null,1,'默认等级',1,cpy_id,0,'admin','admin',NOW(),NOW() from tbl_company WHERE is_del = 0 and cpy_type = 2; 


#初始化优惠
insert INTO fav_favourable VALUES (NULL, '200010', '优惠券优惠', 0,'admin','admin',NOW() ,NOW() );
insert INTO fav_favourable VALUES (NULL, '200011', 'VIN优惠', 0,'admin','admin',NOW() ,NOW() );



