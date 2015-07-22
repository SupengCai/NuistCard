$(function() {
	$("#login_username").popover({
		trigger : 'manual'
	});
	$("#login_password").popover({
		trigger : 'manual'
	});
	$("#regist_account").popover({
		trigger : 'manual'
	});
	$("#regist_password").popover({
		trigger : 'manual'
	});
	$("#regist_confirm_password").popover({
		trigger : 'manual'
	});
	$("#regist_realname").popover({
		trigger : 'manual'
	});
	$("#regist_mobilephone").popover({
		trigger : 'manual'
	});
	$("#regist_homephone").popover({
		trigger : 'manual'
	});
	$("#regist_officephone").popover({
		trigger : 'manual'
	});
	$("#regist_fax").popover({
		trigger : 'manual'
	});
});

$("#loginform").validate({
	onKeyUp : true,
	rules : {
		login_username : {
			required : true,
			minlength : 4,
			maxlength : 20
		},
		login_password : {
			required : true,
			minlength : 4,
			maxlength : 20
		}
	},
	messages : {
		login_username : {
			required : "请输入用户名",
			minlength : "用户名不能小于4个字符",
			maxlength : "用户名不能超过20个字符"

		},
		login_password : {
			required : "请输入密码",
			minlength : "密码不能小于4个字符",
			maxlength : "密码不能超过20个字符"

		}
	},
	errorPlacement : function(error, element) {
		if (element.is("#login_username")) {
			$('#login_username').attr("data-content", error.text());
			$('#login_username').popover('show');
		} else if (element.is("#login_password")) {
			$('#login_password').attr("data-content", error.text());
			$('#login_password').popover('show');
		}
	},
	success : function(label) {
		if (label.attr("for") == "login_username") {// alert("get");
			$('#login_username').popover('hide');
		} else if (label.attr("for") == "login_password") {
			$('#login_password').popover('hide');
		}
	}
});

$("#registform").validate({
	onKeyUp : true,
	rules : {
		regist_account : {
			required : true,
			minlength : 5,
			maxlength : 20,
			remote : {
				type : "post",
				dataType : "json",
				url : "userNameValid",
				data : {
					username : function() {
						return $('#regist_account').val();
					}
				}
			}
		},
		regist_password : {
			required : true,
			minlength : 5,
			maxlength : 20
		},
		regist_confirm_password : {
			required : true,
			minlength : 5,
			maxlength : 20,
			equalTo : "#regist_password"
		},
		regist_realname : {
			required : true,
			minlength : 2,
			maxlength : 20
		},
		regist_mobilephone : {
			required : true,
			rangelength : [ 11, 11 ]
		},
		regist_homephone : {
			required : true,
			minlength : 5,
			maxlength : 20
		},
		regist_officephone : {
			required : true,
			minlength : 5,
			maxlength : 20
		},
		regist_fax : {
			required : true,
			minlength : 5,
			maxlength : 20
		}
	},
	messages : {
		regist_account : {
			required : "请输入用户名",
			minlength : "用户名不能小于5个字符",
			maxlength : "用户名不能超过20个字符",
			remote : "该用户名已存在"

		},
		regist_password : {
			required : "请输入密码",
			minlength : "密码不能小于5个字符",
			maxlength : "密码不能超过20个字符"
		},
		regist_confirm_password : {
			required : "请输入确认密码",
			minlength : "确认密码不能小于5个字符",
			maxlength : "确认密码不能超过20个字符",
			equalTo : "两次输入密码不一致"
		},
		regist_realname : {
			required : "请输入姓名",
			minlength : "姓名不能小于2个字符",
			maxlength : "姓名不能超过20个字符"
		},
		regist_mobilephone : {
			required : "请输入手机号",
			rangelength : "请输入正确的手机号"
		},
		regist_homephone : {
			required : "请输入家庭电话",
			minlength : "家庭电话不能小于5个字符",
			maxlength : "家庭电话不能超过20个字符"
		},
		regist_officephone : {
			required : "请输入办公电话",
			minlength : "办公电话不能小于5个字符",
			maxlength : "办公电话不能超过20个字符"
		},
		regist_fax : {
			required : "请输入传真",
			minlength : "传真不能小于5个字符",
			maxlength : "传真不能超过20个字符"
		}
	},
	errorPlacement : function(error, element) {
		// error.appendTo(element.parent());
		if (element.is("#regist_account")) {
			$('#regist_account').attr("data-content", error.text());
			$('#regist_account').popover('show');
		} else if (element.is("#regist_password")) {
			$('#regist_password').attr("data-content", error.text());
			$('#regist_password').popover('show');
		} else if (element.is("#regist_confirm_password")) {
			$('#regist_confirm_password').attr("data-content", error.text());
			$('#regist_confirm_password').popover('show');
		} else if (element.is("#regist_realname")) {
			$('#regist_realname').attr("data-content", error.text());
			$('#regist_realname').popover('show');
		} else if (element.is("#regist_mobilephone")) {
			$('#regist_mobilephone').attr("data-content", error.text());
			$('#regist_mobilephone').popover('show');
		} else if (element.is("#regist_fax")) {
			$('#regist_fax').attr("data-content", error.text());
			$('#regist_fax').popover('show');
		} else if (element.is("#regist_homephone")) {
			$('#regist_homephone').attr("data-content", error.text());
			$('#regist_homephone').popover('show');
		} else if (element.is("#regist_officephone")) {
			$('#regist_officephone').attr("data-content", error.text());
			$('#regist_officephone').popover('show');
		}
	},
	success : function(label) {
		if (label.attr("for") == "regist_account") {
			$('#regist_account').popover('hide');
		} else if (label.attr("for") == "regist_password") {
			$('#regist_password').popover('hide');
		} else if (label.attr("for") == "regist_confirm_password") {
			$('#regist_confirm_password').popover('hide');
		} else if (label.attr("for") == "regist_realname") {
			$('#regist_realname').popover('hide');
		} else if (label.attr("for") == "regist_mobilephone") {
			$('#regist_mobilephone').popover('hide');
		} else if (label.attr("for") == "regist_homephone") {
			$('#regist_homephone').popover('hide');
		} else if (label.attr("for") == "regist_officephone") {
			$('#regist_officephone').popover('hide');
		} else if (label.attr("for") == "regist_fax") {
			$('#regist_fax').popover('hide');
		}
	}
});