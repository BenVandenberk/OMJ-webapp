$(function(){
	$("#zoekOpdrachtForm").submit(function(e) {
		e.preventDefault();
		var data = $(this).serialize();
		$.ajax({
			url: "ZoekOpdracht",
			method: "GET",
			data: data,
			dataType: "xml",
			success: toonOpdrachten
		})
	})
});

function toonOpdrachten(data, statusText, jqXHR) {
	var headings = data.getElementsByTagName('heading');
	var opdrachten = data.getElementsByTagName('opdracht');
	$("#output").html(createTable(headings, opdrachten));
}

function createTable(headings, data) {
	var html = '';
	html += '<table class="border"><tr class="border" id="head">';
	for (var i = 0; i < headings.length; i++) {
		html += '<td class="border">' + headings[i].childNodes[0].nodeValue + '</td>';
	}
	html += '</tr>';
	for (var i = 0; i < data.length; i++) {
		html += '<tr class="border">';
		html += '<td class="border">' + getNodeValue(data[i].getElementsByTagName('opdrachtID')[0].childNodes) + '</td>';
		html += '<td class="border">' + getNodeValue(data[i].getElementsByTagName('klant')[0].childNodes) + '</td>';
		html += '<td class="border">' + getNodeValue(data[i].getElementsByTagName('korteOmschrijving')[0].childNodes) + '</td>';		
		html += '<td class="border">' + getNodeValue(data[i].getElementsByTagName('omschrijving')[0].childNodes) + '</td>';		
		html += '<td class="border">' + getNodeValue(data[i].getElementsByTagName('geopend')[0].childNodes) + '</td>';		
		html += '<td class="border">' + getNodeValue(data[i].getElementsByTagName('afgesloten')[0].childNodes) + '</td>';		
		html += '</tr>';
	}
	html += '</table>';
	return html;
}

function getNodeValue(nodes) {
	return nodes.length === 1 ? nodes[0].nodeValue : "";
}