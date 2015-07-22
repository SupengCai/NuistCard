$(function() {



    map.addControl(zoomControl);
    map.addControl(scaleControl);
	map.centerAndZoom(city, 13);
	
		


    marker1.addEventListener("click", function() {
//		map.setCenter(point);
//        map.openInfoWindow(infoWin, marker1.getPosition()); // 打开信息窗口
        
        
//        $("#btnClear").click(function(){
//        	$("#txtPosition").html(" ");
//        });
    });
	 marker2.addEventListener("click", function() {
	 	map.setCenter(point2);
        map.openInfoWindow(infoWin, marker2.getPosition()); // 打开信息窗口
        $("#btnClear").click(function(){
        	$("#txtPosition").html(" ");
        });
    });



//     map.addEventListener("click", function(e){
//    	    var pt = e.point;
//    	    gc.getLocation(pt, function(rs){
//    	        var addComp = rs.addressComponents;
//    	        alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
//    	    });
//    	});



	 $("#point1").click(function(){
		 		map.closeInfoWindow();
				map.removeOverlay(marker);
				marker.dispose();
				marker = new BMap.Marker(point1);
				map.setCenter(marker.getPosition());
				map.addOverlay(marker);
				

				 marker.addEventListener("click", function() {
						map.setCenter(marker.getPosition());
				        map.openInfoWindow(infoWin, marker.getPosition()); // 打开信息窗口
				        $("#btnClear").click(function(){
				        	$("#txtPosition").html(" ");
				        });
				    });
			});

		$("#point2").click(function(){

				map.closeInfoWindow();
				map.removeOverlay(marker);
				marker.dispose();
				marker = new BMap.Marker(point2);
				map.setCenter(marker.getPosition());
				map.addOverlay(marker);

				 marker.addEventListener("click", function() {
						map.setCenter(marker.getPosition());
				        map.openInfoWindow(infoWin, marker.getPosition()); // 打开信息窗口
				        $("#btnClear").click(function(){
				        	$("#txtPosition").html(" ");
				        });
				    });
		});

		$("#polyline1").click(function(){
			if(!isAddedpolyline1){
				map.addOverlay(polyline1);
				isAddedpolyline1=true;
			}else{
				map.removeOverlay(polyline1);
				isAddedpolyline1=false;
			}
		});

 });

//function showMarker(point){
//
//map.closeInfoWindow();
//map.removeOverlay(marker);
//marker.dispose();
//marker = new BMap.Marker(point);
//map.setCenter(marker.getPosition());
//map.addOverlay(marker);
//
// marker.addEventListener("click", function() {
//		map.setCenter(marker.getPosition());
//        map.openInfoWindow(infoWin, marker.getPosition()); // 打开信息窗口
//        $("#btnClear").click(function(){
//        	$("#txtPosition").html(" ");
//        }
//        });
//    });