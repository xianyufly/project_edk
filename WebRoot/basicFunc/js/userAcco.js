var userAcco={
				init:function(){
				$('#tt').datagrid({   
				     //url:'datagrid_data.json',   
					 options:"",
				      columns:[[   
				          {field:'code',title:'Code',width:100},   
				          {field:'name',title:'Name',width:100},   
				          {field:'price',title:'Price',width:100,align:'right'}   
				      ]],
				      fit:true,
				      pagination:true
				  });  
				}
		};
$(function(){
	userAcco.init();
});