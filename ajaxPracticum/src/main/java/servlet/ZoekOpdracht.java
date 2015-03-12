package servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistency.DBAccess;
import bean.Opdracht;

@WebServlet("/ZoekOpdracht")
public class ZoekOpdracht extends HttpServlet {

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String klant = request.getParameter("klant");
		klant = klant == null ? "" : klant;
		String beheerder = request.getParameter("beheerder");
		beheerder = beheerder == null ? "" : beheerder;

		DBAccess dbAccess = DBAccess.getInstance();
		ArrayList<Opdracht> opdrachten = null;
		String errorMessage = "";
		try {
			opdrachten = dbAccess.zoekOpdrachten(klant, beheerder);
		} catch (Exception ex) {
			errorMessage += ex.getMessage();
		}

		if (!errorMessage.equals("")) {
			response.setStatus(521);
			request.setAttribute("errorMessage", String.format("521 - Server Error - %s", errorMessage));
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/Error.jsp");
			rd.include(request, response);
		} else {
			request.setAttribute("opdrachten", opdrachten);
			response.setHeader("Cache-Control", "no-cache");
			response.setHeader("Pragma", "no-cache");

			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/GevondenOpdrachtenXML.jsp");
			rd.include(request, response);
		}

	}
}
