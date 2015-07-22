
	var isAddedMarker1=false;
    var isAddedMarker2=false;

	var isAddedpolyline1=false;
	
	var city='南京市';
	var ak='fByIyk938Uo4I8lOYd79Afc5';

	var map = new BMap.Map("life-map");
	var zoomControl = new BMap.ZoomControl();//缩放控件
    var scaleControl = new BMap.ScaleControl();//比例尺控件

	var markers = new Array();
//    var markerIcon = new BMap.Icon("http://api.map.baidu.com/img/markers.png", new BMap.Size(23, 25), {
//   	 offset: new BMap.Size(10, 25), // 指定定位位置
//   	 imageOffset: new BMap.Size(0, 0 - index * 25) // 设置图片偏移
//   	 });
//	var myIcon = new BMap.Icon("/images/trackicon.png",   
//		      new BMap.Size(14, 23), {      
//		       // 指定定位位置。     
//		       // 当标注显示在地图上时，其所指向的地理位置距离图标左上      
//		       // 角各偏移7像素和25像素。您可以看到在本例中该位置即是     
//		       // 图标中央下端的尖角位置。 
//				offset: new BMap.Size(10, 25), 
//		       //anchor: new BMap.Size(7, 25),        
//		      });  
	 var html = [];
	 html.push('<div class="page-header">');
	 html.push('      <h3><label type="text" maxlength="50" id="place"></label></h3>');
	 html.push('      <h4><label type="text" maxlength="50" id="location"></label></h4>');
    // html.push('      <small><label type="text" maxlength="50" size="25"  id="address"></label></small>');
     html.push('</div>');
     html.push('          <button id="btn-track" class="btn btn-success btn-lg btn-block green" type="button">我在这里</button>');

     var htmlPointInfo = [];
     htmlPointInfo.push('<table border="0" cellpadding="1" cellspacing="1" >');
     htmlPointInfo.push('  <tr>');
     htmlPointInfo.push('      <td colspan="2"><h3><label type="text" maxlength="50" id="place"></label></h3></td>');
     htmlPointInfo.push('  </tr>');
     htmlPointInfo.push('  <tr>');
     htmlPointInfo.push('      <td colspan="2"><label type="text" maxlength="50" size="25"  id="address"></label></td>');
     htmlPointInfo.push('  </tr>');
     htmlPointInfo.push('  <tr>');
     htmlPointInfo.push('	     <td colspan="2">');
     htmlPointInfo.push('          <button id="btn-track" class="btn btn-success btn-lg btn-block" type="button">我在这里</button>');
     htmlPointInfo.push('	     </td>');
     htmlPointInfo.push('  </tr>');
     htmlPointInfo.push('</table>');
     
     var htmlTrack = [];
     htmlTrack.push('<div class="page-header">');
     htmlTrack.push('      <h4><label type="text" maxlength="50" id="place"></label></h4>');
//     htmlTrack.push('      <h5><label type="text" maxlength="50" id="location"></label></h5>');
     htmlTrack.push('      <h5><label type="text" maxlength="50">用户:</label><label type="text" maxlength="50" id="name"></label></h5>');
     htmlTrack.push('      <h5><label type="text" maxlength="50">Ta正在:</label><label type="text" maxlength="50" id="doWhat"></label></h5>');
