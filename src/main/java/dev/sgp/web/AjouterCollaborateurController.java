package dev.sgp.web;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.Collaborateur;
import dev.sgp.service.CollaborateurService;
import dev.sgp.service.DepartementService;
import dev.sgp.util.Constantes;
import dev.sgp.util.Outils;

/**
 * @author Antony
 *
 */
public class AjouterCollaborateurController extends HttpServlet {
    private CollaborateurService collabService = Constantes.COLLAB_SERVICE;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	req.setAttribute("listeNoms", Arrays.asList("Robert", "Jean", "Hugues"));
	DepartementService departements = new DepartementService();
	req.setAttribute("liste_departements", departements.getListeDepartement());
	req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String nom = req.getParameter("nom");
	String prenom = req.getParameter("prenom");
	String dateNaissance = req.getParameter("date_naissance");
	String adresse = req.getParameter("adresse").trim();
	String numeroSecuriteSociale = req.getParameter("num_securite_sociale");
	String intitulePoste = req.getParameter("poste");
	Integer numDepartement = Integer.valueOf(req.getParameter("departement"));
	String civilite = req.getParameter("civilite");
	List<String> listeErreur = new ArrayList<>();
	boolean estErreur = false;
	DepartementService departements = new DepartementService();

	if (nom == null || nom.trim().equals("")) {
	    listeErreur.add("Erreur sur le nom !");
	    estErreur = true;
	}
	if (prenom == null || prenom.trim().equals("")) {
	    listeErreur.add("Erreur sur le prenom !");
	    estErreur = true;
	}
	if (dateNaissance == null || dateNaissance.trim().equals("")) {
	    listeErreur.add("Erreur sur la date de naissance !");
	    estErreur = true;
	}
	if (adresse == null || adresse.trim().equals("")) {
	    listeErreur.add("Erreur sur l'adresse !");
	    estErreur = true;
	}
	if (intitulePoste == null || intitulePoste.trim().equals("")) {
	    listeErreur.add("Erreur sur l'intitulé du poste !");
	    estErreur = true;
	}
	if (numeroSecuriteSociale == null || numeroSecuriteSociale.trim().equals("")
		|| numeroSecuriteSociale.length() != 15) {
	    listeErreur.add("Erreur sur le numéro de sécurité sociale !");
	    estErreur = true;
	}

	if (estErreur) {
	    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
	    req.setAttribute("liste_erreur", listeErreur);
	    req.setAttribute("liste_departements", departements.getListeDepartement());
	    req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp").forward(req, resp);
	} else {
	    Collaborateur collaborateur = new Collaborateur();
	    String matricule = "M" + String.format("%03d", Collaborateur.getCpt());
	    Collaborateur.setCpt(Collaborateur.getCpt() + 1);
	    ZonedDateTime dateHeureCreation = ZonedDateTime.now();
	    String suffixe = Outils.getApplicationPropertiesParam("suffixe",
		    "src/main/resources/application.properties");
	    String emailPro = nom.toLowerCase() + "." + prenom.toLowerCase() + "@" + suffixe;

	    collaborateur.setNom(nom);
	    collaborateur.setPrenom(prenom);
	    collaborateur.setDateNaissance(LocalDate.parse(dateNaissance, DateTimeFormatter.ISO_DATE));
	    collaborateur.setAdresse(adresse);
	    collaborateur.setNumeroSecuriteSociale(numeroSecuriteSociale);
	    collaborateur.setDateHeureCreation(dateHeureCreation);
	    collaborateur.setEmailPro(emailPro);
	    collaborateur.setMatricule(matricule);
	    collaborateur.setActif(true);
	    collaborateur.setIntitulePoste(intitulePoste);
	    collaborateur.setCivilite(civilite);
	    collaborateur.setPhoto(req.getContextPath() + "/" + "photo_defaut.png");
	    collaborateur.setTelephone("");
	    collaborateur.setBanque("");
	    collaborateur.setBic("");
	    collaborateur.setIban("");

	    collaborateur.setDepartement(departements.getListeDepartement().get(numDepartement));
	    collabService.sauvegarderCollaborateur(collaborateur);

	    resp.setStatus(HttpServletResponse.SC_ACCEPTED);
	    req.setAttribute("liste_collaborateurs", collabService.listerCollaborateurs());
	    req.setAttribute("liste_departements", departements.getListeDepartement());
	    resp.sendRedirect("lister");
	}
    }

}