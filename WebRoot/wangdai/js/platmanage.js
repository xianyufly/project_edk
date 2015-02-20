var protectId;
var platManage = {
	init : function() {
		$.extend($.fn.validatebox.defaults.rules, {
		    //验证汉子
		    CHS: {
		        validator: function (value) {
		            return /^[\u0391-\uFFE5]+$/.test(value);
		        },
		        message: '只能输入汉字'
		    },
		num:{
			
		}
		});
		$("#addProject").dialog({
			title : "增加项目",
			width : 300,
			height : 350,
			closed : true,
			modal : true
		});
		$("#addTag").dialog({
			title : "增加标签",
			width : 300,
			height : 350,
			closed : true,
			modal : true
		});
		$("#updatePlat").dialog({
			title : "平台修改",
			width : 400,
			height : 500,
			closed : true,
			modal : true
		});
		$("#addPlat").dialog({
			title : "平台添加",
			width : 400,
			height : 450,
			closed : true,
			modal : true 
		});
		$("#deletePlat").dialog({
			title : "平台删除",
			width : 300,
			height : 200,
			closed : true,
			modal : true
		});
		$("#jiadeDeletePlat").dialog({
			title : "平台删除",
			width : 300,
			height : 200,
			closed : true,
			modal : true
		});
		$("#platNameA").validatebox({
			required : true,
			missingMessage : "请输入汉字",
			validType : "CHS"
		});
		$("#platUrl").validatebox({
			required : true,
			// missingMessage:"请填写正确格式",
			validType : "url"
		});
		$("#registMoney").validatebox({
			required : true,
			missingMessage : "请填写数字金额",
			validType : "num"

		});
		$("#rateLimit").validatebox({
			required : true,
			missingMessage : "请填写XX-XX的数字范围"
		});
		$("#platNameU").validatebox({
			required : true,
			missingMessage : "请输入汉字"
		});
		$("#platUrlU").validatebox({
			required : true,
			// missingMessage:"请填写正确格式",
			validType : "url"
		});
		$("#registMoneyU").validatebox({
			required : true,
			missingMessage : "请填写数字金额"
		});
		$("#rateLimitU").validatebox({
			required : true,
			missingMessage : "请填写XX-XX的数字范围"
		});
		$('#addPlatForm').form({
	        url:Sys.path + "/wangdai/platmanage/save.do",
	        onSubmit: function(){
	        		
	                if(!$("#addPlat").form('validate')){
	                	return false;
	                }else{
	                	if($("#protectionType2").getValues!=""){
	                		return true;
	                	}
	                }
	        },
	        success:function(data){
	        	var json = $.parseJSON(data);
	        	if (json.success == 1) {
					$.messager.alert("提示信息", "添加成功");
				} else {
					$.messager.alert("提示信息", "添加失败");
				}
				$("#addPlat").dialog("close");
				$('#dg').datagrid('reload');
	        }
		});
		$('#updatePlatForm').form({
			url:Sys.path + "platmanage/update.do",
			onSubmit:function(){
				if(!$("#updatePlat").form('validate')){
                	return false;
                }
        },
        success:function(data){
        	var json = $.parseJSON(data);
        	if (json.success == 1) {
				$.messager.alert("提示信息", "修改成功");
			} else {
				$.messager.alert("提示信息", "修改失败");
			}
			$("#updatePlat").dialog("close");
			$('#dg').datagrid('reload');
			}
		});
	},
	clear : function() {
		$("#platNameA").val("");
		$("#platImgL").val("");
		$("#platImgS").val("");
		$("#platUrl").val("");
		$("#startTime").val("");
		$("#endTime").val("");
		$("#onLineTime").val("");
		$("#registMoney").val(""), 
		$("#protectionType").val("");
		$("#protectionType2").val("");
		$("#startWord").val("");
		$("#platIntro").val("");
		$("#moneyExplain").val("");
		$("#belongArea").val("");
		$("#belongArea2").val("");
		$("#rateLimit").val("");
		// $("#userEvaluateAvg").val(),
		// $("#serviceAvg").val(),
		// $("#experienceAvg").val(),
		// $("#safeAvg").val(),
	},
	showAddPlatDiv : function() {
		platManage.clear();
		$("#addPlat").dialog("open");
		// $("#updatePlat").dialog("close");
		// $("#deletePlat").dialog("close");
		$('#protectionType')
				.combobox(
						{
							url : Sys.path + 'platmanage/getProtectedList.do',
							valueField : 'id',
							textField : 'd_name',
							required:true,
							missingMessage:"must",
							onSelect : function(record) {
								$('#protectionType2')
										.combobox(
												{
													url :Sys.path + 'platmanage/getPlatManageListbyId.do?id='
															+ record.id,
													valueField : 'id',
													textField : 'text',
													required:true,
													missingMessage:"must",
													onSelect : function(record) {
														protectId = record.id;
													}
												});
								$('#protectionType2').css('visibility',
										'visible');
							}
						});
		$("#belongArea").combobox(
				{
					data:province,
					valueField : 'ProID',
					textField : 'ProName',
					required:true,
					missingMessage:"must",
					onSelect : function(record) {
						var cityNew = new Array();
						for(var i=0;i<city.length;i++){
							var proId = city[i].ProID;
							if(record.ProID==proId){
								cityNew.push(city[i]);
							}
						}
						$('#belongArea2')
								.combobox(
										{
											data:cityNew,
											valueField : 'CityID',
											textField : 'CityName',
											required:true,
											missingMessage:"must",
										});
						$('#belongArea2').css('visibility',
								'visible');
					}
				});

	},
	addPlat : function() {
		/*if ($("#platNameA").val() != "") {
			$.ajax({
				type : "post",
				url : Sys.path + "/wangdai/platmanage/save.do",
				data : {
					plat_name : $("#platNameA").val(),
					plat_img_l : $("#platImgL").val(),
					plat_img_s : $("#platImgS").val(),
					plat_url : $("#platUrl").val(),
					start_time : $("#startTime").val(),
					end_time : $("#endTime").val(),
					on_line_time : $("#onLineTime").val(),
					regist_money : $("#registMoney").val(),
					protection_type : $('#protectionType2')
							.combobox('getValue'),
					// protection_type:record.id,
					start_word : $("#startWord").val(),
					plat_intro : $("#platIntro").val(),
					money_explain : $("#moneyExplain").val(),
					belong_area : $("#belongArea2").combobox('getValue'),
					rate_limit : $("#rateLimit").val(),
				// user_evaluate_avg:$("#userEvaluateAvg").val(),
				// service_avg:$("#serviceAvg").val(),
				// experience_avg:$("#experienceAvg").val(),
				// safe_avg:$("#safeAvg").val(),
				},
				success : function(data) {
					
				},
				dataType : "json"
			});
		} else {
			$.messager.alert("提示信息", "平台名称不可为空");
		}*/
	},
	showupdatePlatDiv : function() {
		var row = $('#dg').datagrid('getSelected');
		$("#protectionType2U").combobox({});
		if (row) {
			$("#updatePlat").dialog("open");
		} else {
			$.messager.alert("提示信息", "请选择您要修改的项目");
		}
		$("#id").val(row.id);
		$("#platNameU").val(row.plat_name);
		/*$("#platImgLU").val(row.plat_img_l);
		$("#platImgSU").val(row.plat_img_s);*/
		$("#platUrlU").val(row.plat_url);
		$("#startTimeU").val(row.start_time);
		$("#endTimeU").val(row.end_time);
		$("#onLineTimeU").val(row.on_line_time);
		$("#registMoneyU").val(row.regist_money);
		console.info(3333);
		$('#protectionTypeU')
				.combobox(
						{
							url : Sys.path + 'platmanage/getProtectedList.do',
							valueField : 'id',
							textField : 'd_name',
							required:true,
							missingMessage:"must",
							onSelect : function(record) {
								$('#protectionType2U')
										.combobox(
												{
													url : Sys.path + 'platmanage/getPlatManageListbyId.do?id='
															+ record.id,
													valueField : 'id',
													textField : 'text',
													required:true,
													missingMessage:"must",
												});
								$('#protectionType2U').css('visibility',
										'visible');
							}
						});
		console.info(44444);
		$("#protectionTypeU").combobox("setValue", row.d_pId);
		$("#protectionTypeU").combobox("setText", row.d_name);
		$("#protectionType2U").combobox("setValue", row.d_id);
		$("#protectionType2U").combobox("setText", row.d_name);
		$("#startWordU").val(row.start_word);
		$("#platIntroU").val(row.plat_intro);
		$("#moneyExplainU").val(row.money_explain);
		$("#rateLimitU").val(row.rate_limit);
		$("#belongAreaU").combobox(
				{
					data:province,
					valueField : 'ProID',
					textField : 'ProName',
					required:true,
					missingMessage:"must",
					onSelect : function(record) {
						var cityNew = new Array();
						for(var i=0;i<city.length;i++){
							var proId = city[i].ProID;
							if(record.ProID==proId){
								cityNew.push(city[i]);
							}
						}
						$('#belongArea2U')
								.combobox(
										{
											data:cityNew,
											valueField : 'CityID',
											textField : 'CityName',
											required:true,
											missingMessage:"must",
										});
						$('#belongArea2U').css('visibility',
								'visible');
					}
				});
	
			var proName = "";
			var cityName = "";
			var ProID="";
			for(var i=0;i<city.length;i++){
				var cityId = city[i].CityID;
				if(row.belong_area==cityId){
					cityName=city[i].CityName;
					ProID=city[i].ProID;
				}
			}
			for(var j=0;j<province.length;j++){
				var proId = province[j].ProID;
				if(ProID==proId){
					proName=province[j].ProName;
				}
			}
		$("#belongAreaU").combobox("setValue", ProID);
		$("#belongAreaU").combobox("setText", proName);
		$("#belongArea2U").combobox("setValue",row.belong_area);
		$("#belongArea2U").combobox("setText", cityName);
		
		// $("#projects").val(row.projects);

		// $("#tags").val(row.tags);

	},


	updatePlat : function() {
//		var row = $('#dg').datagrid('getSelected');
//		$.ajax({
//			type : "post",
//			url : Sys.path + "platmanage/update.do",
//			data : {
//				id : row.id,
//				plat_name : $("#platNameU").val(),
//				plat_img_l : $("#platImgLU").val(),
//				plat_img_s : $("#platImgSU").val(),
//				plat_url : $("#platUrlU").val(),
//				start_time : $("#startTimeU").val(),
//				end_time : $("#endTimeU").val(),
//				on_line_time : $("#onLineTimeU").val(),
//				regist_money : $("#registMoneyU").val(),
//				// protection_type:$("#protectionTypeU").val(),
//				// protection_type:$('#protectionTypeU').combobox('getValue'),
//				protection_type : $('#protectionType2U').combobox('getValue'),
//
//				// protection_type:$('#protectionType2U').val($('#protectionType2U').combobox('getValue')),
//				start_word : $("#startWordU").val(),
//				plat_intro : $("#platIntroU").val(),
//				money_explain : $("#moneyExplainU").val(),
//				belong_area : $("#belongArea2U").combobox('getValue'),
//				rate_limit : $("#rateLimitU").val(),
//
//			},
//
//			success : function(data) {
//				console.info(data);
//				if (data.success == 0) {
//					$.messager.alert("提示信息", "修改失败");
//				} else {
//					$.messager.alert("提示信息", "修改成功");
//				}
//				$("#updatePlat").dialog("close");
//				$('#dg').datagrid('reload');
//			},
//			dataType : "json"
//		});
//		/*
//		 * }, dataType: "json" })
//		 */

	},
	showDeletePlat : function() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$("#deletePlat").dialog("open");
		} else {
			$.messager.alert("提示信息", "请选择您要删除的项目");
		}
	},
	deletePlat : function() {
		var row = $('#dg').datagrid('getSelected');

		$.ajax({
			type : "post",
			url : Sys.path + "platmanage/delete.do",
			data : {

				id : row.id

			},
			success : function(data) {
				if (data.success == 1) {
					$.messager.alert("提示信息", "删除失败");
				} else {
					$.messager.alert("提示信息", "删除成功");
				}
				$("#deletePlat").dialog("close");
				$('#dg').datagrid('reload');
			},
			dataType : "json"
		});
	},
	cancel : function() {
		$("#deletePlat").dialog("close");
		$("#updatePlat").dialog("close");
		$("#addPlat").dialog("close");
		$("#jiadeDeletePlat").dialog("close");


	},
	showAddProjectDiv : function() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$("#addProject").dialog("open");
		} else {
			$.messager.alert("提示信息", "请先选择一个平台");
		}
		$.ajax({
			type : "post",
			url : Sys.path + "platmanage/getPlatManageListbyId.do",
			data : {
				id : 4,
			},
			success : function(data) {
				$("#addProject2").empty();
				for ( var i = 0; i < data.length; i++) {
					var check = "";
					var project = row.projects.split(",");
					for ( var j = 0; j < project.length; j++) {
						if (data[i].text == project[j]) {
							check = "checked='checked'";
						}
					}
					var str = "<label><input id='Project" + i
							+ "' type=\"checkbox\" value='" + data[i].id + "' "
							+ check + "/>" + data[i].text + "<label>";
					if (i % 3 == 0) {
						str = "<br><br>" + str;
					}
					$("#addProject2").append(str);
				}
				// $("#addProject").dialog("open");
			},
			dataType : "json"
		});
	},
	showAddTagDiv : function() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$("#addTag").dialog("open");
		} else {
			$.messager.alert("提示信息", "请先选择一个平台");
		}

		$.ajax({
			type : "post",
			url : Sys.path + "platmanage/getPlatManageListbyId.do",
			data : {
				id : 1,
			},
			success : function(data) {
				$("#addTag2").empty();
				for ( var i = 0; i < data.length; i++) {
					var check = "";
					var tag = row.tags.split(",");
					for ( var j = 0; j < tag.length; j++) {
						if (data[i].text == tag[j]) {
							check = "checked='checked'";
						}
					}
					var str = "<label><input id='Tag" + i
							+ "' type=\"checkbox\" value='" + data[i].id + "' "
							+ check + " />" + data[i].text + "<label>";
					if (i % 4 == 0) {
						str = "<br><br>" + str;
					}
					$("#addTag2").append(str);
				}
				// $("#addProject").dialog("open");
			},
			dataType : "json"
		});
	},
	AddProject : function() {
		var row = $('#dg').datagrid('getSelected');
		$.ajax({
			type : "post",
			url : Sys.path + "proplatmanage/deleteProPlatManageListbyplat_id.do",
			data : {
				plat_id : row.id,
			},
			success : function(data) {
				$("#addProject").dialog("close");
				$('#dg').datagrid('reload');
			},
			dataType : "json"
		});
		var inputs = $("#addProject :input");
		for ( var i = 0; i < $("#addProject2 :input").length; i++) {
			if (inputs[i].checked) {
				$.ajax({
					type : "post",
					url : Sys.path + "proplatmanage/save.do",
					data : {
						project_id : $(inputs[i]).attr("value"),
						plat_id : row.id

					},
					success : function(data) {
						$("#addProject").dialog("close");
						$('#dg').datagrid('reload');
					},

					dataType : "json"

				});

			}
		}

	},
	AddTag : function() {
		var row = $('#dg').datagrid('getSelected');
		$.ajax({
			type : "post",
			url : Sys.path + "tagplatmanage/deleteTagPlatManageListbyplat_id.do",
			data : {
				plat_id : row.id,
			},
			success : function(data) {
				$("#addTag").dialog("close");
				$('#dg').datagrid('reload');
			},
			dataType : "json"
		});
		var inputs = $("#addTag :input");
		for ( var i = 0; i < $("#addTag2 :input").length; i++) {
			if (inputs[i].checked) {
				$.ajax({
					type : "post",
					url : Sys.path + "tagplatmanage/save.do",
					data : {
						tag_id : $(inputs[i]).attr("value"),
						plat_id : row.id

					},
					success : function(data) {
						$("#addTag").dialog("close");
						$('#dg').datagrid('reload');
					},
					dataType : "json"
				});
			}
		}


	},
	cancelAddProjectDiv : function() {
		$("#addProject").dialog("close");
	},
	cancelAddTagDiv : function() {
		$("#addTag").dialog("close");
	},
	showJiadeDeletePlat : function() {
		var row = $('#dg').datagrid('getSelected');
		if (row) {
			$("#jiadeDeletePlat").dialog("open");
		} else {
			$.messager.alert("提示信息", "请选择您要删除的项目");
		}
	},
	jiadeDeletePlat:function(){
		var row = $('#dg').datagrid('getSelected');
		$.ajax({
			type : "post",
			url : Sys.path + "platmanage/updateisdel.do",
			data : {

				id : row.id

			},
			success : function(data) {
				if (data.success == 1) {
					$.messager.alert("提示信息", "删除失败");
				} else {
					$.messager.alert("提示信息", "删除成功");
				}
				$("#jiadeDeletePlat").dialog("close");
				$('#dg').datagrid('reload');
			},
			dataType : "json"
		});
	},
};
var Formatter = {
	protectionType : function(value, rowDate, rowIndex) {
		// if(value==21)return "平台自由投资";//"<a
		// href=\"http://www.baidu.com\">平台自由投资</a>";
		// if(value==23)return "平台自由投资";//"<a
		// href=\"http://www.baidu.com\">平台自由投资</a>";
		// if(value==24)return "平台风险准备金";//"<a
		// href=\"http://www.baidu.com\">平台风险准备金</a>";
		// if(value==26)return "保险公司";
		// if(value==27)return "小贷公司";
		// if(value==28)return "融资性担保";
		// if(value==29)return "非融资性担保";
		// if(value==44)return "银行";
	},
	platImgL : function(value, rowDate, rowIndex) {
		return "<img src='/wangdai/upload/"+value+"' style='width:100px;height:50'>";
	},
	platImgS : function(value, rowDate, rowIndex) {
		return "<img src='/wangdai/upload/"+value+"' style='width:100px;height:50'>";
	},
	belongArea:function(value, rowDate, rowIndex){
		if(value==null){
			return "";
		}else{
		var proName = "";
		var cityName = "";
		
		var ProID="";
		for(var i=0;i<city.length;i++){
			var cityId = city[i].CityID;
			if(value==cityId){
				cityName=city[i].CityName;
				ProID=city[i].ProID;
			}
		}
		for(var j=0;j<province.length;j++){
			var proId = province[j].ProID;
			if(ProID==proId){
				proName=province[j].ProName;
			}
		}
		return proName+","+cityName;
	}},
};

$(function() {
	platManage.init();
});