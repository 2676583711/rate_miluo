window.W = {
    version: "23.1.1",
    assets: "23.1.1.lib.baaa",
    sha: "a2400baaa",
    target: "lib",
    build: "2020-01-27, 09:01"
};
/*! Copyright (c) Windyty SE, 2019 all rights reserved */
var printError = function(t) {
    var e = function() {
        document.getElementById("windy").innerHTML = '\n\t\t\t<p style="text-align: center; margin-top: 200px;">\n\t\t\t\t' + t + '<br /><br />\n\t\t\t\tvisit <a style="color: inherit" href="https://www.windy.com/" target="_blank">www.windy.com</a> or <a style="color: inherit" href="https://api.windy.com/" target="_blank">api.windy.com</a>\n\t\t\t</p>'
    };
    console.error(t),
        "complete" === document.readyState ? e() : window.addEventListener("load", e)
}
    , message = function(t) {
    document.querySelector("#windy").insertAdjacentHTML("beforeend", '<section style="position: absolute;\n\t\t\t\t\tleft: 50%;\n\t\t\t\t\tbottom: 20%;\n\t\t\t\t\topacity: .6;\n\t\t\t\t\tpointer-events: none;\n\t\t\t\t\tline-height: 1.6;\n\t\t\t\t\ttext-align: center;\n\t\t\t\t\ttransform: translate(-50%,-50%);\n\t\t\t\t\tfont-size: 16px;">' + t + "</section>")
};
!function() {    //加感叹号表示立即调用该匿名函数

    if (DEBUG = !1,window.W && window.W.version)
        if (window.L) {
            var t = "https://www.windy.com/v/" + W.assets;
            window.windyInit = function(t, i) {
                var a;
                try {
                    var m = '{"paid":false,"exceeded":false,"domains":"","auth":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1YSI6Ik1vemlsbGEvNS4wIChXaW5kb3dzIE5UIDYuMTsgV09XNjQpIEFwcGxlV2ViS2l0LzUzNy4zNiAoS0hUTUwsIGxpa2UgR2Vja28pIENocm9tZS82OS4wLjM0OTcuMTAwIFNhZmFyaS81MzcuMzYiLCJpYXQiOjE1OTI4OTIwNTQsImV4cCI6MTYwMDA5MjA1NH0.MDUjGe7aBkF9qZ6cpIKviGZ4VEJL1HUSPEP9p6X719Y","type":"basic","apiUser":"woifenga","status":"ok","id":2020195,"name":"http:59.172.208.250:8082"}';
                    a = JSON.parse(m)
                } catch (t) {
                    a = {}
                }
                var s = a.auth
                    , d = a.paid
                    , c = a.domains
                    , p = a.apiUser
                    , l = a.id
                    , u = a.name
                    , w = a.type
                    , y = a.exceeded;
                window.W.windyBoot = {
                    options: t,
                    cb: i,
                    auth: s,
                    id: l,
                    name: u,
                    type: w,
                    paid: d,
                    apiUser: p,
                    exceeded: y
                }
            };
            var e = function(t, e) {
                if (!e || !/\S+/.test(e))
                    return !0;
                var n = document.location.hostname;
                var i = e.split(",").map(function(t) {
                    return new RegExp(t.trim().toLowerCase())
                }).filter(function(t) {
                    return t.test(n)
                });
                return i && i.length > 0
            }
        } else
            printError("Leaflet library is missing");
    else
        printError("Missing global object W. Have you loaded libManifest?")

    const options = {
        key: 'o8Dyvw27fqTOk4suZE0LDt8o7fdd56Xm', // REPLACE WITH YOUR KEY !!!
        lat: 28.783909,
        lon: 113.147681,
        zoom: 11,
    };

    windyInit(options, windyAPI => {
    const { map } = windyAPI;
    getMap(map)
    const MARKER = encodeURIComponent(`<?xml version="1.0" encoding="UTF-8" standalone="no"?>
        <!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
        <svg width="100%" height="100%" viewBox="0 0 14 14" version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" xml:space="preserve" style="fill-rule:evenodd;clip-rule:evenodd;stroke-linejoin:round;stroke-miterlimit:1.41421;">
        <path d="M4.784,13.635c0,0 -0.106,-2.924 0.006,-4.379c0.115,-1.502 0.318,-3.151 0.686,-4.632c0.163,-0.654 0.45,-1.623 0.755,-2.44c0.202,-0.54 0.407,-1.021 0.554,-1.352c0.038,-0.085 0.122,-0.139 0.215,-0.139c0.092,0 0.176,0.054 0.214,0.139c0.151,0.342 0.361,0.835 0.555,1.352c0.305,0.817 0.592,1.786 0.755,2.44c0.368,1.481 0.571,3.13 0.686,4.632c0.112,1.455 0.006,4.379 0.006,4.379l-4.432,0Z" style="fill:rgb(0,46,252);"/><path d="M5.481,12.731c0,0 -0.073,-3.048 0.003,-4.22c0.06,-0.909 0.886,-3.522 1.293,-4.764c0.03,-0.098 0.121,-0.165 0.223,-0.165c0.103,0 0.193,0.067 0.224,0.164c0.406,1.243 1.232,3.856 1.292,4.765c0.076,1.172 0.003,4.22 0.003,4.22l-3.038,0Z" style="fill:rgb(255,255,255);fill-opacity:0.846008;"/>
    </svg>`);
    const MARKER_ICON_URL = `data:image/svg+xml;utf8,${MARKER}`;

    const BoatIcon = L.icon({
        iconUrl: MARKER_ICON_URL,
        iconSize: [24, 24],
        iconAnchor: [12, 12],
        popupAnchor: [0, 0],
    });

    const markers = [];

    const updateIconStyle = () => {
        for (const marker of markers) {
            const icon = marker._icon;
            if (!icon) {
                continue;
            }
            const heading = icon.getAttribute('data-heading');
            if (icon.style.transform.indexOf('rotateZ') === -1) {
                icon.style.transform = `${
                    icon.style.transform
                    } rotateZ(${heading || 0}deg)`;
                icon.style.transformOrigin = 'center';
            }
        }
    };
    windtyMap = map;
    map.on("zoomend", function (ev) {
         // if(map.getZoom()>11){
         //     oldMapInit(map);
         // }
        changeAqiSiteStyle(map.getZoom());
    });
      // addOptionControl(map, W);
     changeAqiSiteStyle(11);
    //initOnlyMarker();
     addShapeFile(map);
   // addAqiRangeControl(map)
});
}();

