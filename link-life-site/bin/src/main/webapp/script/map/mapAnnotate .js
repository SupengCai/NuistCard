$(function() {

//     var curMkr = null; // 记录当前添加的Mkr
//     var mkrTool = new BMapLib.MarkerTool(map, {
//         autoClose: true
//     });
//     mkrTool.addEventListener("markend", function(evt) {
//         var mkr = evt.marker;
//         mkr.openInfoWindow(infoWin);
//         curMkr = mkr;
//     });
//
//     //打开样式面板
//     function openStylePnl() {
//         document.getElementById("divStyle").style.display = "block";
//     }
//
//     //选择样式
//     function selectStyle(index) {
//         mkrTool.open(); //打开工具
//         var icon = BMapLib.MarkerTool.SYS_ICONS[index]; //设置工具样式，使用系统提供的样式BMapLib.MarkerTool.SYS_ICONS[0] -- BMapLib.MarkerTool.SYS_ICONS[23]
//         mkrTool.setIcon(icon);
//         document.getElementById("divStyle").style.display = "none";
//     }

//     //提交数据
//     function fnOK() {
//         var name = encodeHTML(document.getElementById("txtName").value);
//         var tel = encodeHTML(document.getElementById("txtTel").value);
//         var addr = encodeHTML(document.getElementById("txtAddr").value);
//         var desc = encodeHTML(document.getElementById("areaDesc").value);
//
//         if (!name || !tel || !addr) {
//             alert("星号字段必须填写");
//             return;
//         }
//
//        if (curMkr) {
//             //设置label
//             var lbl = new BMap.Label(name, {
//                 offset: new BMap.Size(1, 1)
//             });
//             lbl.setStyle({
//                 border: "solid 1px gray"
//             });
//             curMkr.setLabel(lbl);
//
//             //设置title
//             var title = "电话: " + tel + "\n\r" + "地址: " + addr + "\n\r" + "描述: " + desc;
//             curMkr.setTitle(title);
//         }
//         if (infoWin.isOpen()) {
//             map.closeInfoWindow();
//         }
//
//         //在此用户可将数据提交到后台数据库中
//     }
//
//     //输入校验
//     function encodeHTML(a) {
//         return a.replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/"/g, "&quot;");
//     }
//
//     //重填数据
//     function fnClear() {
//         document.getElementById("txtName").value = "";
//         document.getElementById("txtTel").value = "";
//         document.getElementById("txtAddr").value = "";
//         document.getElementById("areaDesc").value = "";
//     }



 });
