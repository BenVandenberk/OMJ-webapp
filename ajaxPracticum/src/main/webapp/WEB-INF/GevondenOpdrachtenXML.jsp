<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<opdrachten> 
	<headings> 
		<heading>OpdrachtID</heading>
		<heading>Klant</heading>
		<heading>Korte Omschrijving</heading>
		<heading>Volledige Omschrijving</heading>
		<heading>Geopend op</heading>
		<heading>Afgesloten op</heading>
	</headings>
	<c:forEach var="opdracht" items="${opdrachten}">
		<opdracht>
			<opdrachtID>${opdracht.id}</opdrachtID>
			<klant>${opdracht.klant.naam}</klant>
			<korteOmschrijving>${opdracht.korteOmschrijving}</korteOmschrijving>
			<omschrijving>${opdracht.omschrijving}</omschrijving>
			<geopend>${opdracht.geopend}</geopend>
			<afgesloten>${opdracht.afgesloten}</afgesloten>
		</opdracht>
	</c:forEach> 
</opdrachten>