package dev.sgp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.Collaborateur;
import dev.sgp.service.CollaborateurService;
import dev.sgp.util.Constantes;

/**
 * @author Antony
 *
 */
public class EditerCollaborateurController extends HttpServlet {
	private CollaborateurService collabService = Constantes.COLLAB_SERVICE;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// recupere la valeur d'un parametre dont le nom est matricule
		String matricule = req.getParameter("matricule");

		Collaborateur collaborateur = null;
		for (Collaborateur c : collabService.listerCollaborateurs()) {
			if (c.getMatricule().equals(matricule)) {
				collaborateur = c;
			}
		}
		if (collaborateur == null) {
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.sendRedirect("lister");
		} else {
			resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			req.setAttribute("collaborateur", collaborateur);
			req.getRequestDispatcher("/WEB-INF/views/collab/editerCollaborateur.jsp").forward(req, resp);
		}

	}
}
