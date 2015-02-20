var RechargeList = {
	init : function() {
 		var params = {beginTime:'',endTime:''};
		$("#grid").datagrid({
			checkbox:true,
			url : 'operlog/getOperLogList.do',
			queryParams : params,
			pageList:[25,30,35,40,45,50]
		});
	},
	renderAction : function(val, row, index) {
		var id = row.id;
 		var a1 = '<a href="javascript:void(0);" class="actionlink" onclick="RechargeList.del('+ id + ', ' + index + ');">删除</a>';
			return a1;
	},
	flush:function(){
		$('#grid').datagrid('reload');
	},
	delMore : function(id, index) {
		var rows=$('#grid').datagrid('getSelections');
		 if(rows.length==0){
			 Sys.showAlert("请选择要删除的记录","操作提醒");
			 return false;
		 }
		var ids='';
		for(var i=0;i<rows.length;i++){
			if(i==0){
				ids=rows[i].id;
			}else{
				ids=ids+','+rows[i].id;
			}
 		}
      	Sys.showConfirm("操作确认", "是否确认批量删除!!", function() {
			Sys.ajax({
				type : "post",
				url : Sys.path + 'operlog/deleteMoreOperLog.do',
				data : {
					ids:ids
				},
				success : function(data) {
					Sys.showAlert("删除成功","操作提醒");
					RechargeList.flush();
				}
			});
		});
	},
	
	del : function(id, index) {
    	Sys.showConfirm("操作确认", "是否确认删除!!", function() {
			Sys.ajax({
				type : "post",
				url : Sys.path + 'operlog/deleteOperLog.do',
				data : {
					id:id
				},
				success : function(data) {
					Sys.showAlert("删除成功","操作提醒");
					RechargeList.flush();
				}
			});
		});
	},
	search : function() {
		 
		var beginTime = $("#beginTime").datebox('getValue');
 		var endTime = $("#endTime").datebox('getValue');
 		var params = {beginTime:beginTime,endTime:endTime};
		$("#grid").datagrid({
			url : 'operlog/getOperLogList.do',
			queryParams : params
		});
	}
};

$(function(){

	RechargeList.init();
});