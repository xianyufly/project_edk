var groupid='';
var GroupMenu = {
		init: function(){
			$("#add-group").dialog({
				modal:true,
				closed:true
			});
			$("#groupTable").datagrid({
				url : 'menu/getGroups.do',
				border : false,
				singleSelect : true,
				idField : 'id',
				pagination : true,
				fit : true,
				fitColumns : true,
				toolbar:"#add",
				frozenColumns : true,
				pageList : [ 5, 10, 15, 20, 25 ],
				columns : [ [ {
					field : 'groupName',
					title : "管理员帐号",
					width : 105,
					align : 'center'
				}, {
					field : "id",
					title : "操作",
					width : 105,
					align : 'center',
					formatter : function(val, row, index) {
						return "<span class='icon-edit'>&nbsp;&nbsp;&nbsp;&nbsp;</span><a href='javascript:void(0);' onclick='GroupMenu.update("+row.id+");' style='font:14px;'>设置</a>";
					}
				},{
					field :"aaa",
					title :"平台管理",
					width : 105,
					formatter : function(val,row,index){
						return "<span class='icon-edit'>&nbsp;&nbsp;&nbsp;&nbsp;</span><a href='javascript:void(0);' onclick='GroupMenu.updatePLatForm("+row.id+");' style='font:14px;'>设置</a>";
					}
				}] ],
				  onBeforeLoad:function(){
					  $prop = $.messager.progress({  
			                title:'请稍等',  
			                msg:'加载数据...'  
			            });  
				  },
				  onLoadSuccess:function(){
			            setTimeout(function(){  
			                $.messager.progress('close');  
			            },300);  
				  },onLoadError:function(){
			            setTimeout(function(){  
			                $.messager.progress('close');  
			            },100);  
				  }
			});
		},
		update:function(id){
			groupid = id;
			$("#groupmenu").tree({
				url : 'menu/initMenu.do',
				checkbox : true,
				onLoadSuccess:function(){
					$('#bb').dialog({
						title:"管理组设置",
						modal:true,
						buttons:"#group-buttons"
					});
					var params = {
						id :id	
					};
				Sys.ajaxRequest("menu/getMenuOfGroup.do", "get",params , function(data){
					var json = JSON.StrToJSON(data);
					if(json){
						$.each(json,function(i,n){
						var node = 	$("#groupmenu").tree("find",n);
						if($('#groupmenu').tree('isLeaf',node.target))
							$("#groupmenu").tree('check',node.target);
						});
					}
				});
				}
			});
			groupid = id;
		},
		updatePLatForm:function(id){
			$("#platform").datagrid({
				url : "platform/initPlatForm.do?groupId="+id,
				fitColumns:true,
				columns:[[
				          {
				        	field : "ck",
				        	checkbox : true
				          },
						{
							field : "name",
							title : "名称"
						}          
				          
				          
				]],
				singleSelect:false,
				selectOnCheck : true,
				checkOnSelect : true,
				onLoadSuccess:function(data){
					if(data){
						$.each(data.rows,function(index,item){
							if(item.checked){
								$("#platform").datagrid("selectRow",index);
							}
						})
					}
				}
			});
			$('#pt').dialog({
				title:"管理组设置",
				modal:true,
				buttons:"#group-buttons2"
			});
			groupid = id;
		},
		setPlatForm:function(){
			var selecteds = $("#platform").datagrid("getSelections");
			var info="";
			for(var i=0;i<selecteds.length;i++){
				info = (info + selecteds[i].id) + (((i + 1)== selecteds.length) ? '':',');
			}
			Sys.ajaxPost("platform/updateGroupPlatForm.do",
				{groupId:groupid,platformIds:info}, function(data){
				var obj = JSON.StrToJSON(data);
				if(obj.success==true){
					$.messager.alert("提示信息",obj.msg);
					GroupMenu.platFormClose();
				}
			});

		},
		platFormClose:function(){
			$('#pt').dialog('close');
		},
		setAcco:function(){
			var ids = '';
			var nodes = [];
				 $("#groupmenu").find('.tree-checkbox2').each(function(){
				        var node = $(this).parent();
				        nodes.push($.extend({}, $.data(node[0], 'tree-node'), {
				                target: node[0],
				                checked: node.find('.tree-checkbox').hasClass('tree-checkbox2')
			        }));
				});		
			var cNodes = $("#groupmenu").tree('getChecked');
			$.each(nodes,function(i,n){
				ids+=nodes[i].id+",";
			});
			$.each(cNodes,function(i,n){
				ids+=n.id;
				if(i<cNodes.length-1)
					ids+=",";
			});
			var paras = {
					id:groupid,
					MenusId:ids
			};
			Sys.ajaxRequest("menu/setMenus.do", "get", paras, function(data){
				var json = JSON.StrToJSON(data);
				if(json.success=="0"){
					$.messager.alert("设置操作","更改成功","info");
				}
			});
			$('#bb').dialog('close');
			
		},
		close:function(){
			$('#bb').dialog('close');
		},
		addGroup:function(){			
			$.messager.prompt("添加用户组","请输入用户组的名称",function(val){
				if(val){
					if(val=='')
						$.messager.alert('温馨提示','名称不能为空','warn');
					var paras = {
							groupName : val
					};
					Sys.ajaxRequest("menu/addGroup.do", "get", paras, function(data){
						var json = JSON.StrToJSON(data);
						if(json.success=="0"){
							$("#groupTable").datagrid("reload");
							$.messager.alert('温馨提示','添加成功！','info');							
						}else{
							$.messager.alert("温馨提示","添加失败","warn");
						}
					});
				}
			});
		}		
};
$(function(){
	GroupMenu.init();
	
});