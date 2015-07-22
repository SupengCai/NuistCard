$(function(){
    $("#search").click(function(){
    	var keyword=$("#keyword").val();
    	local.search(keyword);
    });
});