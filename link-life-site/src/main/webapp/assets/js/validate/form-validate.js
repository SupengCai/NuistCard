var $ = jQuery.noConflict();
$(function() {
	$("#form-new").validate( {
		onsubmit : true,
		onkeyup : false,
		onfocusout : false,
		onclick : false,
		rules : {
			email : {
				required : true,
				email : true,
				rangelength : [ 5, 30 ],
				remote : {
					type : "post",
					dataType : "json",
					url : "emailvalid",
					data : {
						email : function() {
							return $('#email').val();
						}
					}
				}
			},
			name : {
				required : true,
				maxlength : 20
			},
			accountPsw : {
				required : true,
				rangelength : [ 6, 20 ]
			},
			accountPsw_confirm : {
				equalTo : "#accountPsw"
			}
		},
		messages : {
			email : {
				required : "邮箱还没有输入哦",
				email : "邮箱格式不对哦",
				rangelength : "邮箱长度不正确",
				remote : "该邮箱不能重复注册"
			},
			name : {
				required : "昵称还没有输入哦",
				maxlength : "昵称有点儿长"
			},
			accountPsw : {
				required : "密码还没有输入哦",
				rangelength : "请将密码设置在6-20位之间"
			},
			accountPsw_confirm : {
				required : "确认密码还没有输入哦",
				equalTo : "两次输入密码不一致"
			}
		},
		errorPlacement : function(error) {
			alert(error.text());
		}
	});
	
	$("#form-login").validate( {
		onsubmit : true,
		onkeyup : false,
		onfocusout : false,
		onclick : false,
		rules : {
			j_username : {
				required : true,
				email : true,
				rangelength : [ 5, 30 ]
			},
			j_password : {
				required : true,
				rangelength : [ 6, 20 ]
			}
		},
		messages : {
			j_username : {
				required : "邮箱还没有输入哦",
				email : "邮箱格式不对哦",
				rangelength : "邮箱长度不正确",
			},
			j_password : {
				required : "密码还没有输入哦",
				rangelength : "密码长度不正确"
			}
		},
		errorPlacement : function(error) {
			alert(error.text());
		}
	});
})