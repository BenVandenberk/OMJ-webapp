<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
{ "headings": ["Naam", "BTW-nummer", "Facturatie Adres", "AfleverAdres", "Tel", "Email"], "klanten": [
<c:forEach var="klant" items="${klanten}" varStatus="status">
{"id": ${klant.id}, "naam": "${klant.naam}", "BTWnummer": "${klant.btwNummer}", "facturatieAdres": "${klant.facturatieAdres}", "afleverAdres": "${klant.afleverAdres}", "tel": "${klant.tel}", "email": "${klant.email}"}
<c:if test="${!status.last}">,</c:if>
</c:forEach>
] }
