var $ = jQuery.noConflict();
$(function() {
	$("#form-track").validate( {
		onsubmit : true,
		onkeyup : false,
		onfocusout : false,
		onclick : false,
		rules : {
			doWhat : {
				rangelength : [ 0, 15 ]
			},
			sayWhat : {
				rangelength : [ 0, 20 ]
			}
		},
		messages : {
			doWhat : {
				rangelength : "请输入15字以内信息",
			},
			sayWhat : {
				rangelength : "请输入20字以内信息"
			}
		},
		errorPlacement : function(error) {
			alert(error.text());
		}
	});
})