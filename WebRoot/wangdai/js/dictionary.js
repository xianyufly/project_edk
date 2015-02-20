var datagrid;
var row;
var DictionaryManage = {
	display : function(){
		datagrid.datagrid({
			queryParams: {
				str:$('#str').searchbox('getValue')
			}	
		});
		datagrid.datagrid("reload");
	},
	getOneName: function() {
		$('#p_add').combobox({
			url: Sys.path + 'dictionary/getOneTagList.do',
			valueField: 'id',
			textField: 'd_name',
			onSelect:function(record){
				//console.info(record.id);
				if($('#c_new').css('display')=='block'){
					$('#c_new').css('display','none');
				}
				if(record.id == 0){
					$('#new').css('display','block');
					if($('#two').css('display')=='block'){
						$('#two').css('display','none');
					}
					if($('#c_new').css('display')=='block'){
						$('#c_new').css('display','none');
					}
					if($('#t_new').css('display')=='block'){
						$('#t_new').css('display','none');
					}
				}else{
					$('#two').css('display','block');
					if($('#new').css('display')=='block'){
						$('#new').css('display','none');
					}
				}
				DictionaryManage.getTwoName(record.id);
			}
		});
	},
	getOneTag: function() {
		$('#p_tag').combobox({
			url: Sys.path + 'dictionary/getOneTagListEdit.do',
			valueField: 'id',
			textField: 'd_name'
		});
	},
	getTwoName: function(id){
		$('#c_add').combobox({
			url: Sys.path + 'dictionary/getTwoTagList.do',
			valueField: 'id',
			textField: 'd_name',
			onSelect:function(record){
				//console.info(record.id);
				if(record.id == 0){
					$('#c_new').css('display','block');
					if($('#t_new').css('display')=='block'){
						$('#t_new').css('display','none');
					}
				}else{
					$('#two').css('display','block');
					$('#t_new').css('display','block');
					if($('#c_new').css('display')=='block'){
						$('#c_new').css('display','none');
					}
				}
			},
			onBeforeLoad: function(param){
				param.p_id = id;
			}
		});
	},
	editWindow : function(id,p_id,d_name){
		if(p_id == 0){
			if($('#tag').css('display') == 'block'){
				$('#tag').css('display','none');
			}
		}else{
			if($('#tag').css('display') == 'none'){
				$('#tag').css('display','block');
			}
		}
		DictionaryManage.getOneTag();
		$('#e_id').val(id);
		$('#p_tag').combobox('setValue',p_id);
		$('#c_tag').val(d_name);
		$('#edit_window').dialog("open");
	},
	addDictionary:function(){
		var pp_id=$('#p_add').combobox('getValue');
		var pName=$('#p_newAAdd').val();
		var p_id=$('#c_add').combobox('getValue');
		var pdName=$('#c_newAdd').val();
		var dName=$('#t_newAdd').val();
		if((pp_id==""&&p_id==""&&pName==""&&pdName==""&&dName=="")||(pp_id!=""&&p_id==""&&pName==""&&pdName==""&&dName=="")||
				(pp_id!=""&&p_id!=""&&pName==""&&pdName==""&&dName=="")){
			$.messager.alert('提示','您未填写添加内容。。','info');
		}else{
			$.ajax({
				url:Sys.path + 'dictionary/addDictionary.do',
				dataType:'json',
				type:'post',
				data:{
					pp_id:pp_id,
					pName:pName,
					p_id:p_id,
					pdName:pdName,
					dName:dName
				},
				success:function(data){
					//console.info(data);
					if(data.success == 1){
						$.messager.alert('提示','添加成功。。','info');
						$('#insert_window').dialog("close");
						$("#dg").datagrid("reload");
					}else{
						$.messager.alert('提示','添加失败。。','info');
					}
				}
			});
		}
	},
	updateDictionary: function(){
		var id=$('#e_id').val();
		var p_id=$('#p_tag').combobox('getValue');
		var d_name=$('#c_tag').val();
		$.ajax({
			url:Sys.path + 'dictionary/updateDictionary.do',
			dataType:'json',
			type:'post',
			data:{
				id:id,
				p_id:p_id,
				d_name:d_name
			},
			success:function(data){
				//console.info(data);
				if(data.success == 1){
					$.messager.alert('提示','修改成功。。','info');
					$('#edit_window').dialog("close");
					$("#dg").datagrid("reload");
				}else{
					$.messager.alert('提示','修改失败。。','info');
				}
			}
		});
	},
	cancelUpdate: function() {
		$('#edit_window').dialog("close");
	},
	cancelAdd: function() {
		$('#p_newAAdd').val("");
		$('#c_newAdd').val("");
		$('#t_newAdd').val("");
		if($('#two').css('display') == 'block'){
			$('#two').css('display','none');
		}
		if($('#new').css('display') == 'block'){
			$('#new').css('display','none');
		}
		if($('#c_new').css('display') == 'block'){
			$('#c_new').css('display','none');
		}
		if($('#t_new').css('display') == 'block'){
			$('#t_new').css('display','none');
		}
		$('#insert_window').dialog("close");
	},
	addPlat : function(){		
		var params = {
				tagId : row.id,
				platName : $("#exit_addPlat").val()
			};
		
		Sys.ajaxRequest(Sys.path + "tagplatmanage/addPlat.do","post", params, function(data){
			var json = JSON.StrToJSON(data);
			if(json.success){
				$.messager.alert("提示","保存成功！","info");
				//$("#batch_window").dialog("close");
			}else{
				$.messager.alert("提示",json.msg,"info");
			}
		});
		$("#exit_existPlat").val("");
		Sys.ajaxRequest(Sys.path + "tagplatmanage/getPlatNameList.do","post",{tagId:row.id}, function(data){
			$("#exit_existPlat").val(data);
		});
	},
	deletePlat : function(){
		var params = {
				tagId : row.id,
				platName : $("#exit_addPlat").val()
			};
		
		Sys.ajaxRequest(Sys.path + "tagplatmanage/deletePlat.do","post", params, function(data){
			var json = JSON.StrToJSON(data);
			if(json.success){
				$.messager.alert("提示","删除成功！","info");
				//$("#batch_window").dialog("close");
			}else{
				$.messager.alert("提示",json.msg,"info");
			}
		});
		//console.info("exit_existPlat");
		$("#exit_existPlat").val("");
		Sys.ajaxRequest(Sys.path + "tagplatmanage/getPlatNameList.do","post",{tagId:row.id}, function(data){
			$("#exit_existPlat").val(data);
		});
	},
	add :function(){
		DictionaryManage.getOneName();
		$('c_newAdd').val("");
		$('#p_newAAdd').val("");
		$('#c_newAdd').val("");
		$('#t_newAdd').val("");
		if($('#two').css('display') == 'block'){
			$('#two').css('display','none');
		}
		if($('#new').css('display') == 'block'){
			$('#new').css('display','none');
		}
		if($('#c_new').css('display') == 'block'){
			$('#c_new').css('display','none');
		}
		if($('#t_new').css('display') == 'block'){
			$('#t_new').css('display','none');
		}
		$('#insert_window').dialog("open");
	},
	update :function(){
		row=$('#dg').datagrid('getSelected');
		if(!row){
			$.messager.alert('提示','请选择要修改的数据。','info');
			return;
		}else{
			//console.info(row.id+':'+row.p_id+':'+row.d_name)
			DictionaryManage.getOneTag();
			DictionaryManage.editWindow(row.id,row.p_id,row.d_name);
		}
	},
	deleteDic :function(){
		row = $('#dg').datagrid('getSelected');
		var index=$('#dg').datagrid('getRowIndex',row);
		if(!row){
			$.messager.alert('提示','请选择要删除的数据。','info');
			return;
		}else{
			$.ajax({
				url:Sys.path + 'dictionary/deleteDictionaryById.do',
				dataType:'json',
				type:'post',
				data:{id:row.id},
				success:function(data){
					//console.info(data);
					if(data.success==1){
						$.messager.confirm('提示','确定删除选定数据？',function(r){
							if(r){
								$('#dg').datagrid('deleteRow',index);
								$('#dg').datagrid('reload');
							}else{
								return;
							}
						});
					}else{
						$.messager.alert('提示','请先删除子标签。。','info');
						return;
					}
				}
			});
		}
	},
	batchPlat :function(){
		row = $('#dg').datagrid('getSelected');
		if(!row){
			$.messager.alert('提示','请选择要关联平台的标签。。','info');
			
		}else{
			Sys.ajaxRequest(Sys.path + "tagplatmanage/getPlatNameList.do","post",{tagId:row.id}, function(data){
				$("#exit_existPlat").val(data);
			});
			$("#exit_addPlat").val("");
			$('#batch_window').dialog("open");
		}
	},
	init:function(){
		$("#dic_update_btn").linkbutton({});
		$("#dic_cancelUpdate_btn").linkbutton({});
		$("#dic_add_btn").linkbutton({});
		$("#dic_cancelAdd_btn").linkbutton({});
		$("#dic_addPlat_btn").linkbutton({});
		$("#dic_deletePlat_btn").linkbutton({});
		$('#edit_window').dialog({
			closed:true,
			modal:true
		});
		$('#insert_window').dialog({
			closed:true,
			modal:true
		});
		$('#str').searchbox({
			width:200,
			prompt:'请输入查询关键字',
			searcher:function(){
				DictionaryManage.display();
			}
		});
		$('#batch_window').dialog({
			closed:true,
			modal:true
		});

		datagrid = $('#dg').datagrid({
			url: Sys.path + 'dictionary/getDictionaryList.do',
			idField: 'id',
			fit :true,
			pagination: true,
			frozenColumns:true,
			pageSize: 20,
			pageList: [5,10,15,20],
			queryParams: {
				str:""
			},
			loadMsg :'数据加载中，请稍候。。',
			fitColumns: true,
			singleSelect: true,			
			fieldId:'id',
			columns:[[{
				field: "id",
				title: "编号",
				width: 100,
				align: 'center'
			},{
				field: "d_name",
				title: "类型",
				width: 300,
				align: 'center'
			},{
				field: "p_name",
				title: "父类型",
				width: 300,
				align: 'center'
			}]],
			toolbar:"#dic_toolbar"
		});

	}	
};

$(function() {
	DictionaryManage.init();
	//DictionaryManage.display();
	DictionaryManage.getOneName();
});
