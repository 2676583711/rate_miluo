let state;
function getState(){
    var xhr = new XMLHttpRequest();
    xhr.open("GET", "/desktop/site/state");
    xhr.onload = function(){
        state = xhr.response;
    };
    xhr.send();
}
addEventListener("message", function(evt){  // Worker线程调用监听器，等同于this.addEventListener();
    getState();
    let i = setInterval(function () {
        if(state){
            clearInterval(i);
            postMessage(state);
            self.close();
        }
    },50);
}, false);