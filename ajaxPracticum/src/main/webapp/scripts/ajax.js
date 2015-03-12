function getRequestObject() {
	if (window.XMLHttpRequest) {
		return (new XMLHttpRequest());
	} else if (window.ActiveXObject) {
		return (new ActiveXObject("Microsoft.XMLHTTP"));
	} else {
		return (null);
	}
}

function ajaxCheckedPost(adres, form, responseHandler, prepareData) {
//	var errorObj = dataOk();
//	if (errorObj.error) {
//		alert(errorObj.message);
//	} else {
		var request = getRequestObject();
		request.onreadystatechange = function() {
			responseHandler(request);
		}
		request.open("POST", adres, true);
		request.setRequestHeader("Content-type",
				"application/x-www-form-urlencoded");
		request.send(prepareData(form));
//}
}

function ajaxGet(adres, form, resultRegion, responseHandler) {
	var request = getRequestObject();
	request.onreadystatechange = function() {
		responseHandler(request, resultRegion);
	};
	request.open("GET", adres.concat("?", parseInput(form)), true);
	request.send(null);
}

function ajaxSimpleGet(adres, responseHandler) {
	var request = getRequestObject();
	request.onreadystatechange = function() {
		responseHandler(request);
	};
	request.open("GET", adres, true);
	request.send(null);
}

function handleResponseAlert(request) {
	if ((request.readyState == 4) && (request.status == 200)) {
		alert(request.responseText);
	}
}

function htmlInsert(html, id) {
	document.getElementById(id).innerHTML = html;
}

function parseInput(form) {
	var parameters = "";
	for (var i = 0; i < form.elements.length; i++) {
		if (form.elements[i].type == "submit") {
			continue;
		}
		if (form.elements[i].type == "button") {
			continue;
		}
		if (i > 0) {
			parameters += "&";
		}
		parameters += form.elements[i].name + "=" + form.elements[i].value;
	}
	return parameters;
}