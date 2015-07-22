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
				email : true,</script>
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
			accountName : {
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
			accountName : {
				required : "昵称还没有输入哦",
				maxlength : "昵称有点儿长"
			},
			accountPsw : {
				required : "密码还没有输入哦",
				rangelength : "请将密码设置在6-20位之间"
			},
			accountPsw_confirm : {
				equalTo : "两次输入密码不一致"
			}
		},
		errorPlacement : function(error) {
			alert(error.text());
		}
	});
})