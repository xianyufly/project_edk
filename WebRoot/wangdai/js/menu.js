var menuTable;
var id;
var Menu = {
	init : function() {
		menuTable = $('#menuContent').datagrid({
			url : 'menu/getMenuContent.do',
			border : false,
			singleSelect : true,
			idField : 'id',
			pagination : true,
			fit : true,
			toolbar : '#toolbar',
			fitColumns : true,
			frozenColumns : true,
			pageList : [ 15, 20, 25, 30, 40 ],
			loadMsg : "正在加载数据,请稍后。。。",
			columns : [ [ {
				field : 'text',
				title : "菜单名",
				width : 156,
				align : 'center'
			}, {
				field : "url",
				title : "操作",
				width : 159,
				align : 'center'
			}, {
				field : "parent",
				title : "父菜单",
				width : 159,
				align : 'center'
			}, {
				field : "hasUrl",
				title : "拥有权限",
				width : 159,
				align : 'center'
			} ] ]
		});
	},
	showWin : function() {
		var row = menuTable.datagrid('getSelected');
		if (!row || row == '') {
			alert('请选择一行记录!');
			return false;
		}
		id = row.id;
		$('#menu-url').val(row.hasUrl);
		$('#menu-window').dialog('open');
	},
	setPass : function() {
		if (!id) {
			alert("您没选择一行!");
			return false;
		}
		var paras = {
			id : id,
			hasUrl : $('#menu-url').val()
		};
		Sys.ajaxRequest("menu/setMenuUrl.do", "get", paras, function(data) {
			var json = JSON.StrToJSON(data);
			if (json.success == 0) {
				$.messager.alert("提示", "操作成功", "info");
				$('#menu-window').dialog('close');
				menuTable.datagrid('reload');
				} else {
					$.messager.alert("提示", "系统忙", "info");
			}
			id = '';
		});


	}
};
$(function() {
	Menu.init();
});