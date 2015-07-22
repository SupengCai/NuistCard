var $ = jQuery.noConflict();
$(function() {
	$("#form-suggest").validate( {
		onsubmit : true,
		onkeyup : false,
		onfocusout : false,
		onclick : false,
		rules : {
			suggest : {
				required : true,
				rangelength : [ 1, 50 ]
			}
		},
		messages : {
			suggest : {
				required: "说点什么吧",
				rangelength : "请输入50字以内信息",
			}
		},
		errorPlacement : function(error) {
			alert(error.text());
		}
	});
})