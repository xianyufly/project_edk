/**
 * 
 */
var Sys = {
	'path': '',
	'basePath': '',
	status: {'501':'系统没有此操作','403' : '访问权限异常', '500' : '服务器操作异常', '404': '页面未找到异常', '400':'请求参数异常', '401': '您没有相关权限'},	      
	ajax: function(config) {
		$.ajax({
			type: config.type,
			url: config.url,
			data: config.data,
			success: function(data) {
				data = JSON.StrToJSON(data);
				var success = data.success;
				if(success) {
					//Sys.showAlert('操作成功',null);
					config.success(data);
				}
				else {
					Sys.showError(data.message || "操作失败");
				}
			},
			error: function(XMLHttpRequest, textStatus, errorThrown){
				Sys.showError(Sys.status[''+XMLHttpRequest.status]);
			}
		});
	},
	/**
	 * ajax请求
	 * @param url
	 * @param type	请求类型post或get
	 * @param data
	 */
	ajaxRequest: function(url, type, data, callback){
		$.ajax({
			type: type,
			url: url,
			data: data,
			success : callback,
			error: function(XMLHttpRequest, textStatus, errorThrown){
				Sys.showError(Sys.status[''+XMLHttpRequest.status]);
			}
		});
	},
	/**
	 * ajax get请求
	 * @param url
	 * @param data
	 */
	ajaxGet: function(url, data, callback) {
		this.ajaxRequest(url, 'get', data, callback);
	},
	/**
	 * ajax post请求
	 * @param url
	 * @param data
	 */
	ajaxPost: function(url, data, callback) {
		this.ajaxRequest(url, 'post', data, callback);
	},
	
	/**
	 * ajax表单请求
	 * @param formId	表单id
	 * @param url		请求url
	 * @param callBack	指定请求成功后的回调方法
	 */
	ajaxSubmit: function (formId,url,callBack){
		var options = {
				url : url,
			    success: callBack ,
			    error:function(XMLHttpRequest){
			    	Sys.showError(Sys.status[''+XMLHttpRequest.status]);
			    }
		};
		$('#'+formId).ajaxSubmit(options);
	},
	submit: function(formid, config) {
		$(formid).form('submit', {
		    url: config.url,
		    onSubmit: function() {
	            return $(this).form('validate');
	        },
	        onLoadError : function (){
	        	alert('onLoadError');
	        },
		    success: function(data) {
				data = JSON.StrToJSON(data);
		        if(data.success) {
		        	Sys.showAlert('操作成功',null);
					config.success(data);
				}
				else {
					Sys.showError(data.message);
				}
		    },
	        error: function(XMLHttpRequest, textStatus, errorThrown){
				Sys.showError(XMLHttpRequest.status);
//				Sys.showError(Sys.status[''+XMLHttpRequest.status]);
			}
		});
	},
	showAlert: function(msg, titie) {
		$.messager.alert(titie || '操作提示', msg, 'info');
	},
	
	showError: function(msg, titie) {
		$.messager.alert(titie || '错误提示', msg, 'error',function(){
			if(msg==='登录超时异常')	
				window.location.href=Sys.path+'login.html';
		});
	},
	
	showConfirm: function(titie, msg, fuc) {
		$.messager.confirm(titie, msg, function(r) {
			if (r) {
				fuc();
			}
		});
	},
	/**
	 * 使用 HTML 与 HTML 之间进行传参 获取参数的方法
	 * 
	 * @param val
	 * @returns
	 */
	QueryString : function(val) {
		var uri = window.location.search;
		var re = new RegExp("" + val + "=([^&?]*)", "ig");
		return ((uri.match(re)) ? (uri.match(re)[0].substr(val.length + 1))
				: null);
	},
	//时间格式转换的函数
	formatterdate : function(val, row) {
		if(val==null) {
			return "";
		}
        var date = new Date(val);
        var year=date.getFullYear();
        var month=date.getMonth();
        var day=date.getDate();
        var hour=date.getHours();
        var minute=date.getMinutes();
        var second=date.getSeconds();
        month=month+1;
        if(month<10)
        	month='0'+month;
        if(day<10)
        	day='0'+day;
        if(hour<10)
        	hour='0'+hour;
        if(minute<10)
        	minute='0'+minute;
        if(second<10)
        	second='0'+second;
        return year+'-'+month+'-'+day+' '+hour+':'+minute+':'+second;
    }   
};