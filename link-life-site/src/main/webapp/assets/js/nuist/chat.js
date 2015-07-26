$(function() {

	$("div .panel-body ul a").click(
			function() {
				$("#parentId").attr("value",$(this).parent().parent().parent().parent().attr("id"));
				var replyUser = "回复 ";
				replyUser = replyUser
						+ $.trim($(this).parent().prev().prev().text()) + ": ";
				$("#replymsg").val("");
				$("#chat-modal").show();
				$("#replymsg").val(replyUser);
			});

	$("#btn-chat").click(function() {
		var parentId = 0;
		var content=$("#chatmsg").val();
		if(content.length>0){
		$.post("chat", {
			content : content,
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
			alert("请输入消息的内容");
	});

	$("#btn-replychat").click(function() {
		
		var content=$("#replymsg").val();
		var parentId = $("#parentId").val();
		var array=content.split(': ');
		if($.trim(array[1])!=""){

		$.post("chat", {
			content : content,
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
		$("#chat-modal").hide();
	});

	$(".closemodal").click(function() {
		$("#chat-modal").hide();
	});
})