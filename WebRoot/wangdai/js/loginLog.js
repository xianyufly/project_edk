var $loginTable ='' ;
var LoginLog = {
		init:function(){
					$('#ss').searchbox({  
					    width:250,  
					    searcher:function(value,name){  
					        var paras = {
					        	userName:value
					        };
					        $loginTable.datagrid({
					        	queryParams:paras

					        });
					        $loginTable.datagrid('load');
					    },  
					    menu:'#mm',  
					    prompt:'Please Input Value'  
					}); 
			 $loginTable = $("#loginTable").datagrid({
				 url : 'loginlog/loginTable.do',
					border : false,
					singleSelect : true,
					idField : 'id',
					pagination : true,
					fitColumns : true,
					toolbar:"#tool",
					frozenColumns : true,
					//toolbar : "#toolbar",
					pageList : [ 20,30,40,50,60 ],
					columns : [ [{
						field : 'id',
						title : "编号",
						width : 55,
						checkbox : true,
						align : "center"
					},{
						field:'userName',
						title:'用户名',
						width:55,
						align : "center"
					},{
						field:'loginTime',
						title:'登录时间',
						width:55,
						align : "center"
					},{
						field:'loginIp',
						title:'登录IP',
						width:55,
						align : "center"
					}
					  ]],
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
		},search:function(){
			var value = $('#ss').searchbox('getValue');
			  var paras = {
			        	userName:value
			        };
			  $loginTable.datagrid({
			        	queryParams:paras

			        });
			  $loginTable.datagrid('reload');
		}
};
$(function(){
	LoginLog.init();
});