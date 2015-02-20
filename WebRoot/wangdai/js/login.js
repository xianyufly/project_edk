var Login={
		login:function(){
			var userName=$("#userName").val();
			var passWord = $("#passWord").val();
			var code = $("#code").val();
			if(userName==''){
				$.messager.alert("提示","用户名不能为空","info");
				return false;
			}
			else if(passWord==''){
				$.messager.alert("提示","密码不能为空","info");
				return false;
			}
			else if(code==''){
				$.messager.alert("提示","验证码不能为空","info");
				return false;
			}
			var params ={
					userName:userName,
					passWord:passWord,
					code:code
			};
			Sys.ajaxRequest("sys/login.do", "post", params, function(data){
				var json = JSON.StrToJSON(data);
				if(json.success=="0")
					window.location.href="indexwdpt.html";
				else Login.handdleError(json.msg);
			});
		},
		handdleError:function(mes){
			$.messager.alert("出错信息", mes, "error");
			// 清空密码
			$('#passWord').val('');
			$('#code').val('');
			var date = new Date();
			// 重新加载验证码
			$('#authcode').attr('src', 'sys/createAuthcode.do?date=' + date.getTime());
		}
};
$(function(){
	$("#reloadauthcode").click(function(){
			var date = new Date();
			$('#authcode').attr('src',
					'sys/createAuthcode.do?date=' + date.getTime());
	});
	$('input').keydown(function (e) { //这里给function一个事件参数命名为e，叫event也行，随意的，e就是IE窗口发生的事件。
	    var key = e.which; //e.which是按键的值
	    if (key == 13) {
	    	Login.login();
	    }
	});
});