$(function() {

//	$("#btn-suggest").click(function() {
//		$.post("suggest", {
//			suggest : $("#suggest").val(),
//		}, function(data) {
//			$("#progressing").hide();
//			$("#post-modal").show();
//			$("#msg").html("提交成功，感谢您对我们的支持");
//			$("#btn-postconfrim").removeAttr("disabled");
//		});
//
//	});

//	$("#btn-postconfrim").click(function() {
//		$("#post-modal").hide();
//		$("#msg").html("正在提交请求，请稍后...");
//		$("#btn-postconfrim").attr("disabled", true);
//		window.location.replace("index");
//	});

	$("div .panel-body ul a").click(
			function() {
				$("#parentId").attr("value",$(this).parent().parent().parent().parent().attr("id"));
				var replyUser = "回复 ";
				replyUser = replyUser
						+ $.trim($(this).parent().prev().prev().text()) + ": ";
				$("#replymsg").val("");
				$("#suggest-modal").show();
				$("#replymsg").val(replyUser);
			});

	$("#btn-suggest").click(function() {
		var parentId = 0;
		var suggest=$("#suggestmsg").val();
		if(suggest.length>0){
		$.post("suggest", {
			suggest : suggest,
			parentId : parentId,
		}, function(data) {
			if (data) {
				alert("发送成功");
				location.reload();
			} else {
				alert("发送失败，请重新尝试");
			}
		});
		}
		else
			alert("请输入意见的内容");
	});

	$("#btn-replysuggest").click(function() {
		
		var suggest=$("#replymsg").val();
		var parentId = $("#parentId").val();
		var array=suggest.split(': ');
		if($.trim(array[1])!=""){

		$.post("suggest", {
			suggest : suggest,
			parentId : parentId,
		}, function(data) {
			if (data) {
				alert("回复成功");
				location.reload();
			} else {
				alert("回复失败，请重新尝试");
			}
		});
		}
		else
			alert("请输入回复的内容");
	});

	
	$(".close").click(function() {
		$("#suggest-modal").hide();
	});

	$(".closemodal").click(function() {
		$("#suggest-modal").hide();
	});
})