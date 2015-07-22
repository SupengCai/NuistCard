
	var isAddedMarker1=false;
    var isAddedMarker2=false;

	var isAddedpolyline1=false;

	var map = new BMap.Map("life-map");
	var zoomControl = new BMap.ZoomControl();//缩放控件
    var scaleControl = new BMap.ScaleControl();//比例尺控件

	var point1 = new BMap.Point(118.734692, 31.994756);
	var point2 = new BMap.Point(118.78451, 32.042423);
	var polyline1 = new BMap.Polyline( [ point1, point2 ], {
		strokeColor : "blue",
		strokeWeight : 3,
		strokeOpacity : 0.3
	});

	var marker = new BMap.Marker(point1); // 创建标注
	var marker1 = new BMap.Marker(point1); // 创建标注
	var marker2 = new BMap.Marker(point2); // 创建标注

	 var html = [];
     html.push('<span style="font-size:12px">TA的状态 </span><br/>');
     html.push('<table border="0" cellpadding="1" cellspacing="1" >');
     html.push('  <tr>');
     html.push('      <td align="left" class="common">TA的位置:</td>');
     html.push('      <td colspan="2"><input type="text" maxlength="50" size="18"  id="txtPosition"></td>');
     html.push('	     <td valign="top"><span class="star">*</span></td>');
     html.push('  </tr>');
     html.push('  <tr>');
     html.push('      <td align="left" class="common">TA在做什么:</td>');
     html.push('      <td colspan="2"><input type="text" maxlength="50" size="18"  id="txtDoWhat"></td>');
     html.push('	     <td valign="top"><span class="star">*</span></td>');
     html.push('  </tr>');
     html.push('  <tr>');
     html.push('      <td  align="left" class="common">TA的心情:</td>');
     html.push('      <td colspan="2"><input type="text" maxlength="30" size="18"  id="txtFeel"></td>');
     html.push('	     <td valign="top"><span class="star">*</span></td>');
     html.push('  </tr>');
     html.push('  <tr>');
     html.push('      <td  align="left" class="common">时间:</td>');
     html.push('      <td  colspan="2"><input type="text" maxlength="50" size="18"  id="txtAddTime"></td>');
     html.push('	     <td valign="top"><span class="star">*</span></td>');
     html.push('  </tr>');
     html.push('  <tr>');
     html.push('	     <td  align="center" colspan="3">');
     html.push('          <input type="button" name="btnOK"  onclick="fnOK()" value="确定">&nbsp;&nbsp;');
     html.push('		     <input id="btnClear" type="button" name="btnClear" value="重填">');
     html.push('	     </td>');
     html.push('  </tr>');
     html.push('</table>');

     var htmlPointInfo = [];
     htmlPointInfo.push('<table border="0" cellpadding="1" cellspacing="1" >');
     htmlPointInfo.push('  <tr>');
     htmlPointInfo.push('      <td align="left" class="common">TA在哪:</td>');
     htmlPointInfo.push('      <td colspan="2"><label type="text" maxlength="50" size="18"  id="txtPosition">aaaaaaaaaaaaaaaaaaa</label></td>');
     htmlPointInfo.push('  </tr>');
     htmlPointInfo.push('  <tr>');
     htmlPointInfo.push('      <td align="left" class="common">TA正在:</td>');
     htmlPointInfo.push('      <td colspan="2"><label type="text" maxlength="50" size="18"  id="txtDoWhat">b</label></td>');
     htmlPointInfo.push('  </tr>');
     htmlPointInfo.push('  <tr>');
     htmlPointInfo.push('      <td  align="left" class="common">TA想说:</td>');
     htmlPointInfo.push('      <td colspan="2"><label type="text" maxlength="30" size="18"  id="txtFeel">c</label></td>');
     htmlPointInfo.push('  </tr>');
     htmlPointInfo.push('  <tr>');
     htmlPointInfo.push('      <td  align="left" class="common">时间:</td>');
     htmlPointInfo.push('      <td  colspan="2"><label type="text" maxlength="50" size="18"  id="txtAddTime">d</label></td>');
     htmlPointInfo.push('  </tr>');
     htmlPointInfo.push('  <tr>');
     htmlPointInfo.push('	     <td  align="center" colspan="3">');
     htmlPointInfo.push('          <input type="button" name="btnOK"  onclick="fnOK()" value="确定">&nbsp;&nbsp;');
     htmlPointInfo.push('		     <input id="btnClear" type="button" name="btnClear" value="重填">');
     htmlPointInfo.push('	     </td>');
     htmlPointInfo.push('  </tr>');
     htmlPointInfo.push('</table>');

     var infoWin = new BMap.InfoWindow(htmlPointInfo.join(""), {
         offset: new BMap.Size(0, -10)
     });
     
     var local = new BMap.LocalSearch(map, {
 		  renderOptions:{map: map}
 		});
