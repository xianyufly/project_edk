var table;
var index = -1;
var SynVersion = {
	saveEditor : function() {
		table = $("#versionTalble");
		if (index != -1) {
			table.datagrid('endEdit', index);
			var data = '';
			data = table.datagrid('getRows')[index];
			// console.info(data);
			index = -1;
			var params = {
				id : data.id,
				customerServiceAddr : data.customerServiceAddr,
				startUrl : data.startUrl,
				verifyDays : data.verifyDays,
				fenMode : data.fenMode,
				oneIssueMaxReward : data.oneIssueMaxReward,
				dangerValue1 : data.dangerValue1,
				dangerValue2 : data.dangerValue2,
				integralRate : data.integralRate,
				isBet:data.isBet,
				dangerValue3:data.dangerValue3,
				isLimit:data.isLimit,
				limitType:data.limitType,
				isOpen:data.isOpen,
				limitOdds:data.limitOdds,
				platId:data.platId

			};
			Sys.ajaxRequest("synversion/editConfigNew.do", "post", params, function(data) {
				var json = JSON.StrToJSON(data);
				if (json.success == 0) {
					$.messager.alert("成功提示", "修改成功", "info");
					table.datagrid('acceptChanges');
				} else if (json.success == 1) {
					$.messager.alert("提示", json.msg, "info");
					table.datagrid("rejectChanges");
				} else {
					table.datagrid("rejectChanges");
				}
			});
		} else {
		}
	},
	beginEdit : function() {
		table = $("#versionTalble");
		if (index == -1) {
			var row = table.datagrid('getSelected');
			if (row == null || row == '')
				$.messager.alert("提示", "请选择记录", "info");
			else {
				index = table.datagrid('getRowIndex', row);
				table.datagrid("beginEdit", index);
			}
		} else {
			$.messager.alert("提示", "不能同时编辑多行", "info");
		}

	},
	cancelEdit : function() {
		$("#versionTalble").datagrid("cancelEdit", index);
		index = -1;
	},
	init : function() {
		table = $agent = $("#versionTalble")
				.datagrid(
						{
							url : "synversion/getSysConfig.do",
							border : false,
							singleSelect : true,
							idField : 'id',
							pagination : true,
							fit : true,
							toolbar : "#toolbar",
							fitColumns : true,
							frozenColumns : true,
							pageList : [ 10, 15, 20, 25, 30 ],
							columns : [ [
									{
										field : 'platId',
										title : "所属平台",
										width : 100,
										align : "center",
										formatter : function(value, row, index) {
											for ( var item in platForms) {
												if (platForms[item].id == value) {
													return platForms[item].name;
												}
											}

										}
									},
									{
										field : 'customerServiceAddr',
										title : "客服地址",
										width : 200,
										align : "center",
										editor : {
											type : "validatebox",
											options : {
												required : true,
												messingMessage : "客服地址不能为空"
											}
										}
									},
									{
										field : 'startUrl',
										title : '推广链接',
										width : 200,
										align : "center",
										editor : {
											type : "validatebox",
											options : {
												required : true,
												messingMessage : "推广链接不能为空"
											}
										}
									},
									{
										field : 'fenMode',
										title : '是否开启分模式',
										width : 50,
										align : "center",
										formatter : function(value, row, index) {
											if (value == 1)
												return "是";
											return "否";

										},
										editor : {
											type : "combobox",
											options : {
												data : [ {
													"value" : "1",
													"text" : "是"
												}, {
													"value" : "0",
													"text" : "否"
												} ],
												valueField : "value",
												textField : "text"
											}
										}
									},
									{
										field : 'oneIssueMaxReward',
										title : '当期最高奖金',
										width : 55,
										align : "center",
										editor : {
											type : "validatebox",
											options : {
												required : true,
												messingMessage : "当期最高奖金不能为空"
											}
										}
									},
									{
										field : 'integralRate',
										title : '1块钱兑换的分数',
										width : 55,
										align : "center",
										editor : {
											type : "validatebox",
											options : {
												required : true,
												messingMessage : "分数不能为空"
											}
										}
									},
									{
										field : 'limitType',
										title : '参考值类型',
										width : 50,
										align : "center",
										formatter : function(value, row, index) {
											if (value == 1)
												return "奖金-投注额";
											return "奖金";

										},
										editor : {
											type : "combobox",
											options : {
												data : [ {
													"value" : "1",
													"text" : "奖金-投注额",
													"selected" : true
												}, {
													"value" : "2",
													"text" : "奖金"
												} ],
												valueField : "value",
												textField : "text"
											}
										}
									},
									{
										field : 'dangerValue3',
										title : '禁投值',
										width : 55,
										align : "center",
										editor : {
											type : "numberbox",
											options : {
												required : true,
												messingMessage : "不能为空"
											}
										}
									},
									{
										field : 'isBet',
										title : '是否阻止投注',
										width : 50,
										align : "center",
										formatter : function(value, row, index) {
											if (value == 1)
												return "阻止";
											return "不阻止";

										},
										editor : {
											type : "combobox",
											options : {
												data : [ {
													"value" : "1",
													"text" : "阻止",
													"selected" : true
												}, {
													"value" : "0",
													"text" : "不阻止"
												} ],
												valueField : "value",
												textField : "text"
											}
										}
									},
									{
										field : 'isLimit',
										title : '当期是否加黑',
										width : 50,
										align : "center",
										formatter : function(value, row, index) {
											if (value == 1)
												return "加黑";
											return "不加黑";

										},
										editor : {
											type : "combobox",
											options : {
												data : [ {
													"value" : "1",
													"text" : "加黑",
													"selected" : true
												}, {
													"value" : "0",
													"text" : "不加黑"
												} ],
												valueField : "value",
												textField : "text"
											}
										}
									},
									{
										field : 'limitOdds',
										title : '投注成功概率',
										width : 55,
										align : "center",
										editor : {
											type : "numberbox",
											options : {
												required : true,
												messingMessage : "不能为空"
											}
										}
									},
									{
										field : 'isOpen',
										title : '是否启用订单禁投',
										width : 50,
										align : "center",
										formatter : function(value, row, index) {
											if (value == 1)
												return "启用";
											return "不启用";

										},
										editor : {
											type : "combobox",
											options : {
												data : [ {
													"value" : "1",
													"text" : "启用",
													"selected" : true
												}, {
													"value" : "0",
													"text" : "不启用"
												} ],
												valueField : "value",
												textField : "text"
											}
										}
									},
									{
										field : 'id',
										title : '操作',
										width : 100,
										formatter : function(val, row, index) {
											return "<a class='actionlink' onclick='SynVersion.saveEditor("
													+ index
													+ ")'>保存</a>"
													+ "<a class='actionlink' onclick='SynVersion.cancelEdit()'>取消</a>";
										}
									} ] ],
							onDblClickRow : function(rowIndex, rowData) {
								console.info(1111);
								SynVersion.beginEdit();
							}
						});
	},
	deleted : function(id) {
		var para = {
			id : id
		};
		Sys.ajaxRequest("synversion/deleted.do", "post", para, function(data) {
			var json = JSON.StrToJSON(data);
			if (json.success == 0) {
				$.messager.alert("成功提示", "操作成功", "info");
				table.datagrid('load');
			} else if (json.success == 1) {
				$.messager.alert("提示", "系统忙", "info");
			}
			;
		});
	},
	addFile : function() {
		var url = $("#url").val();
		var fileName = $('#fileName').val();
		if (!url || url == '') {
			$.messager.alert("成功提示", "下载地址不能为空", "info");
			return;
		}
		if (!fileName || fileName == "") {
			$.messager.alert("成功提示", "目标文件名称不能为空", "info");
			return;
		}
		var para = {
			url : url,
			fileName : fileName
		};
		Sys.ajaxRequest("synversion/addFile.do", "post", para, function(data) {
			var json = JSON.StrToJSON(data);
			if (json.success == 0) {
				$.messager.alert("成功提示", "操作成功", "info");
				table.datagrid('load');
			} else if (json.success == 1) {
				$.messager.alert("提示", "系统忙", "info");
			}
			$("#url").val('');
			$('#fileName').val('');
		});
	},
	editConfig : function() {
		var ver = $("#ver").val();
		var syncVer = $('#syncVer').val();
		var startFile = $('#startFile').val();
		var customerServiceAddr = $('#customerServiceAddr').val();
		var startUrl = $('#startUrl').val();
		var verifyDays = $('#verifyDays').val();
		var fenMode = $('#fenMode').combobox("getValue");
		var oneIssueMaxReward = $('#oneIssueMaxReward').val();
		if (!ver || ver == "") {
			$.messager.alert("成功提示", "当前版本不能为空", "info");
			return;
		}
		if (!syncVer || syncVer == "") {
			$.messager.alert("成功提示", "同步版本不能为空", "info");
			return;
		}
		if (!startFile || startFile == "") {
			$.messager.alert("提示", "启动文件不能为空", "info");
			return;
		}
		if (!startUrl || startUrl == "") {
			$.messager.alert("提示", "推广链接不能为空", "info");
			return;
		}
		if (!customerServiceAddr || customerServiceAddr == "") {
			$.messager.alert("提示", "客服地址不能为空", "info");
			return;
		}
		if (!verifyDays || verifyDays == "" || verifyDays == 0) {
			$.messager.alert("提示", "审核周期不能为0", "info");
			return;
		}
		if (fenMode != '0' && fenMode != "1") {
			$.messager.alert("提示", "是否开启分模式错误", "info");
			return;
		}
		if (oneIssueMaxReward == null || oneIssueMaxReward == '') {
			$.messager.alert("提示", "最高中奖金额错误", "info");
			return;
		}
		var para = {
			ver : ver,
			syncVer : syncVer,
			startFile : startFile,
			customerServiceAddr : customerServiceAddr,
			startUrl : startUrl,
			verifyDays : verifyDays,
			fenMode : fenMode,
			oneIssueMaxReward : oneIssueMaxReward
		};
		Sys.ajaxRequest("synversion/editConfig.do", "post", para, function(data) {
			var json = JSON.StrToJSON(data);
			if (json.success == 0) {
				$.messager.alert("成功提示", "操作成功", "info");
				table.datagrid('load');
			} else if (json.success == 1) {
				$.messager.alert("提示", "系统忙", "info");
			}
			SynVersion.getConfig();
		});
	}
};
$(function() {
	SynVersion.init();
	SynVersion.getConfig();
});