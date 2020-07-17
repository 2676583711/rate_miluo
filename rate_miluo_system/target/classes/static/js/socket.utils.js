var socket;
$(function() {
	if("WebSocket" in window) {
		// 建立连接
		socket = io.connect('ws://47.101.50.57:3100');
		getSite(1,"HB071533200201");
	} else {
		// 浏览器不支持 WebSocket
		alert("您的浏览器不支持!");
	}

});

function getSite(code, siteCode){
	socket.emit('onemessage', {
		'mn': siteCode
	});
	switch(code){
	case 1:
		socket.on("onemessage", function(res) {
			return res.str.online;
		});
		break;
	case 2:
		
		break;
	}
}
function getSiteOnline(siteCode){
	getSite(1,siteCode);
}
