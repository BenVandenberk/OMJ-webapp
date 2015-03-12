package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistency.DBAccess;
import util.Datum;
import bean.Opdracht;

@WebServlet("/OpdrachtAanmaken")
public class OpdrachtAanmaken extends HttpServlet {

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String korteOmschrijving = request.getParameter("korteOmschrijving");
		String omschrijving = request.getParameter("omschrijving");
		String beheerder = request.getParameter("beheerder");
		int klantID = Integer.parseInt(request.getParameter("klantId"));
		Datum geopend = new Datum();

		Opdracht opdracht = new Opdracht(korteOmschrijving, klantID, beheerder, geopend);
		opdracht.setOmschrijving(omschrijving);

		DBAccess dbAccess = DBAccess.getInstance();
		String message = "";
		try {
			dbAccess.schrijfOpdracht(opdracht);
		} catch (Exception ex) {
			message += ex.getMessage();
		}

		response.setHeader("Cache-Control", "no-cache");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("text/plain");
		PrintWriter out = response.getWriter();
		if (message.equals("")) {
			out.println("De opdracht werd correct opgenomen");
		} else {
			out.println("Fout bij het registreren van de opdracht:\n\n" + message);
		}
		out.close();
	}

}
