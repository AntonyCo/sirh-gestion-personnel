package dev.sgp.service;

import java.util.ArrayList;
import java.util.List;

import dev.sgp.entite.Departement;

/**
 * @author Antony
 *
 */
public class DepartementService {

	List<Departement> listeDepartement;

	public DepartementService() {
		listeDepartement = new ArrayList<>();

		Departement comptabilite = new Departement();
		comptabilite.setId(0);
		comptabilite.setNom("Comptabilité");

		Departement ressourcesHumaires = new Departement();
		ressourcesHumaires.setId(1);
		ressourcesHumaires.setNom("Ressources Humaines");

		Departement informatique = new Departement();
		informatique.setId(2);
		informatique.setNom("Informatique");

		Departement administratif = new Departement();
		administratif.setId(3);
		administratif.setNom("Administratif");

		listeDepartement.add(comptabilite);
		listeDepartement.add(ressourcesHumaires);
		listeDepartement.add(informatique);
		listeDepartement.add(administratif);
	}

	public List<Departement> getListeDepartement() {
		return listeDepartement;
	}

	public void setListeDepartement(List<Departement> listeDepartement) {
		this.listeDepartement = listeDepartement;
	}

}