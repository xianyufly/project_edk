/*
 * index.html页
 * 菜单栏
 * 
 */
$(function(){
	(function($){
	     var menu={
	    		 handleSidebarAndContentHeight:function() {
	    		        var content = $('.page-content');
	    		        var sidebar = $('.page-sidebar');
	    		        var body = $('body');
	    		        var height;
	    		
	    		        if (body.hasClass("page-footer-fixed") === true && body.hasClass("page-sidebar-fixed") === false) {
	    		            var available_height = $(window).height() - $('.footer').height();
	    		            if (content.height() <  available_height) {
	    		                content.attr('style', 'min-height:' + available_height + 'px !important');
	    		            }
	    		        } else {
	    		            if (body.hasClass('page-sidebar-fixed')) {
	    		                height = _calculateFixedSidebarViewportHeight();
	    		            } else {
	    		                height = sidebar.height() + 20;
	    		            }
	    		            if (height >= content.height()) {
	    		                content.attr('style', 'min-height:' + height + 'px !important');
	    		            } 
	    		        }          
	    		    },
	    		    handleSidebarMenu:function () {
				        jQuery('.page-sidebar').on('click', 'li > a', function (e) {
				                if ($(this).next().hasClass('sub-menu') == false) {
				                    if ($('.btn-navbar').hasClass('collapsed') == false) {
				                        $('.btn-navbar').click();
				                    }
				                    return;
				                }
				
				                var parent = $(this).parent().parent();
								
				                parent.children('li.open').children('a').children('.arrow').removeClass('open');
				                parent.children('li.open').children('.sub-menu').slideUp(200);
				                parent.children('li.open').removeClass('open');
				                
				               
				                var sub = jQuery(this).next();
				                if (sub.is(":visible")) {
				                    jQuery('.arrow', jQuery(this)).removeClass("open");
				                    jQuery(this).parent().removeClass("open");
				                    sub.slideUp(200, function () {
				                    	menu.handleSidebarAndContentHeight();
				                        });
				                } else {
				                    jQuery('.arrow', jQuery(this)).addClass("open");
				                    jQuery(this).parent().addClass("open");
				                    sub.slideDown(200, function () {
				                    	menu.handleSidebarAndContentHeight();
				                        });
				                }
				                parent.children('li').removeClass("active");
								$(this).parent().addClass("active");
				
				                e.preventDefault();
				            });
			            },
			        handleSideBarSubMenu:function(){
			        	$("ul.sub-menu>li>a").click(function(){
			        		$("ul.sub-menu>li").removeClass("active");
			        		$(this).parent().addClass("active");
			        		console.log($(this).attr("url"));
			        		PanelLoader.load("main", $(this).attr("url"));
			        	});
			        },
			        paddingData:function(){
			        	//填充菜单数据
			        	Sys.ajaxPost("sys/initIndex.do", null, function(data) {
			        		var json=JSON.StrToJSON(data);
			        		if(json.success==0){
			        			$('.page-sidebar-menu').empty();
			        			for(var i=0;i<json.tree.length;i++){
			        				var menuData=json.tree[i];
			        				var state='close';
			        				if(i==0){
			        					state='open';
			        				}
			        				var html=menu.createMenu(menuData,state);
			        				$('.page-sidebar-menu').append(html);
			        			}
			        		}
			        		menu.handleSidebarMenu();
		    		    	menu.handleSideBarSubMenu();
			        	});
			        },
			        //生成菜单html
			        createMenu:function(data,state){
			        	
			        	var cssClass=[];
			        	//是否打开
			        	if(state=='open'){
			        		cssClass.push("start");
			        		cssClass.push("active");
			        		cssClass.push("open");
			        	}
			        	var html='<li class="'+cssClass.join(" ")+'">'+
						'<a href="index.html"> <i class="icon-home"></i> <span'+
							'class="title">'+data.text+'</span><span class="arrow "></span> </a>'+
						'<ul class="sub-menu">'
			        	
			        	//判断是否有孩子菜单
			        	if(data.children&&data.children.length>0){
			        		for(var i=0;i<data.children.length;i++){
			        			var menuData=data.children[i];
			        			var cls="";
			        			if(state=='open'&&i==0){
			        				cls="active";
			        			}
			        			html=html+
									'<li class="'+cls+'">'+
										'<a href="javascript:void(0);" url="'+menuData.attributes.url+'"> '+menuData.text+'</a>'+
									'</li>';
			        		}
			        	}
			        	
			        	html=html+'</ul></li>';
			        	return html;
			        },
			        
	    		    init:function(){
			        	//body 大小自适应
			        	$('.page-content').height($(window).outerHeight()-$(".header").height()-$(".footer").outerHeight());
			        	menu.paddingData();
	    		    }
	     };
	     menu.init();
	})(jQuery); 
});