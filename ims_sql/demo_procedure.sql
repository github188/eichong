DELIMITER ;;
create procedure a1(in id integer)
begin
    update tbl_user set user_status = 1
        where user_id = id;
end;;
DELIMITER ;

call a1(1); 
Drop procedure a1;
