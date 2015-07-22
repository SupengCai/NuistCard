$(function() {
	$("#btn-postconfrim").click(function() {
		$("#post-modal").hide();
		$("#msg").html("正在提交请求，请稍后...");
		$("#btn-postconfrim").attr("disabled", true);
	});

	$("#CardLostForm").validate({
		onsubmit : true,
		onkeyup : false,
		onfocusout : false,
		onclick : false,
		rules : {
			pwdLost : {
				required : true,
				number : true,
				rangelength : [ 6, 6 ]
			}
		},
		messages : {
			pwdLost : {
				required : "请输入查询密码！",
				number : "请输入6位数字查询密码！",
				rangelength : "请输入6位数字查询密码！"
			}
		},
		errorPlacement : function(error, element) {
			if ($("#btn-postconfrim").attr("disabled") == "disabled") {
				$("#post-modal").show();
				$("#btn-postconfrim").removeAttr("disabled");
				$("#msg").html(error.text());
				$("#pwdLost").val("");
			}
		}
	});

	$("#CardUnlostForm").validate({
		onsubmit : true,
		onkeyup : false,
		onfocusout : false,
		onclick : false,
		rules : {
			CardNo : {
				required : true,
				number : true,
				rangelength : [ 11, 11 ]
			},
			pwdUnlost : {
				required : true,
				number : true,
				rangelength : [ 6, 6 ]
			}
		},
		messages : {
			CardNo : {
				required : "请输入学号！",
				number : "学号为纯数字！",
				rangelength : "学号长度有误！",
			},
			pwdUnlost : {
				required : "请输入查询密码！",
				number : "请输入6位数字查询密码！",
				rangelength : "请输入6位数字查询密码！"
			}
		},
		errorPlacement : function(error, element) {
			if ($("#btn-postconfrim").attr("disabled") == "disabled") {
				$("#post-modal").show();
				$("#btn-postconfrim").removeAttr("disabled");
				$("#msg").html(error.text());
				$("#pwdUnlost").val("");
			}
		}
	});

	$("#btnCardLost").click(function() {
		if ($("#CardLostForm").valid()) {
			$("#post-modal").show();
			$("#progressing").show();
			var Password = Base64.encode($("#pwdLost").val());

			$.post("cardlost", {
				Password : Password,
			}, function(data) {
				var json = JSON.parse(data);
				if (json.success) {
					$("#btn-postconfrim").removeAttr("disabled");
					$("#msg").html(json.msg);
					$("#CardNo").val("");
					$("#pwdLost").val("");
				} else {
					$("#btn-postconfrim").removeAttr("disabled");
					$("#msg").html(json.msg);
					$("#pwdLost").val("");
				}
				$("#progressing").hide();
			});
		}
	});

	$("#btnCardUnlost").click(function() {
		if ($("#CardUnlostForm").valid()) {
			$("#post-modal").show();
			$("#progressing").show();
			var CardNo = $("#CardNo").val();
			var Password = Base64.encode($("#pwdUnlost").val());

			$.post("cardunlost", {
				CardNo : CardNo,
				Password : Password,
			}, function(data) {
				if (data == 200) {
					$("#btn-postconfrim").removeAttr("disabled");
					$("#msg").html("解除挂失成功！");
					$("#pwdUnlost").val("");
				} else if (data == 408) {
					$("#btn-postconfrim").removeAttr("disabled");
					$("#msg").html("服务器链接超时，请等待校方恢复服务")
				} else {
					$("#btn-postconfrim").removeAttr("disabled");
					$("#msg").html("解除失败，请输入正确的信息！");
					$("#pwdUnlost").val("");
				}
				$("#progressing").hide();
			});
		}
	});
})
