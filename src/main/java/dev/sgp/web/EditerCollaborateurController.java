package dev.sgp.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.Collaborateur;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
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
	DepartementService departements = new DepartementService();
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
	    req.setAttribute("liste_departements", departements.getListeDepartement());
	    req.getRequestDispatcher("/WEB-INF/views/collab/editerCollaborateur.jsp").forward(req, resp);
	}

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String civilite = req.getParameter("civilite");
	String nom = req.getParameter("nom");
	String prenom = req.getParameter("prenom");
	String dateNaissance = req.getParameter("date_naissance");
	String adresse = req.getParameter("adresse").trim();
	String numeroSecuriteSociale = req.getParameter("num_securite_sociale");
	String telephone = req.getParameter("telephone");
	String intitulePoste = req.getParameter("poste");
	Integer numDepartement = Integer.valueOf(req.getParameter("departement"));

	String banque = req.getParameter("banque");
	String bic = req.getParameter("bic");
	String iban = req.getParameter("iban");

	String matricule = req.getParameter("matricule");
	boolean aDesactiver = req.getParameter("desactive") != null;
	DepartementService departements = new DepartementService();
	// On récupère le collaborateur via son matricule
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
	    // On test chaque champs, s'il est différent on le change
	    if (collaborateur.getCivilite().equals(civilite) == false) {
		collaborateur.setCivilite(civilite);
	    }
	    if (collaborateur.getAdresse().equals(adresse) == false) {
		collaborateur.setAdresse(adresse);
	    }
	    if (collaborateur.getTelephone().equals(telephone) == false) {
		collaborateur.setTelephone(telephone);
	    }
	    if (collaborateur.getDepartement().getId() != numDepartement) {
		collaborateur.setDepartement(departements.getListeDepartement().get(numDepartement));
	    }
	    if (collaborateur.getIntitulePoste().equals(intitulePoste) == false) {
		collaborateur.setIntitulePoste(intitulePoste);
	    }
	    if (collaborateur.getBanque().equals(banque) == false) {
		collaborateur.setBanque(banque);
	    }
	    if (collaborateur.getBic().equals(bic) == false) {
		collaborateur.setBic(bic);
	    }
	    if (collaborateur.getIban().equals(iban) == false) {
		collaborateur.setIban(iban);
	    }
	    if (aDesactiver) {
		collaborateur.setActif(false);
	    } else {
		collaborateur.setActif(true);
	    }
	    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	    req.setAttribute("collaborateur", collaborateur);
	    req.setAttribute("liste_departements", departements.getListeDepartement());
	    resp.sendRedirect("lister");
	}
    }
}
