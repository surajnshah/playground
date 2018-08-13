var wsocket;
function connect() {
    wsocket = new WebSocket("ws://localhost:8080/monitorserver");
    wsocket.onmessage = onMessage;
}
function onMessage(evt) {
    document.getElementById("monitor").innerHTML=evt.data;
}
window.addEventListener("load", connect, false);