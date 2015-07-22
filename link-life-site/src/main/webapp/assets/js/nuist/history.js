$(function() {
	var inited = false;
	var title_week = {
		text : "本周",
	};
	var title_term = {
		text : "本学期",
	};
	var title_all = {
		text : "全部",
	};

	var categories_week = [ "周一", "周二", "周三", "周四", "周五", "周六", "周日" ];
	var categories_term_spring = [ "3月", "4月", "5月", "6月", "7月", "8月" ];
	var categories_term_autumn = [ "9月", "10月", "11月", "12月", "1月", "2月" ];

	$("#btn-amount-week").click(
			function() {
				$.showModal();
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=0", function(
						data) {
					$.updateDate(data, 0);
				});
			});

	$("#btn-amount-term").click(
			function() {
				$.showModal();
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=1", function(
						data) {
					$.updateDate(data, 1);
				});
			});

	$("#btn-amount-all").click(
			function() {
				$.showModal();
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=2", function(
						data) {
					$.updateDate(data, 2);
				});
			});
	$("#btn-area-week").click(
			function() {
				$.showModal();
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=0", function(
						data) {
					$.updateDate(data, 0);
				});
				$.hideModal();
			});

	$("#btn-area-term").click(
			function() {
				$.showModal();
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=1", function(
						data) {
					$.updateDate(data, 1);
				});
			});

	$("#btn-area-all").click(
			function() {
				$.showModal();
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=2", function(
						data) {
					$.updateDate(data, 2);
				});
			});
	$("#btn-list-week").click(
			function() {
				$.showModal();
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=0", function(
						data) {
					$.updateDate(data, 0);
				});
				$.hideModal();
			});

	$("#btn-list-term").click(
			function() {
				$.showModal();
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=1", function(
						data) {
					$.updateDate(data, 1);
				});
			});
	$("#btn-list-all").click(
			function() {
				$.showModal();
				$.getJSON("historystatistic?userNumber="
						+ $("#userNumber").val() + "&historyType=2", function(
						data) {
					$.updateDate(data, 2);
				});
			});
	$(document).on("scrollstart", function() {
		if (!inited && $("#amountCharts").hasClass('active')) {
			inited = true;
			$("#areaCharts").hide();
			$("#shopCharts").hide();
		}
	});

	$("#li-area").click(function() {
		$("#areaCharts").show();
		$("#amountCharts").hide();
		$("#shopCharts").hide();
	});
	$("#li-amount").click(function() {
		$("#amountCharts").show();
		$("#areaCharts").hide();
		$("#shopCharts").hide();
	});
	$("#li-shop").click(function() {
		$("#shopCharts").show();
		$("#amountCharts").hide();
		$("#areaCharts").hide();
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
		},
		title : {
			text : '消费金额'
		},
		subtitle : {
			text : '本周'
		},
		plotOptions : {
			column : {
				depth : 25
			}
		},
		xAxis : {
			categories : [ "周一", "周二", "周三", "周四", "周五", "周六", "周日" ]
		},
		yAxis : {
			title : {
				text : '金额 /元'
			},
			labels : {
				formatter : function() {
					return this.value;
				}
			},
			opposite : false
		},
		legend : {
			enabled : true
		},
		series : [ {
			name : '消费金额',
			data : [ 0, 0, 0, 0 ]
		}, {
			name : '充值金额',
			data : [ 0, 0, 0, 0 ]
		} ],
		tooltip : {
			shared : true,
		},
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
		},
		title : {
			text : '消费次数'
		},
		subtitle : {
			text : '本周'
		},
		plotOptions : {
			column : {
				depth : 25
			}
		},
		xAxis : {
			categories : [ "周一", "周二", "周三", "周四", "周五", "周六", "周日" ]
		},
		yAxis : {
			title : {
				text : '次数 /次'
			},
			labels : {
				formatter : function() {
					return this.value;
				}
			},
			opposite : false

		},
		legend : {
			enabled : true
		},
		series : [ {
			name : '消费次数',
			data : [ 0, 0, 0, 0 ]
		}, {
			name : '充值次数',
			data : [ 0, 0, 0, 0 ]
		} ],
		tooltip : {
			shared : true,
		},
	});

	var chart_areadata = new Highcharts.Chart(
			{
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
					pointFormat : '{series.name}: <b>{point.y}</b><br>消费占比: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							color : '#000000',
							connectorColor : '#000000',
							format : '{point.percentage:.1f} %'
						},
						showInLegend : true
					}
				},
				series : [ {
					type : 'pie',
					name : '消费金额',
					data : [ [ '东院', 0 ], [ '中院', 0 ], [ '西院', 0 ] ]
				} ]
			});

	var chart_areatime = new Highcharts.Chart(
			{
				chart : {
					renderTo : 'container_areatime',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				colors : [ '#7CB5EC', '#90ED7D', '#F7A35C', ],
				credits : {
					enabled : false
				},
				title : {
					text : '消费次数'
				},
				tooltip : {
					pointFormat : '{series.name}: <b>{point.y}</b><br>消费占比: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions : {
					pie : {

						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							color : '#000000',
							connectorColor : '#000000',
							format : '{point.percentage:.1f} %'
						},
						showInLegend : true
					}
				},
				series : [ {
					type : 'pie',
					name : '消费次数 ',
					data : [ [ '东院', 0 ], [ '中院', 0 ], [ '西院', 0 ] ]
				} ]
			});

	var chart_shopdata = new Highcharts.Chart(
			{
				chart : {
					renderTo : 'container_shopdata',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				credits : {
					enabled : false
				},
				title : {
					text : '消费金額'
				},
				tooltip : {
					pointFormat : '{series.name}: <b>{point.y}</b><br>消费占比: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							color : '#000000',
							connectorColor : '#000000',
							format : '{point.percentage:.1f} %'
						}
					}
				},
				series : [ {
					type : 'pie',
					name : '消费金额'
				} ]
			});
	var chart_shoptime = new Highcharts.Chart(
			{
				chart : {
					renderTo : 'container_shoptime',
					plotBackgroundColor : null,
					plotBorderWidth : null,
					plotShadow : false
				},
				credits : {
					enabled : false
				},
				title : {
					text : '消费次数'
				},
				tooltip : {
					pointFormat : '{series.name}: <b>{point.y}</b><br>次数占比: <b>{point.percentage:.1f}%</b>'
				},
				plotOptions : {
					pie : {
						allowPointSelect : true,
						cursor : 'pointer',
						dataLabels : {
							enabled : true,
							color : '#000000',
							connectorColor : '#000000',
							format : '{point.percentage:.1f} %'
						}
					}
				},
				series : [ {
					type : 'pie',
					name : '消费次数'
				} ]
			});

	$.showModal = function() {
		$("#post-modal").show();
		$("#progressing").show();
		$("#btn-postconfrim").hide();
	};

	$.hideModal = function() {
		$("#post-modal").hide();
		$("#progressing").hide();
		$("#btn-postconfrim").show();
	};

	$.updateDate = function(data, historyType) {
		switch (historyType) {
		case 0:
			chart_amountdata.setTitle(null, title_week);
			chart_amounttime.setTitle(null, title_week);
			chart_areadata.setTitle(null, title_week);
			chart_areatime.setTitle(null, title_week);
			chart_shopdata.setTitle(null, title_week);
			chart_shoptime.setTitle(null, title_week);
			chart_amountdata.xAxis[0].setCategories(categories_week);
			chart_amounttime.xAxis[0].setCategories(categories_week);
			break;
		case 1:
			chart_amountdata.setTitle(null, title_term);
			chart_amounttime.setTitle(null, title_term);
			chart_areadata.setTitle(null, title_term);
			chart_areatime.setTitle(null, title_term);
			chart_shopdata.setTitle(null, title_term);
			chart_shoptime.setTitle(null, title_term);
			var date = new Date;
			var month = date.getMonth() + 1;
			if (month > 2 && month < 9) {
				chart_amountdata.xAxis[0].setCategories(categories_term_spring);
				chart_amounttime.xAxis[0].setCategories(categories_term_spring);
			} else {
				chart_amountdata.xAxis[0].setCategories(categories_term_autumn);
				chart_amounttime.xAxis[0].setCategories(categories_term_autumn);
			}
			break;
		case 2:
			chart_amountdata.setTitle(null, title_all);
			chart_amounttime.setTitle(null, title_all);
			chart_areadata.setTitle(null, title_all);
			chart_areatime.setTitle(null, title_all);

			chart_amountdata.xAxis[0].setCategories(data.categories);
			chart_amounttime.xAxis[0].setCategories(data.categories);
			break;
		default:
			break;
		}
		chart_amountdata.series[0].setData(data.consumeAmounts);
		chart_amountdata.series[1].setData(data.rechargeAmounts);
		chart_amounttime.series[0].setData(data.consumeTimes);
		chart_amounttime.series[1].setData(data.rechargeTimes);

		// 设置区域数据
		var areaData = [];
		var areaTime = [];
		var shopDatas = [];
		var shopTimes = [];
		areaData.push([ '东院', data.areaConsumeAmounts[0] ]);
		areaData.push([ '中院', data.areaConsumeAmounts[1] ]);
		areaData.push([ '西院', data.areaConsumeAmounts[2] ]);
		areaTime.push([ '东院', data.areaConsumeTimes[0] ]);
		areaTime.push([ '中院', data.areaConsumeTimes[1] ]);
		areaTime.push([ '西院', data.areaConsumeTimes[2] ]);
		chart_areadata.series[0].setData(areaData);
		chart_areatime.series[0].setData(areaTime);

		$('#td_consumeamount').text(data.consumeAmount + "元");
		$('#td_consumetime').text(data.consumeTime + "次");
		$('#td_rechargeamount').text(data.rechargeAmount + "元");
		$('#td_rechargetime').text(data.rechargeTime + "次");
		$('#td_averageconsumes').text(
				(data.consumeAmount / data.consumeTime).toFixed(1) + "元");
		$('#td_averagerecharges').text(
				(data.rechargeAmount / data.rechargeTime).toFixed(1) + "元");
		$('#td_averageconsume').text(data.averageConsume + "元");
		$('#td_eastamount').text(data.areaConsumeAmounts[0] + "元");
		$('#td_easttime').text(data.areaConsumeTimes[0] + "次");
		$('#td_centreamount').text(data.areaConsumeAmounts[1] + "元");
		$('#td_centretime').text(data.areaConsumeTimes[1] + "次");
		$('#td_westamount').text(data.areaConsumeAmounts[2] + "元");
		$('#td_westtime').text(data.areaConsumeTimes[2] + "次");
		$('#td_avg_eastamount').text(data.averageAreaConsume[0] + "元");
		$('#td_avg_easttime').text(data.averageAreaConsumeTimes[0] + "次");
		$('#td_avg_centreamount').text(data.averageAreaConsume[1] + "元");
		$('#td_avg_centretime').text(data.averageAreaConsumeTimes[1] + "次");
		$('#td_avg_westamount').text(data.averageAreaConsume[2] + "元");
		$('#td_avg_westtime').text(data.averageAreaConsumeTimes[2] + "次");

		$('#td_avg_eastamounts').text(
				(data.areaConsumeAmounts[0] / data.areaConsumeTimes[0])
						.toFixed(1)
						+ "元");
		$('#td_avg_centreamounts').text(
				(data.areaConsumeAmounts[1] / data.areaConsumeTimes[1])
						.toFixed(1)
						+ "元");
		$('#td_avg_westamounts').text(
				(data.areaConsumeAmounts[2] / data.areaConsumeTimes[2])
						.toFixed(1)
						+ "元");
		$.hideModal();
		$('#table-east tbody tr').empty();
		$('#table-center tbody tr').empty();
		$('#table-west tbody tr').empty();
		$.updateTable(data.eastShopMap, 0, shopDatas, shopTimes);
		$.updateTable(data.centerShopMap, 1, shopDatas, shopTimes);
		$.updateTable(data.westShopMap, 2, shopDatas, shopTimes);

		chart_shopdata.series[0].setData(shopDatas);
		chart_shoptime.series[0].setData(shopTimes);
	};

	$.updateTable = function(list, area, shopData, shopTime) {
		var tr, amounts, times;
		var table;
		switch (area) {
		case 0:
			table = $('#table-east');
			break;
		case 1:
			table = $('#table-center');
			break;
		case 2:
			table = $('#table-west');
			break;
		default:
			break;
		}
		for ( var k in list) {
			amounts = Number((-list[k].amounts).toFixed(1));
			times = list[k].times;
			tr = "<tr><td>" + k + "</td><td>" + amounts + "</td><td>" + times
					+ "</td><td>" + (amounts / times).toFixed(1) + "</td></tr>";

			table.append(tr);
			shopData.push([ k, amounts ]);
			shopTime.push([ k, times ]);
		}
	};

	$.getJSON("historystatistic?userNumber=" + $("#userNumber").val()
			+ "&historyType=0", function(data) {
		$.updateDate(data, 0);
	});
});
