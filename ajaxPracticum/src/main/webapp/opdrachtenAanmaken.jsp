<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Opdrachten Aanmaken</title>
<script src="scripts/opdrachtenAanmaken.js" type="text/javascript"></script>
<script src="scripts/ajax.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="./css/style.css">
</head>
<body onload="load()">

<div class="menu">

<a href="index.jsp">Home</a><br/>
<a href="klantenZoeken.jsp">Klant Zoeken</a><br/>
<a href="klantenRegistreren.jsp">Klant Registreren</a><br/>
<a href="opdrachtenAanmaken.jsp">Opdrachten Aanmaken</a><br/>
<a href="opdrachtenZoeken.jsp">Opdrachten Zoeken</a>

</div>

<div class="center">

	<h1>Opdrachten aanmaken</h1>
	<h3>Vul de gegevens in:</h3>
	<div id="error"></div>

	<form action="">
		<table>
			<tr>
				<td><label>Klant*:</label></td>
				<td><select id="klanten" style="width:150px"></select></td>
			</tr>
			<tr>
				<td><label>Korte omschrijving*:</label></td>
				<td><input type="text" name="korteOmschrijving" style="width:300px"/></td>
			</tr>
			<tr>
				<td style="vertical-align:top"><label>Uitgebreide omschrijving:</label></td>
				<td><textarea name="omschrijving" style="width:300px;height:100px"></textarea></td>
			</tr>
			<tr>
				<td><label>Beheerder*:</label></td>
				<td><input type="text" name="beheerder" style="width:300px"/></td>
			</tr>
			<tr>
				<td><label>Geopend op:</label></td>
				<td><label id="huidigeDatum"></label></td>
			</tr>
			<td></td>
			<td style="text-align:right"><input type="button" value="Opslaan" onclick="ajaxCheckedPost('OpdrachtAanmaken', this.form, handleResponseAlert, prepareData, dataOk)"/></td>
		</table>
	</form>
	
	</div>
	
</body>
</html>