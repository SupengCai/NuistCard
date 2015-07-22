$(function(){
	$("#div-track").hide();
	var time = new Date().format("yyyyMMdd");
	var mintime=$.getDate(6)+"000000";
	var maxtime=$.getDate(-1)+"246060";
	$.getTrackMarkers(null,mintime,maxtime);
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
    
    $("#btn-back").click(function() {
		$("#life-map").show();
		$("#toolbar").show();
		$("#div-track").hide();
	});
    
/*    $('#keyword').bind('input', function() {
    	//去除空格
    	var keyword=$("#keyword").val();
    	keyword=$.replaceSpace(keyword);
    	$("#keyword").val(keyword);
    	//若无输入，函数返回
    	if(keyword ==""){
			$("#keyword").val("");
			$('#suggestionlist').hide();
			return; 
		}
    	//向服务器发送输入，获取并处理提示
    	$("li[name='li']").remove();
    	$.ajax({
			type : "get",
			async : false,
			url : "placeSuggestion?&output=json&region="+$("#city").val()+"&query="+keyword,
			dataType : "json",
			success : function(jsonArray) {
				$.each(jsonArray, function(index, json) {
					var msg = "<li name='li'><a name='suggestplace'>"+json.name+"</a></li>";
					$('#suggestionlist').append(msg);
					if(index==5)
						return false; 
				});
				$("li").click(function(){
					$("#keyword").val($(this).children().html());
					$('#suggestionlist').hide();
				});
				if($('#suggestionlist').find("li[name='li']").length==0)
					$('#suggestionlist').hide();
				else
					$('#suggestionlist').show();
			},
			error : function() {
				//alert('fail');
			}
		});
    });*/
    
  
});
