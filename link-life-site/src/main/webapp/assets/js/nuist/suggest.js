$(function() {

	$("#btn-suggest").click(function() {
		$.post("suggest", {
			suggest : $("#suggest").val(),
		}, function(data) {
			$("#progressing").hide();
			$("#post-modal").show();
			$("#msg").html("提交成功，感谢您对我们的支持");
			$("#btn-postconfrim").removeAttr("disabled");
		});

	});

	$("#btn-postconfrim").click(function() {
		$("#post-modal").hide();
		$("#msg").html("正在提交请求，请稍后...");
		$("#btn-postconfrim").attr("disabled", true);
		window.location.replace("index");
	});

})