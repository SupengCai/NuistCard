$(function() {
	$("#button-login").click();
	
	$("#btn-postconfrim").click(function() {
		$("#post-modal").hide();
		$("#msg").html("正在提交请求，请稍后...");
		$("#btn-postconfrim").attr("disabled", true);
	});

	$("#btn-recharge").click(function() {
		window.location.replace("nuist/recharge");
	});

	$("#btn-history").click(function() {
		window.location.replace("nuist/history");
	});

	$("#btn-cardlost").click(function() {
		window.location.replace("nuist/cardlost");
	});

	$("#btn-info").click(function() {
		window.location.replace("nuist/info");
	});
	
	$("#btn-score").click(function() {
		window.location.replace("nuist/score");
	});
	
	$("#btn-chat").click(function() {
		window.location.replace("nuist/chat");
	});

	$("#btn-changepwd").click(function() {
		window.location.replace("nuist/changepwd");
	});

	$("#btn-signin").click(function() {
		$("#button-login").click();
	});

	$("#btn-signout").click(function() {
		window.location.replace("nuist/index?signout=1");
	});

	$("#btn-sub-signin").click(function() {
		$("#button-login").click();
	});
	
	$("#btn-suggest").click(function() {
		window.location.replace("nuist/suggest");
	});

	$("#btn-remember").click(function() {
		var rem = $("#rememberme");
		if (rem.attr("checked")) {
			rem.removeAttr("checked");
			$(this).text("记住密码");
		} else {
			rem.attr("checked", 'true');
			$(this).text("");
			$(this).html("<span class=\"glyphicon glyphicon-ok huge\" aria-hidden=\"true\"></span>");
			
		}
	});
	
	$("#form-signin").validate({
		onsubmit : true,
		onkeyup : false,
		onfocusout : false,
		onclick : false,
		rules : {
			account : {
				required : true,
				number : true,
				rangelength : [ 11, 11 ]
			},
			password : {
				required : true,
				number : true,
				rangelength : [ 6, 6 ]
			}
		},
		messages : {
			account : {
				required : "请输入学号！",
				number : "学号为纯数字！",
				rangelength : "学号长度有误！",
			},
			password : {
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
				if (element.is("#password"))
					$("#password").val("");
			}
		}
	});

	$("#btn-login")
			.click(
					function() {
						if ($("#form-signin").valid()) {
							$(
									"#btn-recharge,#btn-info,#btn-changepwd,#btn-signin,#btn-sub-signin,#btn-history")
									.unbind();
							$("#post-modal").show();
							$("#progressing").show();
							var account = $("#account").val();
							var password = Base64.encode($("#password").val());

							var rememberme = false;
							var rem = $("#rememberme");
							if (rem.attr("checked"))
								rememberme = true;
							
							$.post("nuist/signin", {
								usernumber : account,
								password : password,
								rememberme : rememberme,
							}, function(data) {
								var json = JSON.parse(data);
								if (json.success) {
									$("#btn-postconfrim")
											.removeAttr("disabled");
									$("#msg").html(json.msg);
									window.location.replace("nuist/index");
								}
								else {
									$("#btn-postconfrim")
											.removeAttr("disabled");
									$("#msg").html(json.msg);
									$("#password").val("");
								}
								$("#progressing").hide();
							});
						}
					});
})