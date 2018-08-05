const app = document.getElementById('root');

const container = document.createElement('div');
container.setAttribute('class', 'container');

app.appendChild(container);

// Create a request variable and assign a new XMLHttpRequest object to it.
var request = new XMLHttpRequest();

// Open a new connection, using the GET request on the URL endpoint.
request.open('GET', '/rest/monitor', true);

request.onload = function() {

    // Begin accessing JSON data here.
    var data = JSON.parse(this.response);

    if (request.status >= 200 && request.status < 400) {
        console.log("Accessing monitoring data...");

        const div = document.createElement('div');
        div.setAttribute('class', 'row');

        container.appendChild(div);

        const table = document.createElement('table');
        table.setAttribute('class', 'table');
        const tbody = document.createElement('tbody');

        var freeMemory = {element:"freeMemory", title:"Free Memory"};
        var maxMemory = {element:"maxMemory", title:"Max Memory"};
        var totalMemory = {element:"totalMemory", title:"Total Memory"};
        var availableProcessors = {element:"availableProcessors", title:"Available Processors"};
        var systemLoadAverage = {element:"systemLoadAverage", title:"System Load Average"};
        var usedMemory = {element:"usedMemory", title:"Used Memory (%)"};

        var monitors = [freeMemory, maxMemory, totalMemory, availableProcessors, systemLoadAverage, usedMemory];

        div.appendChild(table);
        table.appendChild(tbody);

        //var i;
        for (var i = 0; i < monitors.length; i++) {
            const row = document.createElement('tr');
            const th = document.createElement('th');
            th.setAttribute('scope', 'row');
            th.textContent = monitors[i].title;
            const td = document.createElement('td');
            td.textContent = data[monitors[i].element].toString();
            tbody.appendChild(row);
            row.appendChild(th);
            row.appendChild(td);
        }

    } else {
        console.log('Error...');
    }

};

// Send request
request.send();