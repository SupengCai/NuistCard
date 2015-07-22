$(function(){
	var email;
	$("#life-map").hide();
	$("#toolbar-send").hide();
    $("button[name='btn-track']").click(function(){
    	email=$(this).attr('id');
    	email=$.replaceSpace(email);
		if(email ==""){
			return;
		}
		$.getTrackMarkers(email,$("#mintime").val(),$("#maxtime").val());
    	$("#container").hide();
    	$("#toolbar-search").hide();
    	$("#life-map").show();
    	$("#toolbar-send").show();
    });
    
    $("#search").click(function(){
    	var keyword=$("#keyword").val();
    	keyword=$.replaceSpace(keyword);
    	$("#keyword").val(keyword);
		if(keyword ==""){
			$("#keyword").val("");
			return;
		}
    	var data={"keyword":keyword,"city":$("#city").val()};  
    	$.ajax({
    	    type: "post",
    	    contentType: "application/json",
    	    dataType: "json",
    	    url: "search",
    	    data: JSON.stringify(data),
    	    success: function(msg){
    	    	$.removeAllMarkers();
    	    	$.addPlaceMarks(msg);
    		},
    		error:function(XMLResponse){
    			//alert(XMLResponse.responseText)
    		},
    		complete :function(){
    		}
    	});
    });
    
    $("#btn-refresh").click(function(){
		if(email ==""){
			return;
		}
		$.getTrackMarkers(email,$("#mintime").val(),$("#maxtime").val());
    });
});


