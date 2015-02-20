 function login(){
    	if ($('#loginFrom').validate().form()) {
          		var userName=$("#username").val();
				var passWord = $("#password").val();
				var code = $("#code").val();
				var params ={
						userName:userName,
						passWord:passWord,
						code:code
				};
      			Sys.ajaxRequest("sys/login.do", "post", params, function(data){
					var json = JSON.StrToJSON(data);
					if(json.success=="0"){
						window.location.href="index.html";
					}
					else {
						$('#errorMsg').removeClass('hide');
						$("#errorMsg span").text(json.msg);
						var date=new Date();
						// 重新加载验证码
						$('#authcode').attr('src', 'sys/createAuthcode.do?date=' + date.getTime());
						switch(json.success){
							case 1:
								$("#code").focus();
								$("#code").val("");
							break;
							case 2:
								$("#username").focus();
								$("#username").val("");
							break;
							case 3:
								$("#password").focus();
								$("#password").val("");
							break;
						}
					}
				});
            }
    }
$(function(){
	$('#loginFrom').validate({
        errorElement: 'label', 
        errorClass: 'help-inline', 
        focusInvalid: false, 
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            },
             code: {
                required: true
            }
        },

        messages: {
            username: {
                required: "账号不能为空."
            },
            password: {
                required: "密码不能为空."
            },
            code: {
                required: "验证码不能为空."
            }
        },

        invalidHandler: function (event, validator) { //display error alert on form submit   
            $('#errorMsg').removeClass('hide');
        },

        highlight: function (element) { // hightlight error inputs
            $(element)
                .closest('.form-flag').addClass('error'); // set error class to the control group
        },

        success: function (label) {
            label.siblings('.form-flag').removeClass('error');
            label.remove();
        },

        errorPlacement: function (error, element) {
            error.addClass('help-small no-left-padding').insertAfter(element.closest('.control-wrapper'));
        },

        submitHandler: function (form) {
        		
         }	
    });
});
