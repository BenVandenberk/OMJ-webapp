<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Registratie Klant</title>
<script src="scripts/jquery.js" type="text/javascript"></script>
<script src="scripts/registreerKlant.js" type="text/javascript"></script>
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

<h1>Klanten registreren</h1>
<h3>Vul de gegevens in:</h3>

<form id="klantForm">

<table>
	<tr>
		<td><label>Naam*: </label></td>
		<td><input type="text" name="klantNaam" required/></td>
	</tr>
	<tr>
		<td><label>BTW-nummer: </label></td>
		<td><input type="text" name="BTWNr" /></td>
	</tr>
		<td><label>Straat*: </label></td>
		<td><input type="text" name="straat" required/></td>
	</tr>
	</tr>
		<td><label>Nr*: </label></td>
		<td><input type="text" name="nr" required/></td>
	</tr>
	</tr>
		<td><label>Postcode*: </label></td>
		<td><input type="text" name="postcode" required pattern=".{4,}"/></td>
	</tr>
	</tr>
		<td><label>Woonplaats*: </label></td>
		<td><input type="text" name="woonplaats" required/></td>
	</tr>
	</tr>
		<td><label>Land*: </label></td>
		<td><input type="text" name="land" required pattern=".{3,}"/></td>
	</tr>
	</tr>
		<td><label>Telefoonnummer*: </label></td>
		<td><input type="text" name="tel" required pattern=".{9,}"/></td>
	</tr>
	</tr>
		<td><label>Email*: </label></td>
		<td><input type="email" name="email" required/></td>
	</tr>
	<tr>
		<td><input type="submit" value="Voeg Klant toe"/></td>
	</tr>
</table>

</form>

</div>

</body>
</html>