<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Klant Zoeken</title>
<script src="scripts/jquery.js" type="text/javascript"></script>
<script src="scripts/zoekKlant.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body>

<div class="menu">

<a href="index.jsp">Home</a><br/>
<a href="klantenZoeken.jsp">Klant Zoeken</a><br/>
<a href="klantenRegistreren.jsp">Klant Registreren</a><br/>
<a href="opdrachtenAanmaken.jsp">Opdrachten Aanmaken</a><br/>
<a href="opdrachtenZoeken.jsp">Opdrachten Zoeken</a>

</div>

<div class="center">

<h1>Zoek een klant</h1>
<h3>Geef zoekparameters in:</h3>

<form id="zoekKlantForm">

<table>
	<tr>
		<td><label>ID: </label></td>
		<td><input type="text" name="zoekID"/></td>
	</tr>
		<tr>
		<td><label>Naam: </label></td>
		<td><input type="text" name="zoekNaam"/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Zoek"/></td>
	</tr>
</table>

</form>

<div id="output">

</div>

</div>

</body>
</html>