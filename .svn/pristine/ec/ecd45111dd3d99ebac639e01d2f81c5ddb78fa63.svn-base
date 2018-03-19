db_host=127.0.0.1
db_pass=123456
db_user=root
db_name=eichong

rm -f transfer_final.sql

#cat old_procedure_and_function.sql >> transfer_final.sql
#echo -e "\n" >> transfer_final.sql

cat zhangchunyang/1_rename.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/2_new_table.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/3_structure.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/4_init.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/5_company.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/6_user.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/7_card.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/8_account.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/9_history_data.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/10_drop.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/11_new_column.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/12_menu.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/14_warn.sql >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zangyaoyi_sql.sql  >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

cat zhangchunyang/13_pur.sql   >> transfer_final.sql
echo -e "\n" >> transfer_final.sql

date
mysql -uroot -p123456 eichong  <  transfer_final.sql 
date



