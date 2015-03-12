$(function() {
	$("#klantForm").submit(function(e) {
		e.preventDefault();
		var data = $(this).serialize();
		$.ajax({
			url : "RegistreerKlant",
			method : "POST",
			data: data,
			success: function(data, textStatus, jqXHR) {
				alert(jqXHR.responseText);
			}
		});
		e.unbind();
	})
});


