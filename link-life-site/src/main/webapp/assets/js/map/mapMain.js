$(function() {



    map.addControl(zoomControl);
    map.addControl(scaleControl);
	map.centerAndZoom(city, 13);

//     map.addEventListener("click", function(e){
//    	    var pt = e.point;
//    	    gc.getLocation(pt, function(rs){
//    	        var addComp = rs.addressComponents;
//    	        alert(addComp.province + ", " + addComp.city + ", " + addComp.district + ", " + addComp.street + ", " + addComp.streetNumber);
//    	    });
//    	});


 });

