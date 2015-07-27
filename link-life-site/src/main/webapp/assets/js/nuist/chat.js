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
		var length=content.length;
		if(length>150){
			alert("请将消息内容控制在150字以内");
		}else if(length>0){
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
		var length=content.length;
		if(length>140){
			alert("请将回复内容控制在150字以内");
		}else if($.trim(array[1])!=""){

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