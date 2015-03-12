function showJSONKlanten(request, resultRegion) {
    if ((request.readyState == 4) && (request.status == 200)) {
    	var rawData = request.responseText;
    	var data = eval('(' + rawData + ')');
    	var table = createTable(data.headings, data.klanten);
    	htmlInsert(table, resultRegion);
    }
    if ((request.readyState == 4) && (request.status == 521)) {
    	htmlInsert(request.responseText, resultRegion);
    }
}

function createTable(headings, data) {
	var html = '';
	html += '<table class="border"><tr class="border" id="head">';
	for (var i = 0; i < headings.length; i++) {
		html += '<td class="border">' + headings[i] + '</td>';
	}
	html += '</tr>';
	for (var i = 0; i < data.length; i++) {
		html += '<tr class="border">';
		html += '<td class="border">' + data[i].naam + '</td>';
		html += '<td class="border">' + data[i].BTWnummer + '</td>';
		html += '<td class="border">' + data[i].facturatieAdres + '</td>';
		html += '<td class="border">' + data[i].afleverAdres + '</td>';
		html += '<td class="border">' + data[i].tel + '</td>';
		html += '<td class="border">' + data[i].email + '</td>';
		html += '</tr>';
	}
	html += '</table>';
	return html;
}