//     htmlTrack.push('      <h5><label type="text" maxlength="50">Ta的心情:</label><label type="text" maxlength="50" id="sayWhat"></label></h5>');
     htmlTrack.push('      <h5><label type="text" maxlength="50">时间:</label><label type="text" maxlength="50" id="date"></label></h5>');
     htmlTrack.push('</div>');
     htmlTrack.push('          <button id="btn-msg" class="btn btn-success btn-lg btn-block" type="button">给他留言</button>');
     
     
    

     var infoWin = new BMap.InfoWindow(html.join(""), {
         offset: new BMap.Size(0, -10)
     });
     
     var trackWin = new BMap.InfoWindow(htmlTrack.join(""), {
         offset: new BMap.Size(0, -10)
     });
     
     var local = new BMap.LocalSearch(map, {
 		  renderOptions:{map: map}
 		});

	  //LBS条件检索存在问题，js中需再次确认邮箱正确
     $.addTrackMarks = function(jsonArray,email,isone) {
    	 if(isone){
    		 $.each(jsonArray, function(index, json) {
    	     		if(email==json.email){
    	         		var point = $.getCoordinatePointFromNoKey(json.location);
    	         		var marker = new BMap.Marker(point); // 创建标注
    	         		var date = $.getDateFromJson(String(json.time));
    	         		markers.push(marker); // 标注添加到Array
    	         		map.addOverlay(marker); // 图层添加标注
    	         		if(index==0){
    	     				map.setCenter(marker.getPosition());
    	         			map.openInfoWindow(trackWin, marker.getPosition()); // 打开信息窗口
    	         			$("#place").html(json.title);
    	         			$("#name").html(json.name);
    	         			$("#location").html(json.address);
    	         			$("#doWhat").html(json.doWhat);
    	         			$("#sayWhat").html(json.sayWhat);
    	         			$("#date").html(date);
    	     			}
    	         		
    	         		marker.addEventListener("click", function() { // 添加标注点击事件
    	         			map.setCenter(marker.getPosition());
    	         			map.openInfoWindow(trackWin, marker.getPosition()); // 打开信息窗口
    	         			$("#place").html(json.title);
    	         			$("#location").html(json.address);
    	         			$("#name").html(json.name);
    	         			$("#doWhat").html(json.doWhat);
    	         			$("#sayWhat").html(json.sayWhat);
    	         			$("#date").html(date);
    	         		});
    	     		}
    	     	});
    	 }else{
    		 $.each(jsonArray, function(index, json) {
 	         		var point = $.getCoordinatePointFromNoKey(json.location);
 	         		var marker = new BMap.Marker(point); // 创建标注
 	         		var date = $.getDateFromJson(String(json.time));
 	         		markers.push(marker); // 标注添加到Array
 	         		map.addOverlay(marker); // 图层添加标注
 	         		if(index==0){
 	     				map.setCenter(marker.getPosition());
 	         			map.openInfoWindow(trackWin, marker.getPosition()); // 打开信息窗口
 	         			$("#place").html(json.title);
 	         			$("#name").html(json.name);
 	         			$("#location").html(json.address);
 	         			$("#doWhat").html(json.doWhat);
 	         			$("#sayWhat").html(json.sayWhat);
 	         			$("#date").html(date);
 	     			}
 	         		
 	         		marker.addEventListener("click", function() { // 添加标注点击事件
 	         			map.setCenter(marker.getPosition());
 	         			map.openInfoWindow(trackWin, marker.getPosition()); // 打开信息窗口
 	         			$("#place").html(json.title);
 	         			$("#location").html(json.address);
 	         			$("#name").html(json.name);
 	         			$("#doWhat").html(json.doWhat);
 	         			$("#sayWhat").html(json.sayWhat);
 	         			$("#date").html(date);
 	         		});
 	     	});
    	 }
     	
     }

     $.addPlaceMarks = function(jsonArray) {
 		$.each(jsonArray, function(index, json) {
 			var point = $.getCoordinatePoint(json.location);
 			var marker = new BMap.Marker(point); // 创建标注
 			markers.push(marker); // 标注添加到Array
 			map.addOverlay(marker); // 图层添加标注
 			if(index==0) {
 				map.setZoom(17);
 				map.openInfoWindow(infoWin, marker.getPosition());
 				$("#place").html(json.name);
 				$("#location").html(json.address);
 				 $("#btn-track").click(function() {
 					$("#title").attr("value",json.name);
 					$("#address").attr("value",json.address);
 					$("#longitude").attr("value",json.location.lng); 
 					$("#latitude").attr("value",json.location.lat); 
 					$("#life-map").hide();
 					$("#toolbar").hide();
 					$("#div-track").show();
 				});
 			}

 			marker.addEventListener("click", function() { // 添加标注点击事件
 				map.setCenter(marker.getPosition());
 				map.openInfoWindow(infoWin, marker.getPosition()); // 打开信息窗口
 				$("#place").html(json.name);
 				$("#location").html(json.address);
 				$("#btn-track").click(function() {
 					$("#title").attr("value",json.name);
 					$("#address").attr("value",json.address);
 					$("#longitude").attr("value",json.location.lng); 
 					$("#latitude").attr("value",json.location.lat); 
 					$("#life-map").hide();
 					$("#toolbar").hide();
 					$("#div-track").show();
 				});
 			});
 		});
 	}
     
     $.getCoordinatePoint = function(json) {
     	var point = new BMap.Point(json.lng, json.lat);
     	return point;
     }

     $.getCoordinatePointFromNoKey = function(location) {
    	var coordinate= new Array();
    	coordinate = String(location).split(",");
      	var point = new BMap.Point(coordinate[0], coordinate[1]);
      	return point;
      }
     
     $.removeAllMarkers = function() {
     	$.each(markers, function(index, marker) {
     		map.removeOverlay(marker);
     		marker.dispose();
     	});
     }
     
     $.getDateFromJson = function(time) {
    	 return time.substring(4,6)+"月"+time.substring(6,8)+"日  "+time.substring(8,10)+":"+time.substring(10,12); 
    	 //time.substring(0,4)+"-"+time.substring(4,6)+"-"+time.substring(6,8)+"   "+time.substring(8,10)+":"+time.substring(10,12)+":"+time.substring(12,14);
      }
    