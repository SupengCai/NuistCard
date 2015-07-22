$(function() {
	var pageIndex=1;
	
	$("#btn-postconfrim").click(function() {
		$("#post-modal").hide();
		$("#msg").html("正在提交请求，请稍后...");
		$("#btn-postconfrim").attr("disabled", true);
	});
	
//	$.post("score", {
//		pageIndex : pageIndex,
//	}, function(data) {
//		var json = JSON.parse(data);
//		if (json.success) {
//			$("#post-modal").hide();
//			$.addScoreTr(json.obj);
//			$("#msg").html("正在提交请求，请稍后...");
//			$("#btn-postconfrim").attr("disabled", true);
//			pageIndex++;
//		} else {
//			$("#btn-postconfrim").removeAttr("disabled");
//			$("#msg").html(json.msg);
//		}
//		$("#progressing").hide();
//	});
	
	$("#btn-score").click(function() {
		$(this).attr('disabled',"true");
		$("#post-modal").show();
		$("#progressing").show();
		$.post("score", {
			pageIndex : pageIndex,
		}, function(data) {
			var json = JSON.parse(data);
			if (json.success) {
				$("#post-modal").hide();
				$.addScoreTr(json.obj);
				$("#msg").html("正在提交请求，请稍后...");
				$("#btn-postconfrim").attr("disabled", true);
				pageIndex++;
			} else {
				$("#btn-postconfrim").removeAttr("disabled");
				$("#msg").html(json.msg);
			}
			$("#progressing").hide();
		});
		$(this).removeAttr("disabled");
	});
	
	$.addScoreTr = function(array) {
		var inner="";
		var innerTS="";
		var innerTF="";
		var scores=0;
		var point=0;
		var failedNum=0;
		var year=array[0].XN+"学年    第"+array[0].XQ+"学期";
//		if(pageIndex!=1)
//			inner+="<div  class=\"table-responsive\"><div class=\"progress\"><div class=\"progress-bar progress-bar-info\" role=\"progressbar\" aria-valuenow=\"100\" aria-valuemin=\"0\" aria-valuemax=\"100\" style=\"width: 100%\"><span class=\"sr-only\">20% Complete</span></div></div>";
		inner+="<h4>"+year+"</h4><table class=\"table table-bordered table-hover\"><tbody><tr><th><h4>课程</h4></th><th><h4>成绩</h4></th><th><h4>学分</h4></th><th><h4>绩点</h4></th></tr>";
		
		$.each(array,function(n,elem) {
			var sp=$.getScorePoint(elem.CJ);
			inner+="<tr><td>"+elem.KCMC+"</h4></td><td>"+elem.CJ+"</h4></td><td>"+Number(elem.XF)+"</h4></td><td>"+sp+"</td></tr>";
			scores+=Number(elem.XF);
			point+=Number(sp);
			if(elem.CJ<60&&elem.CJ)
				failedNum++;
		});
		inner+="</tbody></table></div>";
		$(inner).appendTo($("#div-score"));
		
		//成绩统计
		innerTS="<tr><td>"+year+"</td><td>"+scores+"</td><td>"+point.toFixed(1)+"</td></tr>";
		//$(innerTS).appendTo($("#table-score"));
		$("#table-score tr:first").after(innerTS);
		
		var t_score=0;
		var t_point=0;
		var t_failed=0;
		
		var trList = $('#table-score tr');
	    for (var i=0;i<trList.length-1;i++) {
	        var tdArr = trList.eq(i).find("td");
	        t_score+= Number(tdArr.eq(1).text());
	        t_point+= Number(tdArr.eq(2).text());
	    }
		
		var tdList=$("#table-score tr:last td");
		tdList.eq(1).text(t_score);
		tdList.eq(2).text(t_point.toFixed(1));
		
		// 挂科统计
		if(failedNum>0){
			innerTF+="<tr><td>"+year+"</td><td>"+failedNum+"</td></tr>";
			$("#table-failed tr:first").after(innerTF);
			//$(innerTF).appendTo($("#table-failed"));
		}
		
		trList=$("#table-failed tr");
		 for (var i = 0; i < trList.length - 1; i++) {
			var tdArr = trList.eq(i).find("td");
			t_failed += Number(tdArr.eq(1).text());
		}
		 
		 $("#table-failed tr:last td:last").text(t_failed);
	};
	

		$.getScorePoint = function(score) {
		var result = 0;
		if (score >= 0 && score <= 100) {
			if (score == 100) {
				result = 5;
			} else if (score >= 96.0 && score <= 99.9) {
				result = 4.8;
			} else if (score >= 93.0 && score <= 95.9) {
				result = 4.5;
			} else if (score >= 90.0 && score <= 92.9) {
				result = 4;
			} else if (score >= 86.0 && score <= 89.9) {
				result = 3.8;
			} else if (score >= 83.0 && score <= 85.9) {
				result = 3.5;
			} else if (score >= 80.0 && score <= 82.9) {
				result = 3;
			} else if (score >= 76.0 && score <= 79.9) {
				result = 2.8;
			} else if (score >= 73.0 && score <= 75.9) {
				result = 2.5;
			} else if (score >= 70.0 && score <= 72.9) {
				result = 2;
			} else if (score >= 66.0 && score <= 69.9) {
				result = 1.8;
			} else if (score >= 63.0 && score <= 65.9) {
				result = 1.5;
			} else if (score >= 60.0 && score <= 62.9) {
				result = 1;
			}
		} else if (score == "优秀") {
			result = 4;
		} else if (score == "良好") {
			result = 3.5;
		} else if (score == "中等") {
			result = 2.5;
		} else if (score == "及格") {
			result = 1.5;
		}
		return result;
	}
})