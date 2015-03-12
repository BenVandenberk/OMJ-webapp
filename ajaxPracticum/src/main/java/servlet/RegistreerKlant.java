package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistency.DBAccess;
import bean.Adres;
import bean.Contact;
import bean.ContactType;
import bean.Klant;

@WebServlet("/RegistreerKlant")
public class RegistreerKlant extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String naam = request.getParameter("klantNaam");
		String straat = request.getParameter("straat");
		String nr = request.getParameter("nr");
		String postcode = request.getParameter("postcode");
		String woonplaats = request.getParameter("woonplaats");
		String land = request.getParameter("land");
		String tel = request.getParameter("tel");
		String email = request.getParameter("email");
		String btw = request.getParameter("BTWNr");

		Adres facturatieAdres = new Adres(straat, nr, postcode, woonplaats, land);
		Adres afleverAdres = new Adres(straat, nr, postcode, woonplaats, land);
		Contact telContact = new Contact(ContactType.Tel, tel);
		Contact emailContact = new Contact(ContactType.Email, email);
		Klant teRegistrerenKlant = new Klant(naam, facturatieAdres, afleverAdres, telContact, emailContact);
		if (btw != null) {
			teRegistrerenKlant.setBtwNummer(btw);
		}

		DBAccess dbAccess = DBAccess.getInstance();
		String message = "";
		try {
			dbAccess.schrijfKlant(teRegistrerenKlant);
		} catch (Exception ex) {
			message += ex.getMessage();
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		if (message.equals("")) {
			out.println(naam + " werd correct opgenomen");
		} else {
			out.println("Fout bij het registreren van de klant:\n\n" + message);
		}
		out.close();
	}
}
