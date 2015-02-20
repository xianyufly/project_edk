var datagrid;

var CollectConfig={
	display : function(){
		datagrid.datagrid({
			queryParams:{
				platId:$('#search_plat').combobox('getValue')
			}
		});
		datagrid.datagrid("reload");
	},
	init : function(){
		$("#collectConfig_addCol_btn").linkbutton({});
		$("#collectConfig_cancelCol_btn").linkbutton({});
		$("#collectConfig_update_btn").linkbutton({});
		$("#collectConfig_cancelUpdate_btn").linkbutton({});
		$('#update_window').dialog({
			closed:true,
			modal:true
		});
		$('#add_window').dialog({
			closed:true,
			modal:true
		});
		$('#search_plat').combobox({
			url: Sys.path + 'collect/getPlatList.do',
			valueField:'id',
			textField:'plat_name'
		});
		datagrid = $('#dg').datagrid({
			url: Sys.path + 'collect/getCollectConfigList.do',
			idField:'id',
			queryParams:{
				platId:""
			},			
			//height:830,
			border:false,
			singleSelect:true,
			pagination:true,
			fit : true,
			fitColumns:true,
			frozenColumns : true,			
			pageSize:20,
			pageList:[5,10,20,30],
			//pageList:[25,30,40,50],
			loadMsg:'数据加载中，请稍后。。。',						
			columns:[[{
				field:'id',
				title:'编号',
				width:20,
				align:'center'
			},{
				field:'collect_switch',
				title:'开关',
				width:20,
				align:'center',
				formatter:function(value,rowData,index){
					if(value==1){
						return "开";
					}else if(value==0){
						return "关";
					}
				}
			},{
				field:'plat_name',
				title:'平台',
				width:40,
				align:'center'
			},{
				field:'collection_id',
				title:'采集器ID',
				width:25,
				align:'center'
			},{
				field:'collect_pages_days',
				title:'采集页数/天数',
				width:40,
				align:'center'
			},{
				field:'last_collect_time',
				title:'最后采集时间',
				width:50,
				align:'center'
			},{
				field:'collect_interval',
				title:'时间间隔',
				width:30,
				align:'center'
			},{
				field:'collect_url',
				title:'采集路径1',
				width:100,
				align:'center'
			},{
				field:'collect_url2',
				title:'采集路径2',
				width:100,
				align:'center'
			},{
				field:'collect_type',
				title:'采集类型',
				width:30,
				align:'center',
				formatter:function(value,rowData,index){
					if(value==1){
						return "页数";
					}else if(value==2){
						return "天数";
					}
				}
			},{
				field:'url_type',
				title:'路径类型',
				width:30,
				align:'center',
				formatter:function(value,rowData,index){
					if(value==1){
						return "html";
					}else if(value==2){
						return "json";
					}
				}
			},{
				field:'is_rewrite',
				title:'是否伪静态',
				width:30,
				align:'center',
				formatter:function(value,rowData,index){
					if(value==1){
						return "是";
					}else if(value==0){
						return "否";
					}
				}
			},{
				field:'url_error_count',
				title:'地址错误次数',
				width:30,
				align:'center'
			},{
				field:'regular_error',
				title:'采集信息是否错误',
				width:40,
				align:'center',
				formatter:function(value,rowData,index){
					if(value==0){
						return "错误";
					}else if(value==1){
						return "正确";
					}
				}
			}]],
			toolbar:"#collect_toolbar"
		});
	},
	add : function(){
		CollectConfig.clearAll();
		//console.info('add');
		//console.info($('#add_window'));
		$('#add_window').dialog("open");
		//console.info("add2")
	},
	update : function(){
		var row=$('#dg').datagrid('getSelected');
		if(!row){
			$.messager.alert('提示','请选择要修改的记录。。','info');
		}else{
			console.info("id:"+row.id);
			$('#id').val(row.id);
			if(row.collect_switch == 1){
				$('#open').attr('checked','checked');
			}else if(row.collect_switch == 0){
				$('#close').attr('checked','checked');
			}else{
				$('#open').attr('checked',false);
				$('#close').attr('checked',false);
			}
			$('#plat').combobox('setValue',row.p_id);
			$('#collection').val(row.collection_id);
			$('#pages_days').val(row.collect_pages_days);
			$('#last_collect_time').val(row.last_collect_time);
			$('#interval').val(row.collect_interval);
			$('#url1').val(row.collect_url);
			$('#url2').val(row.collect_url2);
			$('#regular').val(row.regular);
			$('#other_rule').val(row.other_rule);
			$('#title').val(row.regular_title);
			$('#other_title').val(row.rule_title);
			$('#regular_publish_time').val(row.regular_publish_time);
			$('#rule_publish_time').val(row.rule_publish_time);
			$('#regular_sole_number').val(row.regular_sole_number);
			$('#rule_sole_number').val(row.rule_sole_number);
			$('#rate').val(row.regular_rate);
			$('#rule_rate').val(row.rule_rate);
			$('#regular_loan_money').val(row.regular_loan_money);
			$('#rule_loan_money').val(row.rule_loan_money);
			$('#regular_repayment_date').val(row.regular_repayment_date);
			$('#rule_repayment_date').val(row.rule_repayment_date);
			$('#regular_repayment_type').val(row.regular_repayment_type);
			$('#rule_repayment_type').val(row.rule_repayment_type);
			$('#regular_bid_schedule').val(row.regular_bid_schedule);
			$('#rule_bid_schedule').val(row.rule_bid_schedule);
			if(row.collect_type == 1){
				$('#pages').attr('checked','checked');
			}else if(row.collect_type == 2){
				$('#days').attr('checked','checked');
			}else{
				$('#pages').attr('checked',false);
				$('#days').attr('checked',false);
			}
			$('#collect_url_page').val(row.collect_url_page);
			$('#collect_url2_page').val(row.collect_url2_page);
			if(row.url_type == 1){
				$('#html').attr('checked','checked');
			}else if(row.url_type == 2){
				$('#json').attr('checked','checked');
			}else{
				$('#html').attr('checked',false);
				$('#json').attr('checked',false);
			}
			if(row.is_rewrite == 1){
				$('#yes').attr('checked','checked');
			}else if(row.is_rewrite == 0){
				$('#no').attr('checked','checked');
			}else{
				$('#yes').attr('checked',false);
				$('#no').attr('checked',false);
			}
			$('#publish_formater').val(row.publish_formater);
			$('#update_window').dialog('open');
		}
	},
	deleteCollect : function(){
		var row=$('#dg').datagrid('getSelected');
		var index=$('#dg').datagrid('getRowIndex',row);
		if(!row){
			$.messager.alert('提示','请选择要删除的记录。。。','info');
		}else{
			$.messager.confirm('提示','确定删除选定记录？',function(r){
				if(r){
					$.ajax({
						url: Sys.path + 'collect/deleteRecord.do',
						dataType:'json',
						type:'post',
						data:{id:row.id},
						success:function(){
							$('#dg').datagrid('deleteRow',index);
							$('#dg').datagrid('reload');
						}
					});
				}
			});
		}
	},
	cancelUpdate :function(){
		$('#update_window').dialog('close');
	},
	getPlat : function(){
		$('#plat').combobox({
			url: Sys.path + 'collect/getPlatList.do',
			valueField:'id',
			textField:'plat_name'
		});
		$('#add_plat').combobox({
			url: Sys.path + 'collect/getPlatList.do',
			valueField:'id',
			textField:'plat_name'
		});
	},
	updateCollectConfig : function(){
		var id=$('#id').val();
		var collect_switch;
		if($('#open').attr('checked') == 'checked'){
			collect_switch=$('#open').val();
		}else{// if($('#close').attr('checked') == 'checked'){
			collect_switch=$('#close').val();
		}
		var plat=$('#plat').combobox('getValue');
		var collection=$('#collection').val();
		var pages_days=$('#pages_days').val();
		var interval=$('#interval').val();
		var url1=$('#url1').val();
		var url2=$('#url2').val();
		var regular=$('#regular').val();
		var other_rule=$('#other_rule').val();
		var regular_title=$('#title').val();
		var rule_title=$('#other_title').val();
		var regular_publish_time=$('#regular_publish_time').val();
		var rule_publish_time=$('#rule_publish_time').val();
		var regular_sole_number=$('#regular_sole_number').val();
		var rule_sole_number=$('#rule_sole_number').val();
		var regular_rate=$('#rate').val();
		var rule_rate=$('#rule_rate').val();
		var regular_loan_money=$('#regular_loan_money').val();
		var rule_loan_money=$('#rule_loan_money').val();
		var regular_repayment_date=$('#regular_repayment_date').val();
		var rule_repayment_date=$('#rule_repayment_date').val();
		var regular_repayment_type=$('#regular_repayment_type').val();
		var rule_repayment_type=$('#rule_repayment_type').val();
		var regular_bid_schedule=$('#regular_bid_schedule').val();
		var rule_bid_schedule=$('#rule_bid_schedule').val();
		var collect_type;
		if($('#pages').attr('checked') == 'checked'){
			collect_type = 1;
		}else{// if($('#days').attr('checked') == 'checked'){
			collect_type=2;
		}
		var collect_url_page=$('#collect_url_page').val();
		var collect_url2_page=$('#collect_url2_page').val();
		var url_type;
		if($('#html').attr('checked') == 'checked'){
			url_type = 1;
		}else{ //if($('#json').attr('checked') == 'checked'){
			url_type=2;
		}
		var is_rewrite;
		if($('#yes').attr('checked') == 'checked'){
			is_rewrite = 1;
		}else{// if($('#no').attr('checked') == 'checked'){
			is_rewrite=0;
		}
		var publish_formatter=$('#publish_formater').val();
		$.ajax({
			url: Sys.path + 'collect/updateCollectConfig.do',
			dataType:'json',
			type:'post',
			data:{
				id:id,
				collectSwitch:collect_switch,
				platId:plat,
				collectionId:collection,
				collectPagesDays:pages_days,
				collectInterval:interval,
				collectUrl:url1,
				regular:regular,
				otherRule:other_rule,
				regularTitle:regular_title,
				ruleTitle:rule_title,
				regularPublishTime:regular_publish_time,
				rulePublishTime:rule_publish_time,
				regularSoleNumber:regular_sole_number,
				ruleSoleNumber:rule_sole_number,
				regularRate:regular_rate,
				ruleRate:rule_rate,
				regularLoanMoney:regular_loan_money,
				ruleLoanMoney:rule_loan_money,
				regularRepaymentDate:regular_repayment_date,
				ruleRepaymentDate:rule_repayment_date,
				regularRepaymentType:regular_repayment_type,
				ruleRepaymentType:rule_repayment_type,
				regularBidSchedule:regular_bid_schedule,
				ruleBidSchedule:rule_bid_schedule,
				collectType:collect_type,
				collectUrl2:url2,
				collectUrlPage:collect_url_page,
				collectUrl2Page:collect_url2_page,
				urlType:url_type,
				reWrite:is_rewrite,
				publishFormat:publish_formatter,
				isDelele:0
			},
			success:function(data){
				//console.info(data);
				if(data.success == 1){
					$.messager.alert('提示','修改成功。。。','info');
					$('#update_window').dialog('close');
					$('#dg').datagrid('reload');
				}else{
					$.messager.alert('提示','修改失败。。','info');
				}
			}
		});
	},
	cancelAdd : function(){
		CollectConfig.clearAll();
		$('#add_window').dialog('close');
	},
	addCollectConfig : function(){
		var collect_switch;
		if($('#add_open').attr('checked') == 'checked'){
			collect_switch=1;
		}else{ //if($('#add_close').attr('checked') == 'checked'){
			collect_switch=0;
		}
		var plat=$('#add_plat').combobox('getValue');
		var collection=$('#add_collection').val();
		var pages_days=$('#add_pages_days').val();
		var interval=$('#add_interval').val();
		var url1=$('#add_url1').val();
		var url2=$('#add_url2').val();
		var regular=$('#add_regular').val();
		var other_rule=$('#add_other_rule').val();
		var regular_title=$('#add_title').val();
		var rule_title=$('#add_other_title').val();
		var regular_publish_time=$('#add_regular_publish_time').val();
		var rule_publish_time=$('#add_rule_publish_time').val();
		var regular_sole_number=$('#add_regular_sole_number').val();
		var rule_sole_number=$('#add_rule_sole_number').val();
		var regular_rate=$('#add_rate').val();
		var rule_rate=$('#add_rule_rate').val();
		var regular_loan_money=$('#add_regular_loan_money').val();
		var rule_loan_money=$('#add_rule_loan_money').val();
		var regular_repayment_date=$('#add_regular_repayment_date').val();
		var rule_repayment_date=$('#add_rule_repayment_date').val();
		var regular_repayment_type=$('#add_regular_repayment_type').val();
		var rule_repayment_type=$('#add_rule_repayment_type').val();
		var regular_bid_schedule=$('#add_regular_bid_schedule').val();
		var rule_bid_schedule=$('#add_rule_bid_schedule').val();
		var collect_type;
		if($('#add_pages').attr('checked') == 'checked'){
			collect_type = 1;
		}else {//if($('#add_days').attr('checked') == 'checked'){
			collect_type=2;
		}
		var collect_url_page=$('#add_collect_url_page').val();
		var collect_url2_page=$('#add_collect_url2_page').val();
		var url_type;
		if($('#add_html').attr('checked') == 'checked'){
			url_type = 1;
		}else {//if($('#add_json').attr('checked') == 'checked'){
			url_type=2;
		}
		var is_rewrite;
		if($('#add_yes').attr('checked') == 'checked'){
			is_rewrite = 1;
		}else {//if($('#add_no').attr('checked') == 'checked'){
			is_rewrite=0;
		}
		var publish_formatter=$('#add_publish_formatter').val();
//		var error_count=$('#add_error_count').val();
//		var regular_error;
//		if($('#error').attr('checked') == 'checked'){
//			regular_error = 0;
//		}else if($('#right').attr('checked') == 'checked'){
//			regular_error=1;
//		}else{
//			regular_error="";
//		}
//		var flag;
//		if(collect_switch!="" || plat!="" || collection!="" || pages_days!="" || interval!="" || 
//				url1!="" || url2!="" || regular!="" || other_rule!="" || regular_title!="" || 
//				rule_title!="" || regular_publish_time!="" || rule_publish_time!="" || regular_sole_number!="" ||
//				rule_sole_number!="" || regular_rate!="" || rule_rate!="" || regular_loan_money!="" || rule_loan_money!="" ||
//				regular_repayment_date!="" || rule_repayment_date!="" || regular_repayment_type!="" || rule_repayment_type!="" ||
//				regular_bid_schedule!="" || rule_bid_schedule!="" || collect_type!="" || collect_url_page!="" || collect_url2_page!=""){
//			flag=true;
//		}else{
//			flag=false;
//		}
		//console.info("1:"+collect_switch);
		//console.info("2:"+collect_type);
//		if(flag){
			$.ajax({
				url: Sys.path + 'collect/insertCollectConfig.do',
				dataType:'json',
				type:'post',
				data:{
					collectSwitch:collect_switch,
					platId:plat,
					collectionId:collection,
					collectPagesDays:pages_days,
					collectInterval:interval,
					collectUrl:url1,
					collectUrl2:url2,
					regular:regular,
					otherRule:other_rule,
					regularTitle:regular_title,
					ruleTitle:rule_title,
					regularPublishTime:regular_publish_time,
					rulePublishTime:rule_publish_time,
					regularSoleNumber:regular_sole_number,
					ruleSoleNumber:rule_sole_number,
					regularRate:regular_rate,
					ruleRate:rule_rate,
					regularLoanMoney:regular_loan_money,
					ruleLoanMoney:rule_loan_money,
					regularRepaymentDate:regular_repayment_date,
					ruleRepaymentDate:rule_repayment_date,
					regularRepaymentType:regular_repayment_type,
					ruleRepaymentType:rule_repayment_type,
					regularBidSchedule:regular_bid_schedule,
					ruleBidSchedule:rule_bid_schedule,
					collectType:collect_type,
					collectUrlPage:collect_url_page,
					collectUrl2Page:collect_url2_page,
					urlType:url_type,
					reWrite:is_rewrite,
					publishFormat:publish_formatter,
					urlErrorCount:0,
					regularError:1,
					isDelete:0
				},
				success:function(data){
					console.info(data);
					if (data.success == 1) {
						$.messager.alert('提示', '添加成功。。。', 'info');
						$('#add_window').dialog('close');
						$('#dg').datagrid('reload');
					} else {
						$.messager.alert('提示', data.msg, 'info');
						//$('#add_window').dialog('close');
					}
				}
			});
//		}else{
//			$.messager.alert('提示','添加数据不能全部为空，请重新添加。。','info');
//		}
		
	},
	clearAll : function(){
//		if($('#add_open').attr('checked') == 'checked'){
//			$('#add_open').attr('checked',false);
//		}
//		if($('#add_close').attr('checked') == 'checked'){
//			$('#add_close').attr('checked',false);
//		}
		$('#add_plat').combobox('setValue',"");
		$('#add_collection').val('');
		$('#add_pages_days').val('');
		$('#add_interval').val('');
//		if($('#add_days').attr('checked') == 'checked'){
//			$('#add_days').attr('checked',false);
//		}
//		if($('#add_pages').attr('checked') == 'checked'){
//			$('#add_pages').attr('checked',false);
//		}
		$('#add_url1').val('');
		$('#add_url2').val('');
		$('#add_regular').val('');
		$('#add_other_rule').val('');
		$('#add_title').val('');
		$('#add_other_title').val('');
		$('#add_regular_publish_time').val('');
		$('#add_rule_publish_time').val('');
		$('#add_regular_sole_number').val('');
		$('#add_rule_sole_number').val('');
		$('#add_rate').val('');
		$('#add_rule_rate').val('');
		$('#add_regular_loan_money').val('');
		$('#add_rule_loan_money').val('');
		$('#add_regular_repayment_date').val('');
		$('#add_rule_repayment_date').val('');
		$('#add_regular_repayment_type').val('');
		$('#add_rule_repayment_type').val('');
		$('#add_regular_bid_schedule').val('');
		$('#add_rule_bid_schedule').val('');
		$('#add_collect_url_page').val('');
		$('#add_collect_url2_page').val('');
//		if($('#add_html').attr('checked') == 'checked'){
//			$('#add_html').attr('checked',false);
//		}
//		if($('#add_json').attr('checked') == 'checked'){
//			$('#add_json').attr('checked',false);
//		}
//		if($('#add_yes').attr('checked') == 'checked'){
//			$('#add_yes').attr('checked',false);
//		}
//		if($('#add_no').attr('checked') == 'checked'){
//			$('#add_no').attr('checked',false);
//		}
		$('#add_publish_formatter').val('');
	}
};

$(function() {	
	CollectConfig.init();
	//CollectConfig.display();
	CollectConfig.getPlat();
});

