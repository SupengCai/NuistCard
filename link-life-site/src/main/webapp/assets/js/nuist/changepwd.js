$(function() {
	$("#btn-postconfrim").click(function() {
		$("#post-modal").hide();
		$("#msg").html("正在提交请求，请稍后...");
		$("#btn-postconfrim").attr("disabled", true);
	});

	$("#ChangePwdForm").validate({
		onsubmit : true,
		onkeyup : false,
		onfocusout : false,
		onclick : false,
		rules : {
			Password : {
				required : true,
				number : true,
				rangelength : [ 6, 6 ]
			},
			NewPassword : {
				required : true,
				number : true,
				rangelength : [ 6, 6 ]
			},
			ConfirmPassword : {
				required : true,
				equalTo : "#NewPassword"
			}
		},
		messages : {
			Password : {
				required : "请输入查询密码！",
				number : "请输入6位数字查询密码！",
				rangelength : "请输入6位数字查询密码！"
			},
			NewPassword : {
				required : "请输入新密码！",
				number : "请输入6位纯数字新密码！",
				rangelength : "请输入6位纯数字新密码！"
			},
			ConfirmPassword : {
				required : "请输入新密码确认！",
				equalTo : "两次输入密码不一致"
			}
		},
		errorPlacement : function(error, element) {
			if ($("#btn-postconfrim").attr("disabled") == "disabled") {
				$("#post-modal").show();
				$("#btn-postconfrim").removeAttr("disabled");
				$("#msg").html(error.text());
				if (element.is("#Password"))
					$("#Password").val("");
				else if (element.is("#NewPassword"))
					$("#NewPassword").val("");
				else if (element.is("#ConfirmPassword"))
					$("#ConfirmPassword").val("");
			}
		}
	});

	$("#btnChangePwd").click(function() {
		if ($("#ChangePwdForm").valid()) {
			$("#post-modal").show();
			$("#progressing").show();
			var Password = Base64.encode($("#Password").val());
			var NewPassword = Base64.encode($("#NewPassword").val());
			var ConfirmPassword = Base64.encode($("#ConfirmPassword").val());

			$.post("changepwd", {
				Password : Password,
				NewPassword : NewPassword,
				ConfirmPassword : ConfirmPassword,
			}, function(data) {
				var json = JSON.parse(data);
				if (json.success) {
					$("#btn-postconfrim").removeAttr("disabled");
					$("#msg").html(json.msg);
					$("#Password").val("");
					$("#NewPassword").val("");
					$("#ConfirmPassword").val("");
				} else {
					$("#btn-postconfrim").removeAttr("disabled");
					$("#msg").html(json.msg);
					$("#Password").val("");
					$("#NewPassword").val("");
					$("#ConfirmPassword").val("");
				}
				$("#progressing").hide();
			});
		}
	});
})
