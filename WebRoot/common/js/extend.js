
function ex(status) {
	//alert(status);
	if(status == 603) {
		Sys.showAlert("没有访问权限1,请先分配频道所属项目");
	}
	if(status == 403) {
		Sys.showAlert("没有访问权限,请选择");
	}
	else if(status == 500) {
		Sys.showAlert("系统错误");
	}
	else if(status == 401) {
		alert("系统超时，请重新登录");
		window.location.href = "login.jsp";
	}
}
(function() {
	$.extend($.fn.datagrid.defaults, {
		pageSize: 30
	});
	
	$.extend($.fn.datagrid.methods, {
		showMsg: function() {
			alert("aaa");
		}
	});
	
	$.extend($.fn.datagrid.defaults, {
		onLoadError: function(data) {
			//debugger;
			//var responseText = JSON.StrToJSON(data.responseText);
			ex(data.status);
		}
	});
	
	$.extend($.fn.tree.defaults, {
		onLoadError: function(data) {
		//	var responseText = JSON.StrToJSON(data.responseText);
			ex(data.status);
		}
	});
	
	$.extend($.fn.treegrid.defaults, {
		onLoadError: function(data) {
			//var responseText = JSON.StrToJSON(data.responseText);
			ex(data.status);
		}
	});
})(jQuery);