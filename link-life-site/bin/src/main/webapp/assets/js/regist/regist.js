$(function(){
	$("#form-new").hide();
    $("#btn-login").click(function(){
    	$("#form-new").hide();
    	$("#form-login").show();
    });
    
	$("#btn-new").click(function() {
		$("#form-login").hide();
    	$("#form-new").show();
	});
});