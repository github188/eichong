
date
mysql -uroot -p123456 -e "drop database eichong; create database eichong DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;"
zcat ../eichong_20171114.5.sql.gz | mysql -uroot -p123456 eichong  
#mysql -uroot -p123456 eichong  < after_create.sql
mysql -uroot -p123456 eichong  < eichong_proc_20171114_2.sql
date

