var UpdatePass={
		init:function(){
		},
		update:function(){
			if($("#updateFrom").form('validate')){
				var oldPass = $('#oldPass').val();
				var newPass = $("#newPass").val();
				if(newPass!=$("#newPassT").val()){
					$.messager.alert("提示","两次密码 不一样","info");
				}else{
					var paras  = {
							oldPass:oldPass,
							newPass:newPass
					};
					Sys.ajaxRequest("sys/updatePass.do", "post", paras, function(data){
						var json = JSON.StrToJSON(data);
						if(json.success==0){
							$.messager.alert("成功提示", "修改成功", "info",function(){
							     document.forms[0].reset();
							});
						}else if(json.success==1){
							$.messager.alert("成功提示", json.msg, "info",function(){
							     document.forms[0].reset();
							});
						}			
					});
				}
			}
		}
};
$(function(){
	UpdatePass.init();
});