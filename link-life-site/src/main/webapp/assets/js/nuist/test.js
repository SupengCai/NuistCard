$(function() {
	var title_week = {
		text : "本周",
	};
	var title_term = {
		text : "本学期",
	};
	var title_all = {
		text : "全部",
	};

	// 坐标轴分类
	var categories_week = [ "周一", "周二", "周三", "周四", "周五", "周六", "周日" ];
	var categories_term_spring = [ "3月", "4月", "5月", "6月", "7月", "8月" ];
	var categories_term_autumn = [ "9月", "10月", "11月", "12月", "1月", "2月" ];

	// Chart数据模拟
	var data_week = [ 10, 40, 50, 20, 40, 50, 20 ];
	var data_month = [ 10, 40, 50, 20 ];
	var data_term_spring = [ 10, 40, 50, 20, 50, 20 ];
	var data_term_autumn = [ 101, 40, 91, 20, 30, 20 ];
	var data_term_year = [ 10, 40, 50, 20, 10, 40, 50, 20, 10, 40, 50, 20 ];
	var data_term_all = [ 1000, 4000, 5000, 2000 ];

	$("#btn-amount-week").click(
			function() {
				// 弹出modal窗口
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=0", function(
						data) {
					$.showModal();
					$.updateDate(data, 0);
					$.hideModal();
				});
			});

	$("#btn-amount-term").click(
			function() {
				// 弹出modal窗口
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=1", function(
						data) {
					$.showModal();
					$.updateDate(data, 1);
					$.hideModal();
				});
			});

	$("#btn-amount-all").click(
			function() {
				// 弹出modal窗口
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=2", function(
						data) {
					$.showModal();
					$.updateDate(data, 2);
					$.hideModal();
				});
			});
	$("#btn-area-week").click(
			function() {
				// 弹出modal窗口
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=0", function(
						data) {
					$.showModal();
					$.updateDate(data, 0);
					$.hideModal();
				});
			});

	$("#btn-area-term").click(
			function() {
				// 弹出modal窗口
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=1", function(
						data) {
					$.showModal();
					$.updateDate(data, 1);
					$.hideModal();
				});
			});

	$("#btn-area-all").click(
			function() {
				// 弹出modal窗口
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=2", function(
						data) {
					$.showModal();
					$.updateDate(data, 2);
					$.hideModal();
				});
			});
	$("#btn-amount-test").click(function() {
		$('#td_consumeamount').text("1" + "元");
		$('#td_consumetime').text("2" + "次");
		$('#td_rechargeamount').text("3" + "元");
		$('#td_rechargetime').text("4" + "次");
		$('#td_averageconsume').text("5" + "元");
		$('#td_eastamount').text("6" + "元");
		$('#td_easttime').text("7" + "次");
		$('#td_centreamount').text("8" + "元");
		$('#td_centretime').text("9" + "次");
		$('#td_westamount').text("10" + "元");
		$('#td_westtime').text("11" + "元");
		$('#td_avg_eastamount').text("12" + "次");
		$('#td_avg_easttime').text("13" + "元");
		$('#td_avg_centreamount').text("14" + "次");
		$('#td_avg_centretime').text("15" + "元");
		$('#td_avg_westamount').text("16" + "次");
		$('#td_avg_westtime').text("17" + "元");
		$.updateDate([ 0 ], 2);

	});
	$("#btn-area-test").click(function() {

		$.showModal();
		alert("close");
		$.hideModal();

	});

	var chart_amountdata = new Highcharts.Chart({
		chart : {
			renderTo : 'container_amountdata',
			type : 'column',
			margin : 75,
		},
		colors : [ '#7CB5EC', '#90ED7D', '#F7A35C', ],
		credits : {
			enabled : false
		// 禁用版权信息
		},
		title : {// 主标题
			text : '我的消费金额'
		},
		subtitle : {// 副标题
			text : '本周'
		},
		plotOptions : {// 为每个节点显示值
			column : {
				depth : 25
			}
		},
		xAxis : {
			categories : [ "2012年", "2013年", "2014年", "2015年" ]
		// Highcharts.getOptions().lang.shortMonths
		},
		yAxis : {
			title : {
				text : '金额 /元'
			},
			labels : {// 格式化纵坐标的显示风格
				formatter : function() {
					return this.value;
				}
			},
			opposite : false
		// 反转
		},
		legend : {// 是否显示底注
			enabled : true
		},
		series : [ {// 图表数值
			name : '消费金额',
			// colorByPoint: true, //为每个柱子显示不同颜色
			data : [ 1000, 4000, 5000, 2000 ]
		}, {// 图表数值
			name : '充值金额',
			// colorByPoint: true, //为每个柱子显示不同颜色
			data : [ 1000, 4000, 5000, 2000 ]
		}
		/*
		 * ,{ name: '数量', data: [2, 3, null, 4, 0 ] }
		 */
		],
		tooltip : {
			shared : true,// 节点数据框共享
		/*
		 * enabled: true,//每个节点显示数据框 formatter: function() {//格式化每个节点数据框 return '<b>'+
		 * this.x +'</b><br>'+ this.series.name +': '+ this.y; }
		 */
		},
	/*
	 * plotOptions: {//为每个节点显示值 column: { dataLabels: { enabled: true },
	 * enableMouseTracking: true }, },
	 */
	});

	var chart_amounttime = new Highcharts.Chart({
		chart : {
			renderTo : 'container_amounttime',
			type : 'column',
			margin : 75,
		},
		colors : [ '#7CB5EC', '#90ED7D', '#F7A35C', ],
		credits : {
			enabled : false
		// 禁用版权信息
		},
		title : {// 主标题
			text : '我的消费次数'
		},
		subtitle : {// 副标题
			text : '本周'
		},
		plotOptions : {// 为每个节点显示值
			column : {
				depth : 25
			}
		},
		xAxis : {
			categories : [ "2012年", "2013年", "2014年", "2015年" ]
		// Highcharts.getOptions().lang.shortMonths
		},
		yAxis : {
			title : {
				text : '次数 /次'
			},
			labels : {// 格式化纵坐标的显示风格
				formatter : function() {
					return this.value;
				}
			},
			opposite : false
		// 反转
		},
		legend : {// 是否显示底注
			enabled : true
		},
		series : [ {// 图表数值
			name : '消费次数',
			data : [ 10, 40, 50, 20 ]
		}, {// 图表数值
			name : '充值次数',
			data : [ 10, 40, 50, 20 ]
		} ],
		tooltip : {
			shared : true,// 节点数据框共享
		},
	});

	var chart_areadata = new Highcharts.Chart({
		chart : {
			renderTo : 'container_areadata',
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false
		},
		colors : [ '#7CB5EC', '#90ED7D', '#F7A35C', ],
		credits : {
			enabled : false
		// 禁用版权信息
		},
		title : {
			text : '消费金额'
		},
		tooltip : {
			pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : false
				},
				showInLegend : true
			}
		},
		series : [ {
			type : 'pie',
			name : '消费金额',
			data : [ [ '东院', 45.0 ], [ '中院', 26.8 ], [ '西院', 8.5 ] ]
		} ]
	});

	var chart_areatime = new Highcharts.Chart({
		chart : {
			renderTo : 'container_areatime',
			plotBackgroundColor : null,
			plotBorderWidth : null,
			plotShadow : false
		},
		colors : [ '#7CB5EC', '#90ED7D', '#F7A35C', ],
		credits : {
			enabled : false
		// 禁用版权信息
		},
		title : {
			text : '消费次数'
		},
		tooltip : {
			pointFormat : '{series.name}: <b>{point.percentage:.1f}%</b>'
		},
		plotOptions : {
			pie : {
				allowPointSelect : true,
				cursor : 'pointer',
				dataLabels : {
					enabled : false
				},
				showInLegend : true
			}
		},
		series : [ {
			type : 'pie',
			name : '消费次数 ',
			data : [ [ '东院', 45.0 ], [ '中院', 26.8 ], [ '西院', 8.5 ] ]
		} ]
	});

	$.showModal = function() {
		$("#post-modal").show();
		$("#progressing").show();
		$("#btn-postconfrim").hide();
	}

	$.hideModal = function() {
		$("#post-modal").hide();
		$("#progressing").hide();
		$("#btn-postconfrim").show();
	}

	$.updateDate = function(date, historyType) {
		switch (historyType) {
		case 0:// 本周
			// 设置标题
			chart_amountdata.setTitle(null, title_week);
			chart_amounttime.setTitle(null, title_week);
			chart_areadata.setTitle(null, title_week);
			chart_areatime.setTitle(null, title_week);

			// 设置x轴参数项
			chart_amountdata.xAxis[0].setCategories(categories_week);
			chart_amounttime.xAxis[0].setCategories(categories_week);
			break;
		case 1:// 本学期
			// 设置标题
			chart_amountdata.setTitle(null, title_term);
			chart_amounttime.setTitle(null, title_term);
			chart_areadata.setTitle(null, title_term);
			chart_areatime.setTitle(null, title_term);

			// 设置x轴参数项
			var date = new Date;
			var month = date.getMonth() + 1;
			if (month > 2 && month < 9) {
				chart_amountdata.xAxis[0].setCategories(categories_term_spring);
				chart_amounttime.xAxis[0].setCategories(categories_term_spring);
			} else {
				chart_amountdata.xAxis[0].setCategories(categories_term_autumn);
				chart_amounttime.xAxis[0].setCategories(categories_term_spring);
			}
			break;
		case 2:// 全部
			// 设置标题
			chart_amountdata.setTitle(null, title_all);
			chart_amounttime.setTitle(null, title_all);
			chart_areadata.setTitle(null, title_all);
			chart_areatime.setTitle(null, title_all);

			// 设置x轴参数项
			chart_amountdata.xAxis[0].setCategories(data.categories);
			chart_amounttime.xAxis[0].setCategories(data.categories);
			break;
		default:
			break;
		}
		// 设置金额数据
		chart_amountdata.series[0].setData(data.consumAmounts);
		chart_amountdata.series[1].setData(data.rechargeAmounts);
		chart_amounttime.series[0].setData(data.comsumTimes);
		chart_amounttime.series[1].setData(data.rechargeTimes);

		// 设置区域数据
		var areaData = [];
		var areaTime = [];
		areaData.push([ '东院', data.areaConsumAmounts[0] ]);
		areaData.push([ '中院', data.areaConsumAmounts[1] ]);
		areaData.push([ '西院', data.areaConsumAmounts[2] ]);
		areaTime.push([ '东院', data.areaConsumTimes[0] ]);
		areaTime.push([ '中院', data.areaConsumTimes[1] ]);
		areaTime.push([ '西院', data.areaConsumTimes[2] ]);
		chart_areadata.series[0].setData(areaData);
		chart_areatime.series[0].setData(areaTime);

		// 设置金额表格数据
		$('#td_consumeamount').text(data.consumeAmount + "元");
		$('#td_consumetime').text(data.consumeTime + "次");
		$('#td_rechargeamount').text(data.rechargeAmount + "元");
		$('#td_rechargetime').text(data.rechargeTime + "次");
		$('#td_averageconsume').text(data.averageConsume + "元");
		// 设置区域表格数据
		$('#td_eastamount').text(data.areaConsumAmounts[0] + "元");
		$('#td_easttime').text(data.areaConsumTimes[0] + "次");
		$('#td_centreamount').text(data.areaConsumAmounts[1] + "元");
		$('#td_centretime').text(data.areaConsumTimes[1] + "次");
		$('#td_westamount').text(data.areaConsumAmounts[2] + "元");
		$('#td_westtime').text(data.areaConsumTimes[2] + "次");
		$('#td_avg_eastamount').text(data.averageAreaConsum[0] + "元");
		$('#td_avg_easttime').text(data.averageAreaConsumTimes[0] + "次");
		$('#td_avg_centreamount').text(data.averageAreaConsum[1] + "元");
		$('#td_avg_centretime').text(data.averageAreaConsumTimes[1] + "次");
		$('#td_avg_westamount').text(data.averageAreaConsum[2] + "元");
		$('#td_avg_westtime').text(data.averageAreaConsumTimes[2] + "次");
	}
});