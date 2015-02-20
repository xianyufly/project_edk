/**
 *图形处理
 */
var ChartComm = {
		/**
		 * 根据后台数据生成折线图
		 * @param categories 横坐标
		 * @param seriesData 数据
		 * @param renderDiv 放置图形的div
		 * @param dTitle 图形标题
		 * @param yTitle 图形说明
		 */
		logLineChart :function(categories,seriesData,renderDiv,dTitle,yTitle,titleTime){
			var step = categories.length/categories.length;
			if (categories.length>31) {
				step = categories.length/15;
			}
			if (yTitle != null) {
				series = [ {
					name : yTitle,
					data : seriesData
				}];
				if(categories.length>=20){
					step = 2;
				}
			}else{
				series = seriesData;
				if(categories.length>=20){
					step = 2;
				}
			}
			new Highcharts.Chart({
				chart : {
					renderTo : renderDiv,
					type : 'spline',
					marginRight : 30,
					marginBottom : 25
				},
				title : {
					text : dTitle,
					x : -20
				},
	            subtitle: {
	                text: titleTime,
	                x: -20
	            },
				xAxis : {
					categories : categories,
					 labels: {  
	                        step: step}
				},
				// y轴
				yAxis : {
					title : {
						text : yTitle
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				// 鼠标移到节点时显示的数据
				tooltip : {
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>' + this.x + ': '
								+ this.y;
					}
				},
				// 右边的tip
				legend : {
					layout : 'horizontal',
					align : 'right',
					horizontalAlign : 'top',
					x : -10,
					y : -10,
					borderWidth : 0
				},
				series : series
			});
		},
		/**
		 * 
		 * @param data 
		 * renderDiv : 要渲染到的元素的id
		 * title ： 标题
		 * fmt ： x轴时间格式
		 * yTitle : y轴标题
		 * seriesName ： 图例名称
		 * rows ： 数据
		 * start ： 开始日期
		 * interval ： 间隔毫秒数
		 */
		lineChart : function(data) {
			new Highcharts.Chart({
		        chart: {
		            renderTo: data.renderDiv
		        },
		        title:{
		        	text:data.title
		        },
		        xAxis: {
		            type: 'datetime',
		            labels: {
		                formatter: function() {
		                	 //'%Y-%m-%d'
		                     return  Highcharts.dateFormat(data.fmt, this.value);
		                }
	                }
		        },
		        yAxis: {
		        	title:{
		        		text:data.yTitle
		        	}
		        },
		        series: [{
		        	name:data.seriesName,
		            data:data.rows,
		            pointStart: Date.UTC(data.start.year,data.start.month,data.start.day),
		            pointInterval: data.interval // one day
		        }]
		    });
		},
		logLineChart3 :function(categories,series,renderDiv,dTitle,yTitle,titleTime){
			new Highcharts.Chart({
				chart : {
					renderTo : renderDiv,
					type : 'line',
					marginRight : 30,
					marginBottom : 25
				},
				title : {
					text : dTitle,
					x : -20
				},
	            subtitle: {
	                text: titleTime,
	                x: -20
	            },
				xAxis : {
					categories : categories
				},
				// y轴
				yAxis : {
					title : {
						text : yTitle
					},
					plotLines : [ {
						value : 0,
						width : 1,
						color : '#808080'
					} ]
				},
				// 鼠标移到节点时显示的数据
				tooltip : {
					formatter : function() {
						return '<b>' + this.series.name + '</b><br/>' + this.x + ': '
								+ this.y;
					}
				},
				// 右边的tip
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'top',
					x : -10,
					y : 100,
					borderWidth : 0
				},
				series : series
			});
		},
		
		logLineChart2 : function(seriesData,renderDiv,dTitle,yTitle,year,month,day){
			new Highcharts.Chart({
				chart: {
	                renderTo: renderDiv,
	                type: 'spline'
	            },
	            title: {
	                text: dTitle
	            },
	            xAxis: {
	                type: 'datetime'
	            },
	            yAxis: {
	                title: {
	                    text: yTitle
	                },
	                min: 0,
	                minorGridLineWidth: 0,
	                gridLineWidth: 0,
	                alternateGridColor: null,
	                plotBands: [  ]
	            },
	            tooltip: {
	                formatter: function() {
	                        return ''+
	                        Highcharts.dateFormat('%e. %b %Y, %H:00', this.x) +': '+ this.y ;
	                }
	            },
	            plotOptions: {
	                spline: {
	                    lineWidth: 4,
	                    states: {
	                        hover: {
	                            lineWidth: 5
	                        }
	                    },
	                    marker: {
	                        enabled: false,
	                        states: {
	                            hover: {
	                                enabled: true,
	                                symbol: 'circle',
	                                radius: 5,
	                                lineWidth: 1
	                            }
	                        }
	                    },
	                    pointInterval: 3600000, // one hour
	                    pointStart: Date.UTC(year, month, day, 0, 0, 0)
	                }
	            },
	            series : [ {
					name : yTitle,
					data : seriesData
				}],
				navigation: {
	                menuItemStyle: {
	                    fontSize: '10px'
	                }
	            }
	 
			});
		},
		/**
		 * 多条数据线
		 */
		logMoreLine:function(renderDiv,title,yTitle,year,month,day,data){
			new Highcharts.Chart({
	            chart: {
	                renderTo: renderDiv,
	                type: 'spline'
	            },
	            title: {
	                text: title
	            },
	            xAxis: {
	                type: 'datetime'
	            },
	            yAxis: {
	                title: {
	                    text: yTitle
	                },
	                min: 0,
	                minorGridLineWidth: 0,
	                gridLineWidth: 0,
	                alternateGridColor: null
	            },
	            tooltip: {
	                formatter: function() {
	                        return ''+
	                        Highcharts.dateFormat('%e. %b %Y, %H:00', this.x) +': '+ this.y +' m/s';
	                }
	            },
	            plotOptions: {
	                spline: {
	                    lineWidth: 4,
	                    states: {
	                        hover: {
	                            lineWidth: 5
	                        }
	                    },
	                    marker: {
	                        enabled: false,
	                        states: {
	                            hover: {
	                                enabled: true,
	                                symbol: 'circle',
	                                radius: 5,
	                                lineWidth: 1
	                            }
	                        }
	                    },
	                    pointInterval: 3600000, // one hour
	                    pointStart: Date.UTC(year, month, day, 0, 0, 0)
	                }
	            },
	            series: data
	            ,
	            navigation: {
	                menuItemStyle: {
	                    fontSize: '10px'
	                }
	            }
	        });
		},
/**
 * 生成饼图
 * @param seriesData 数据
 * @param renderDiv 放饼图div的id
 * @param dTitle 图形标题
 * @param yTitle 名称
 */
		logPieChart : function(seriesData, renderDiv, dTitle, yTitle) {
			new Highcharts.Chart({
				chart : {
					renderTo : renderDiv,
					plotBackgroundColor : null,
					plotBorderWidth : 1,
					plotShadow : false
				},
				title : {
					text : dTitle
				},
				tooltip : {
					pointFormat : '{series.name}:<b>{point.percentage}%</b>',
					percentageDecimals : 1
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							color : '#000000',
							connectorColor : '#000000',
							formatter : function() {
								return '<b>' + this.point.name + '</b>: '
										+ this.percentage.toFixed(1) + ' %';
							}
						}
					}
				},
				series : [ {
					type : 'pie',
					name : yTitle,
					data : seriesData
				} ]
			});
		}
};