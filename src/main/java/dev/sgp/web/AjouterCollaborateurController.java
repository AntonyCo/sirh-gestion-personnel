package dev.sgp.web;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dev.sgp.entite.Collaborateur;
import dev.sgp.service.CollaborateurService;
import dev.sgp.util.Constantes;
import dev.sgp.util.Outils;

public class AjouterCollaborateurController extends HttpServlet {
	private CollaborateurService collabService = Constantes.COLLAB_SERVICE;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
		req.setAttribute("listeNoms", Arrays.asList("Robert", "Jean", "Hugues"));
		req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp")
		.forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws
	ServletException, IOException {
		String nom = req.getParameter("nom");
		String prenom = req.getParameter("prenom");
		String dateNaissance = req.getParameter("date_naissance");
		String adresse = req.getParameter("adresse").trim();
		String numeroSecuriteSociale = req.getParameter("numero_securite_sociale");
		
		StringBuilder chaineErreur = new StringBuilder();
		boolean estErreur = false;
		
		if(nom == null || nom.trim().equals("")){
			chaineErreur.append("Erreur sur le nom !").append(System.getProperty("line.separator"));
			estErreur = true;
		}
		if(prenom == null || prenom.trim().equals("")){
			chaineErreur.append("Erreur sur le prenom !").append(System.getProperty("line.separator"));
			estErreur = true;
		}
		if(dateNaissance == null || dateNaissance.trim().equals("")){
			chaineErreur.append("Erreur sur la date de naissance !").append(System.getProperty("line.separator"));
			estErreur = true;
		}
		if(adresse == null || adresse.trim().equals("")){
			chaineErreur.append("Erreur sur l'adresse !").append(System.getProperty("line.separator"));
			estErreur = true;
		}
		if(numeroSecuriteSociale == null || numeroSecuriteSociale.trim().equals("") || numeroSecuriteSociale.length() != 15){
			chaineErreur.append("Erreur sur le numéro de sécurité sociale !").append(System.getProperty("line.separator"));
			estErreur = true;
		}
		
		if(estErreur == false){
			Collaborateur collaborateur = new Collaborateur();
			String matricule = "M"+Collaborateur.getCpt();
			Collaborateur.setCpt(Collaborateur.getCpt() + 1);
			ZonedDateTime dateHeureCreation = ZonedDateTime.now();
			String suffixe = Outils.getApplicationPropertiesParam("suffixe", "src/main/resources/application.properties");
			String emailPro = nom+"."+prenom+suffixe;
			
			collaborateur.setNom(nom);
			collaborateur.setPrenom(prenom);
			collaborateur.setDateNaissance(LocalDate.parse(dateNaissance));
			collaborateur.setAdresse(adresse);
			collaborateur.setNumeroSecuriteSociale(numeroSecuriteSociale);
			collaborateur.setDateHeureCreation(dateHeureCreation);
			collaborateur.setEmailPro(emailPro);
			collaborateur.setMatricule(matricule);
			collaborateur.setActif(true);
			
			collabService.sauvegarderCollaborateur(collaborateur);
			req.getRequestDispatcher("/WEB-INF/views/collab/listerCollaborateurs.jsp")
			.forward(req, resp);
		}else{
			resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			req.setAttribute("chaine_erreur", chaineErreur);
			req.getRequestDispatcher("/WEB-INF/views/collab/ajouterCollaborateur.jsp")
			.forward(req, resp);
		}
	}
		
}
/*
En cas d’erreurs de saisie, toutes les erreurs sont indiquées avec un code de reponse 400.
Lorsque la sauvegarde a eu lieu, l’utilisateur est redirigé vers la liste des collaborateurs.
Une photo fictive est affectée par défaut.*/