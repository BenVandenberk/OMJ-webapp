$(function() {
	$("#zoekKlantForm").submit(function(e) {
		e.preventDefault();
		var data = $(this).serialize();
		$.ajax({
			url : "ZoekKlant",
			method : "GET",
			data : data,
			success : toonKlanten,
			dataType : "json"
		});
	})
});

function toonKlanten(data, textStatus, jqXHR) {	
	$("#output").html(createTable(data.headings, data.klanten));
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
