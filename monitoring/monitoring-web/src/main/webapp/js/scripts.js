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

        const row1 = document.createElement('tr');
        const th1 = document.createElement('th');
        th1.setAttribute('scope', 'row');
        th1.textContent = "Free Memory";
        const td1 = document.createElement('td');
        td1.textContent = data.freeMemory.toString();

        const row2 = document.createElement('tr');
        const th2 = document.createElement('th');
        th2.setAttribute('scope', 'row');
        th2.textContent = "Max Memory";
        const td2 = document.createElement('td');
        td2.textContent = data.maxMemory.toString();

        const row3 = document.createElement('tr');
        const th3 = document.createElement('th');
        th3.setAttribute('scope', 'row');
        th3.textContent = "Total Memory";
        const td3 = document.createElement('td');
        td3.textContent = data.totalMemory.toString();

        const row4 = document.createElement('tr');
        const th4 = document.createElement('th');
        th4.setAttribute('scope', 'row');
        th4.textContent = "Available Processors";
        const td4 = document.createElement('td');
        td4.textContent = data.availableProcessors.toString();

        const row5 = document.createElement('tr');
        const th5 = document.createElement('th');
        th5.setAttribute('scope', 'row');
        th5.textContent = "System Load Average";
        const td5 = document.createElement('td');
        td5.textContent = data.systemLoadAverage.toString();

        div.appendChild(table);
        table.appendChild(tbody);
        tbody.appendChild(row1);
        row1.appendChild(th1);
        row1.appendChild(td1);
        tbody.appendChild(row2);
        row2.appendChild(th2);
        row2.appendChild(td2);
        tbody.appendChild(row3);
        row3.appendChild(th3);
        row3.appendChild(td3);
        tbody.appendChild(row4);
        row4.appendChild(th4);
        row4.appendChild(td4);
        tbody.appendChild(row5);
        row5.appendChild(th5);
        row5.appendChild(td5);

    } else {
        console.log('Error...');
    }

};

// Send request
request.send();