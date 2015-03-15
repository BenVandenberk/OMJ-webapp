var klanten;

$(function() {
	$.ajax({
		url : "ZoekKlant",
		method : "GET",
		dataType : "json",
		success : vulSelect
	});

	$("#opdrachtForm").validate({
		rules : {
			korteOmschrijving : {
				required: true,
				minlength: 5
			},
			beheerder : "required"
		},
		messages : {			
			beheerder : {
				required: "Geef de beheerder van de opdracht mee"				
			},
			korteOmschrijving: {
				required: "Geef een korte omschrijving in",
				minlength: "De omschrijving moet minstens 5 karakters lang zijn"
			}
		},
		errorElement : 'div',
		errorLabelContainer: '.errorMessages'
	});

	$("#btnMaakOpdracht").click(function(e) {
		if ($("#opdrachtForm").valid()) {
			var formData = prepareData("opdrachtForm");
			$.ajax({
				url : "OpdrachtAanmaken",
				method : "POST",
				data : formData,
				success : function(data, statusText, jqXHR) {
					alert("Opdracht geregistreerd");
				}
			});
		}
	})

	$("#huidigeDatum").html(getDatum());
});

function vulSelect(data, statusText, jqXHR) {
	klanten = data.klanten;
	for (var i = 0; i < klanten.length; i++) {
		var opt = document.createElement('option');
		opt.text = klanten[i].naam;
		opt.value = klanten[i];
		$("#klanten").append(opt);
	}
}

function prepareData(formId) {
	var formData = $("#" + formId).serialize();
	var selectedIndex = $("#" + formId).find("select").prop("selectedIndex");
	var klant = klanten[selectedIndex];
	formData += '&klantId=' + klant.id;
	return formData;
}

function valideer() {
	$("#opdrachtForm").validate({
		rules : {
			korteOmschrijving : "required"
		},
		messages : {
			korteOmschrijving : "Geef een korte omschrijving in"
		}
	});
}