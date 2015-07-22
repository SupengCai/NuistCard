$(function() {

	$("#join").click(
			function() {
				$.getJSON("statistictype?userNumber=" + $("#userNumber").val()
						+ "&statisticType=1", function(data) {
					$.showModal("分享成功，您的参与让我们的统计更准确");
				});
			});

	$("#disjoin").click(
			function() {
				$.getJSON("statistictype?userNumber=" + $("#userNumber").val()
						+ "&statisticType=3", function(data) {
					$.showModal("取消成功，我们将不再统计您的数据");
				});
			});

	$("#btn-postconfrim").click(function() {
		$("#post-modal").hide();
		$("#msg").html("正在提交请求，请稍后...");
		$("#btn-postconfrim").attr("disabled", true);
		window.location.replace("ranklist");
	});

	$.showModal = function(str) {
		$("#post-modal").show();
		$("#btn-postconfrim").removeAttr("disabled");
		$("#msg").html(str);
	}
});
