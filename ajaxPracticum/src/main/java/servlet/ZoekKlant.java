package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistency.DBAccess;
import bean.Klant;

@WebServlet("/ZoekKlant")
public class ZoekKlant extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("zoekID");
		id = id == null ? "" : id;
		String naam = request.getParameter("zoekNaam");
		naam = naam == null ? "" : naam;

		DBAccess dbAccess = DBAccess.getInstance();
		String errorMessage = "";
		List<Klant> klanten = null;
		try {
			klanten = dbAccess.zoekKlanten(id, naam);
		} catch (Exception ex) {
			errorMessage = ex.getMessage();
		}

		if (!errorMessage.equals("")) {
			response.setStatus(521);
			request.setAttribute("errorMessage", String.format("521 - Server Error - %s", errorMessage));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Error.jsp");
			rd.include(request, response);
		} else {
			request.setAttribute("klanten", klanten);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/GevondenKlantenJSON.jsp");
			rd.include(request, response);
		}
	}

}
