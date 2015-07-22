$(function(){
	$("#form-login").hide();
    $("#btn-login").click(function(){
    	$("#form-new").hide();
    	$("#form-login").show();
    });
    
	$("#btn-new").click(function() {
		$("#form-login").hide();
    	$("#form-new").show();
	});
	
	$('#test').bind('input propertychange', function() {
		$('#btn-test').click();

    });
});