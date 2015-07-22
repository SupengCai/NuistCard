$(function() {

	$.replaceSpace = function(str) 
	{ 
		return str.replace(/\s+/g, ""); 
	}
	
//	//搜索地址提示
//	$.placesuggest = function(json) 
//	{ 
//		//return str.replace(/\s+/g, ""); 
//	}
	
	Date.prototype.format = function(fmt)   
	{ 
	  var o = {   
	    "M+" : this.getMonth()+1,                 //月份   
	    "d+" : this.getDate(),                    //日   
	    "h+" : this.getHours(),                   //小时   
	    "m+" : this.getMinutes(),                 //分   
	    "s+" : this.getSeconds(),                 //秒   
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度   
	    "S"  : this.getMilliseconds()             //毫秒   
	  };   
	  if(/(y+)/.test(fmt))   
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length));   
	  for(var k in o)   
	    if(new RegExp("("+ k +")").test(fmt))   
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length)));   
	  return fmt;   
	}  
	
	//搜索地址提示
	$.getTrackMarkers = function(email,mintime,maxtime) 
	{ 
		var url="http://api.map.baidu.com/geodata/v3/poi/list?geotable_id=75311&ak=BV4fuXdUHi3FQLqY44XBNaZG&page_size=30&time="+mintime+","+maxtime;
		if(email)
			url=url+"&email="+email;
		$.ajax({
			type : "get",
			async : false,
			url : url,
			dataType : "jsonp",
			success : function(json) {
				$.removeAllMarkers();
				//有数据，添加markers，展示信息
				if(json.size!=0){
					if(email)
						$.addTrackMarks(json.pois,email,true);
					else
						$.addTrackMarks(json.pois,email,false);
				}
			},
			error : function() {
				//alert('fail');
			}
//			,error: function(XMLHttpRequest, textStatus, errorThrown) {
//                alert(XMLHttpRequest.status);
//                alert(XMLHttpRequest.readyState);
//                alert(textStatus);
//            },
		});
	}
	//获取时间  day=0 昨天 ;day=-1 今天 ; day=6 七天前
	$.getDate = function(day)
	{
	    var zdate=new Date();
	    var sdate=zdate.getTime()-(1*24*60*60*1000);
	    var edate=new Date(sdate-(day*24*60*60*1000)).format("yyyyMMdd");
	    return edate;
	 
	}
 });
