var userid = '';
var row = '';
var platId = 0;
var UserAcco = {
	init : function() {
		$("#useraccotable")
				.datagrid(
						{
							url : 'menu/getUsers.do',
							border : false,
							singleSelect : true,
							idField : 'id',
							pagination : true,
							fit : true,
							fitColumns : true,
							frozenColumns : true,
							pageList : [ 25, 30, 40 ,50],
							toolbar : "#p",
							loadMsg : "正在加载数据,请稍后。。。",
							columns : [ [
									{
										field : 'userName',
										title : "管理员帐号",
										width : 156,
										align : 'center'
									},
									{
										field : "id",
										title : "操作",
										width : 159,
										align : 'center',
										formatter : function(val, row, index) {
											return "<span class='icon-edit'>&nbsp;&nbsp;&nbsp;&nbsp;</span><a href='javascript:void(0);' onclick='UserAcco.update("
													+ row.id
													+ ");' style='font:14px;'>设置</a>";
										}
									} ] ]
						});
		/*$("#platIdUserAcco").combobox({
			data : platForms,
			valueField :'id',
			textField : 'name',
			onSelect:function(record){
				platId = record.id;
			}
		});*/
	},
	update : function(id) {

		$("#qxmenu").tree(
				{
					url : 'menu/initMenuOfGroup.do',
					checkbox : true,
					onLoadSuccess : function() {
						$('#dd').dialog({
							title : "管理组设置",
							modal : true,
							buttons : "#dlg-buttons"
						});
						var params = {
							id : id
						};
						Sys.ajaxRequest("menu/getGroupOfUser.do", "get",
								params, function(data) {
									var json = JSON.StrToJSON(data);
									if (json) {
										$.each(json, function(i, n) {
											var node = $("#qxmenu").tree(
													"find", n);
											$("#qxmenu").tree('check',
													node.target);
										});
									}
								});
					}
				});
		$("#qxmenu").tree('reload');
		userid = id;
	},
	setAcco : function() {
		var ids = '';
		var nodes = [];
		$("#qxmenu").find('.tree-checkbox2').each(
				function() {
					var node = $(this).parent();
					nodes.push($.extend({}, $.data(node[0], 'tree-node'), {
						target : node[0],
						checked : node.find('.tree-checkbox').hasClass(
								'tree-checkbox2')
					}));
				});
		var cNodes = $("#qxmenu").tree('getChecked');
		$.each(nodes, function(i, n) {
			ids += nodes[i].id + ",";
		});
		$.each(cNodes, function(i, n) {
			ids += n.id;
			if (i < cNodes.length - 1)
				ids += ",";
		});
		if (ids != '') {
			var paras = {
				id : userid,
				groupIds : ids
			};
			Sys.ajaxRequest("menu/setGroups.do", "get", paras, function(data) {
				var json = JSON.StrToJSON(data);
				if (json.success == "0") {
					$.messager.alert("设置操作", "更改成功", "info");
				}
			});
		} else {
			$.messager.alert("设置操作", "请选择", "info");
		}
		$('#dd').dialog('close');

	},
	close : function() {
		$('#dd').dialog('close');
	},
	openEditer : function() {
		$('#addManager-window').dialog('open');
	},
	addManager : function() {
		var userName = $('#new_name').val();
		var passWord = $('#passWord').val();
		if (!userName||userName == '') {
			$.messager.alert("设置操作", "请输入用户名", "info");
			return false;
		}
		if (!passWord||passWord == '') {
			$.messager.alert("设置操作", "请输入用户名", "info");
			return false;
		}
		var paras = {
				userName:userName,
				passWord:passWord,
				platId : platId
		};
		Sys.ajaxRequest("menu/addManager.do", "post", paras, function(data) {
			var json = JSON.StrToJSON(data);
			if (json.success == "0") {
				$.messager.alert("设置操作", "添加成功", "info");
				$('#addManager-window').dialog('close');
				UserAcco.init();
			}
		});
	},
	closeEditer : function() {
		$('#addManager-window').dialog('close');
	},
	deleted:function(){
		var row = $("#useraccotable").datagrid('getSelected');
		if (row == null || row == '')
			$.messager.alert("提示", "请选择记录", "info");
		else{
			var paras = {
					id:row.id
			};
			Sys.ajaxGet("menu/deletedManager.do", paras, function(data){
				var json = JSON.StrToJSON(data);
				if (json.success == 0) {
					$.messager
							.alert("成功提示", "删除成功", "info");
					$("#useraccotable").datagrid('load');
				} else if (json.success == 1) {
					$.messager
							.alert("提示", json.msg, "info");
				}
			});
		}
	}

};
$(function() {
	UserAcco.init();
	
});
function reloadGrid(){
	$("#useraccotable").datagrid("reload");
}