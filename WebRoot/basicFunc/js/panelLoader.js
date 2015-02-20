/**
 * 动态加载页面
 */

var PanelLoader={
	
   //方法
	load:function(panelId,url){
		
		if($("#"+panelId)){
			$("#"+panelId).panel({
				border:false,
				noheader:true,
				height:'100%',
				fit:true,
				href:url
			});
			$("#"+panelId).panel('refresh');
		}
	
	}
	
	
};