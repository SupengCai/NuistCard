$(function() {

	$("#btn-postconfrim").click(function() {
		$("#post-modal").hide();
		$("#msg").html("正在提交请求，请稍后...");
		$("#btn-postconfrim").attr("disabled", true);
	});

	$("#TransferForm").validate({
		onsubmit : true,
		onkeyup : false,
		onfocusout : false,
		onclick : false,
		rules : {
			txtAmount : {
				required : true,
				number : true,
				rangelength : [ 1, 3 ]
			},
			Password : {
				required : true,
				number : true,
				rangelength : [ 6, 6 ]
			}
		},
		messages : {
			txtAmount : {
				required : "请输入充值金额！",
				number : "请输入正确格式的充值金额！",
				rangelength : "输入金额有误！",
			},
			Password : {
				required : "请输入密码！",
				number : "请输入6位纯数字密码！",
				rangelength : "请输入6位纯数字密码！"
			}
		},
		errorPlacement : function(error, element) {
			if ($("#btn-postconfrim").attr("disabled") == "disabled") {
				$("#post-modal").show();
				$("#btn-postconfrim").removeAttr("disabled");
				$("#msg").html(error.text());
				if (element.is("#txtAmount"))
					$("#txtAmount").val("");
				else if (element.is("#Password"))
					$("#Password").val("");
			}
		}
	});

	$("#btnTransfer").click(function() {
		if ($("#TransferForm").valid()) {
			$("#post-modal").show();
			$("#progressing").show();
			var amount = $("#txtAmount").val();
			var password = Base64.encode($("#Password").val());

			$.post("recharge", {
				amount : amount,
				passWord : password,
			}, function(data) {
				var json = JSON.parse(data);
				if (json.success) {
					$("#btn-postconfrim").removeAttr("disabled");
					$("#msg").html(json.msg);
					$("#txtAmount").val("");
					$("#Password").val("");
				} else {
					$("#btn-postconfrim").removeAttr("disabled");
					$("#msg").html(json.msg);
					$("#Password").val("");
				}
				$("#progressing").hide();
			});
		}
	});
})
