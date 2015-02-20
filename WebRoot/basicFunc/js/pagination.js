var curPage = 1;
var rows = 10;
var Page = {
	toPage : function(grid,page) {
		var params = $(grid).datagrid('options').queryParams;
		params.page = page;
		curPage = page;
		//设置当前页样式不起作用 ？？
		var pages = $(".pagination ul li");
		$.each(pages,function(i,n){
			$(n).attr('class','');
			if($(n).text()==curPage) {
				$(n).attr('style','active');
			}
		});
		$(grid).datagrid({
			queryParams : params
		});
	},
	renderPagination : function(grid,total) {
		var pageNum = 0;
		var html = "";
		var prePage = 1;
		var nextPage = 1;
		if(total%rows!=0) {
			pageNum = parseInt(total/rows) + 1;
		} else {
			pageNum = total/rows;
		}
		if(curPage<=1) {
			prePage = 1;
		} else {
			prePage = curPage - 1;
		}
		if(curPage<pageNum) {
			nextPage = curPage + 1;
		} else {
			nextPage = pageNum;
		}
		var pre = "<li><a href='#' onclick=Page.toPage('"+grid+"',"+prePage+")>上一页</a></li>";
		var next = "<li><a href='#' onclick=Page.toPage('"+grid+"',"+nextPage+")>下一页</a></li>";
		html = html + pre;
		for(var i=1 ; i<=pageNum ; i++) {
			html = html+"<li><a href='#' onclick=Page.toPage('"+grid+"',"+i+")>"+i+"</a></li>";
		}
		html = html +next;
		$(".pagination ul").children().remove();
		$(".pagination ul").append(html);
	}
};