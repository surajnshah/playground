const socketapp = document.getElementById('socketroot');

const socketcontainer = document.createElement('div');
socketcontainer.setAttribute('class', 'container');

socketapp.appendChild(socketcontainer);

var wsocket;
function connect() {
    wsocket = new WebSocket("ws://localhost:8080/monitorserver");
    wsocket.onmessage = onMessage;
}

const div = document.createElement('div');
div.setAttribute('class', 'row');

socketcontainer.appendChild(div);

const table = document.createElement('table');
table.setAttribute('class', 'table');
const tbody = document.createElement('tbody');

var freeMemory = {element:"freeMemory", title:"Free Memory"};
var maxMemory = {element:"maxMemory", title:"Max Memory"};
var totalMemory = {element:"totalMemory", title:"Total Memory"};
var availableProcessors = {element:"availableProcessors", title:"Available Processors"};
var systemLoadAverage = {element:"systemLoadAverage", title:"System Load Average"};
var usedMemory = {element:"usedMemory", title:"Used Memory (%)"};
var systemCpuLoad = {element:"systemCpuLoad", title:"System CPU Load (%)"};

var monitors = [freeMemory, maxMemory, totalMemory, availableProcessors, systemLoadAverage, usedMemory, systemCpuLoad];

div.appendChild(table);
table.appendChild(tbody);

for (var i = 0; i < monitors.length; i++) {
    const row = document.createElement('tr');
    const th = document.createElement('th');
    th.setAttribute('scope', 'row');
    th.textContent = monitors[i].title;
    const td = document.createElement('td');
    td.textContent = monitors[i].element;
    td.setAttribute("Id", monitors[i].element);
    tbody.appendChild(row);
    row.appendChild(th);
    row.appendChild(td);
}

function onMessage(evt) {
    var data = JSON.parse(evt.data);

    for (var i = 0; i < monitors.length; i++) {
        document.getElementById(monitors[i].element).innerHTML=data[monitors[i].element].toString();
    }

}
window.addEventListener("load", connect, false);