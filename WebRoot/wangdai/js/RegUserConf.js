var Recharge = {
	init : function() {
		
		Sys.ajax({
			url : Sys.path + 'reguserconf/getSysSysConfigInfo.do',
 			success : function(data) {
 				if(data.rows.length!=0){
  					if(data.rows[0].canReg==1){
 						$('#openResId').attr('checked',true);
 					}
 					$('#configId').attr('value',data.rows[0].id);
  					$('#userNameFilterId').attr('value',data.rows[0].userNameFilter);
 					$('#passFilterId').attr('value',data.rows[0].passFilter);
 				}
 				
 			}
		}); 
	},
	
	configue:function(){
 	 var id=$("#configId").val();
      var canReg=0;
	  if(document.getElementById("openResId").checked){
			 canReg=1; 
	  }
      var userNameFilter= $("#userNameFilterId").val();
      var passFilter= $("#passFilterId").val();
 		Sys.ajax({
				url : Sys.path + 'reguserconf/saveOrUpdataSysConfig.do',
				data:{canReg :canReg, userNameFilter: userNameFilter,passFilter:passFilter,id:id},
				success : function(data1) {
					Recharge.init();
					Sys.showAlert("保存成功","操作提醒");
				}
		});
	} 
	 
};

$(function(){
	Recharge.init();
 });