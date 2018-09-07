package dev.sgp.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditerCollaborateurController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
		// recupere la valeur d'un parametre dont le nom est matricule
		String matricule = req.getParameter("matricule");
		String titre = req.getParameter("titre");
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");

		String texteRetour = "";
		boolean estEnErreur = false;
		resp.setContentType("text/html");
		
		if(matricule == null ){
			texteRetour = texteRetour.concat("matricule ");
			estEnErreur = true;
		}
		if(titre == null){
			texteRetour = texteRetour.concat("titre ");
			estEnErreur = true;
		}
			
		if(nom == null){
			texteRetour = texteRetour.concat("nom ");
			estEnErreur = true;
		}
			
		if(prenom == null){
			texteRetour = texteRetour.concat("prenom");
			estEnErreur = true;
		}
			
		if(estEnErreur == false){
			resp.setStatus(HttpServletResponse.SC_CREATED);
			resp.getWriter().write("<h1>Creation d'un collaborateur</h1>"
				+ "<p>"
				+ "Matricule= "+matricule
				+ ", titre= "+titre
				+ ", nom= "+nom
				+ ", prenom= "+prenom
				+ "</p>");
		}else{
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			resp.getWriter().write("<h1>Edition de collaborateur</h1>"
				+ "<p>"
				+ "Les paramètres suivants sont incorrects: "+texteRetour
				+ "</p>");
		}
			
	}
}
