$(function() {
	
	$("div .panel-body ul a").click(function() {
		var replyUser="回复 ";
		replyUser=replyUser+$.trim($(this).parent().prev().prev().text())+":";
		$("#chatmsg").val("");
		//alert($(this).parent().prev(":h5").html());
		$("#chat-modal").show();
		$("#chatmsg").val(replyUser);
//		$("#btn-postconfrim").attr("disabled", true);
	});

	$("#btn-chat").click(function() {
		alert("send successed!");
	});
	
	$(".close").click(function() {
		$("#chat-modal").hide();
	});
	
	$(".closemodal").click(function() {
		$("#chat-modal").hide();
	});
})