package dev.sgp.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
public class ListerCollaborateursController extends HttpServlet {
    private CollaborateurService collabService = Constantes.COLLAB_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	List<Collaborateur> collaborateurs = collabService.listerCollaborateurs();
	List<Collaborateur> listeFiltreActif = new ArrayList<>();
	DepartementService departements = new DepartementService();
	// FILTRAGE DES COLLABORATEURS ACTIFS
	for (Collaborateur c : collaborateurs) {
	    if (c.isActif()) {
		listeFiltreActif.add(c);
	    }
	}
	req.setAttribute("liste_collaborateurs", listeFiltreActif);
	req.setAttribute("liste_departements", departements.getListeDepartement());
	req.getRequestDispatcher("/WEB-INF/views/collab/listerCollaborateurs.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String filtreDepartement = req.getParameter("departement");
	String filtreNom = req.getParameter("filtre_nom");
	boolean voirDesactive = req.getParameter("voir_desactive") != null;
	DepartementService departements = new DepartementService();
	List<Collaborateur> collaborateurs = collabService.listerCollaborateurs();
	List<Collaborateur> listeFiltreActif = new ArrayList<>();
	List<Collaborateur> listeFiltreDepartement = new ArrayList<>();
	List<Collaborateur> listeFiltre = new ArrayList<>();

	// FILTRAGE DES COLLABORATEURS ACTIFS
	if (voirDesactive) {
	    listeFiltreActif = collaborateurs;
	} else {
	    for (Collaborateur c : collaborateurs) {
		if (c.isActif()) {
		    listeFiltreActif.add(c);
		}
	    }
	}
	// FILTRAGE PAR DEPARTEMENT
	// Si la valeur est "tous" alors on prend l'ensemble des collaborateurs
	if (filtreDepartement.equals("tous")) {
	    listeFiltreDepartement = listeFiltreActif;
	} else {
	    // Sinon on filtre par numéro de département l'ensemble des
	    // collaborateurs
	    Integer idDepartement = Integer.valueOf(filtreDepartement);
	    for (Collaborateur c : listeFiltreActif) {
		if (c.getDepartement().getId() == idDepartement) {
		    listeFiltreDepartement.add(c);
		}
	    }
	}
	// FILTRAGE PAR NOM
	// Si le champs est vide on prend l'ensemble des collaborateurs de la
	// liste
	// filtrée par département
	if (filtreNom == null) {
	    listeFiltre = listeFiltreDepartement;
	} else {
	    // Sinon on filtre par nom ou prénom l'ensemble des collaborateurs
	    // de la liste
	    // filtrée par département
	    for (Collaborateur c : listeFiltreDepartement) {
		if (c.getNom().toLowerCase().startsWith(filtreNom.toLowerCase())
			|| c.getPrenom().toLowerCase().startsWith(filtreNom.toLowerCase())) {
		    listeFiltre.add(c);
		}
	    }
	}
	req.setAttribute("liste_collaborateurs", listeFiltre);
	req.setAttribute("liste_departements", departements.getListeDepartement());
	req.getRequestDispatcher("/WEB-INF/views/collab/listerCollaborateurs.jsp").forward(req, resp);
    }
}