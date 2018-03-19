/**
 * Created by Aaron on 2015/3/25.
 */
$(document).ready(function(){

    /**
     * 建议
     */
    $('#submitBtn').click(function(e){
        e.preventDefault();
        if(!$('#content').val().trim()){
            alert('请输入建议!');
            return;
        }

        Ajax.custom({
            url:config.ISuggest,
            data:{
                febaUserid:UserService.getUserId(),
                febaContent:$('#content').val().trim()
            }
        },function(res){
            res.status == '1' ?alert('提交成功'):alert(res.msg || 'error');
        })
    });

});