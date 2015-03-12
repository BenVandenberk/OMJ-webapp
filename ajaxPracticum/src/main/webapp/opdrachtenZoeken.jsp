<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Opdracht Zoeken</title>
<script src="scripts/ajax.js" type="text/javascript"></script>
<script src="scripts/zoekOpdracht.js" type="text/javascript"></script>
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

<h1>Opdrachten zoeken</h1>
<h3>Geef zoekparameters in:</h3>

<form action="">

<table>
	<tr>
		<td><label>Klant: </label></td>
		<td><input type="text" name="klant"/></td>
	</tr>
		<tr>
		<td><label>Beheerder: </label></td>
		<td><input type="text" name="beheerder"/></td>
	</tr>
	<tr>
		<td><input type="button" value="Zoek" onclick="ajaxGet('ZoekOpdracht', this.form, 'output', showXMLOpdrachten)"/></td>
	</tr>
</table>

</form>

</div>

<div id="output">

</div>

</body>
</html>