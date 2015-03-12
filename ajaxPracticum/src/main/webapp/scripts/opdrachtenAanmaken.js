var klanten;

function load() {
	ajaxSimpleGet('ZoekKlant', handleResponse);
	vulDatumIn('huidigeDatum');
}

function handleResponse(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		var rawData = request.responseText;
		var data = eval('(' + rawData + ')');
		klanten = data.klanten;
		vulSelect(klanten, 'klanten');
	}
	if ((request.readyState == 4) && (request.status == 521)) {
		htmlInsert('Fout bij het laden van klanten:\n' + request.responseText,
				'error');
	}
}

function prepareData(form) {
	var parameters = "";
	var first = true;
	for (var i = 0; i < form.elements.length; i++) {
		if (form.elements[i].type == 'submit') {
			continue;
		}
		if (form.elements[i].type == 'button') {
			continue;
		}
		if (!first) {
			parameters += '&';
		}
		if (form.elements[i].type == 'select-one') {
			var select = form.elements[i];
			var klant = klanten[select.selectedIndex];
			parameters += 'klantId=' + klant.id;
		} else {
			parameters += form.elements[i].name + '=' + form.elements[i].value;
		}
		first = false;
	}
	return parameters;
}

function dataOk() {
	var message = '';

	var select = document.getElementById('klanten');
	if (select.selecedIndex < 0) {
		message += 'Klant\n';
	}
	var korteOmschrijving = document.getElementsByName('korteOmschrijving')[0];
	if (korteOmschrijving.value == '') {
		message += 'Korte omschrijving\n';
	}
	var beheerder = document.getElementsByName('beheerder')[0];
	if (beheerder.value == '') {
		message += 'Beheerder\n';
	}
	
	if (message == '') {
		return {
			message: message,
			error: false
		};
	} else {
		return {
			message: 'Je mist volgende velden:\n' + message,
			error: true
		};
	}
}

function vulSelect(klanten, id) {
	var select = document.getElementById(id);
	for (var i = 0; i < klanten.length; i++) {
		var opt = document.createElement('option');
		opt.text = klanten[i].naam;
		opt.value = klanten[i];
		select.appendChild(opt);
	}
}

function vulDatumIn(labelId) {
	var today = new Date();
	var dd = today.getDate();
	var mm = today.getMonth() + 1;
	var yyyy = today.getFullYear();

	if (dd < 10) {
		dd = '0' + dd
	}

	if (mm < 10) {
		mm = '0' + mm
	}

	today = dd + '/' + mm + '/' + yyyy;
	htmlInsert(today, labelId);
}