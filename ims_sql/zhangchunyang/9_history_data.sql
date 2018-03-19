
#cpy_id公司ID处理




#费率
-- update tbl_rateinformation
-- set 
-- raIn_TipTimeTariffMoney = raIn_ServiceCharge,
-- raIn_PeakElectricityMoney = raIn_ServiceCharge,
-- raIn_UsualMoney = raIn_ServiceCharge,
-- raIn_ValleyTimeMoney = raIn_ServiceCharge;


 #桩体配置
 alter table tbl_pilemaker add column is_del int(1) not null DEFAULT 0 COMMENT '删除标识';
 alter table tbl_carcompany add column is_del int(1) not null DEFAULT 0 COMMENT '删除标识'; 
 alter table tbl_carInfo add column is_del int(1) not null DEFAULT 0 COMMENT '删除标识'; 


