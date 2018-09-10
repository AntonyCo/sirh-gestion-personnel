<%@ page language="java" pageEncoding="UTF-8"%>
<%@page import="dev.sgp.service.DepartementService"%>
<%@page import="dev.sgp.entite.Departement"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SGP - App</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
	<%
	    Collaborateur c = (Collaborateur) request.getAttribute("collaborateur");
	    request.setAttribute("test", "test");
	%>
	<div class="container">
		<div class="row">
			<img class="col-lg-2"
				src="<%=request.getContextPath() + "/" + c.getPhoto()%>"
				style="width: 250px; margin: 2px 0 0 5px;" alt="Profil" />
			<div class="col-lg-9">
				<form method="post" action="editer">
					<div class="row">
						<h1 class="col-lg-8"><%=c.getNom().toUpperCase() + " " + c.getPrenom() + " - " + c.getMatricule()%></h1>

						<fieldset>
							<label class="checkbox col-lg-4" style="margin-top: 30px"><input
								<%=((c.isActif() ? "" : "checked")) %> type="checkbox" name="desactive" value="">Désactiver</label>
						</fieldset>
					</div>

					<!-- PANEL IDENTITE -->
					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href=".panel-identite">Identité</a>
							</h4>
						</div>
					</div>
					<div class="panel-identite panel-collapse collapse">
						<div class="panel-body">
							<fieldset>
								<div class="form-group row">
									<label for="civilite" class="col-lg-2 col-form-label">Civilité</label>
									<div class="col-lg-8">
										<select name="civilite" class="form-control">
											<option
												<%=(c.getCivilite().equals("Mr.") ? "selected" : "")%>
												value="Mr.">Mr.</option>
											<option
												<%=(c.getCivilite().equals("Mme.") ? "selected" : "")%>
												value="Mme.">Mme.</option>
											<option
												<%=(c.getCivilite().equals("Autre") ? "selected" : "")%>
												value="Autre">Autre</option>
										</select>
									</div>
								</div>

								<div class="form-group row">
									<label for="nom" class="col-lg-2 col-form-label">Nom</label>
									<div class="col-lg-8">
										<input class="form-control" type="text" id="nom" name="nom"
											readonly value="<%=c.getNom()%>" />
									</div>
								</div>

								<div class="form-group row">
									<label for="prenom" class="col-lg-2 col-form-label">Prenom</label>
									<div class="col-lg-8">
										<input class="form-control" type="text" id="prenom" readonly
											name="prenom" value="<%=c.getPrenom()%>" size="20"
											maxlength="20" />
									</div>
								</div>

								<div class="form-group row">
									<label for="date_naissance" class="col-lg-2 col-form-label">Date
										de naissance</label>
									<div class="col-lg-8">
										<input class="form-control" type="date" id="date_naissance"
											readonly name="date_naissance" maxlength="10"
											placeholder="01/01/1970"
											value="<%=c.getDateNaissance().toString()%>" size="20"
											maxlength="20" />
									</div>
								</div>

								<div class="form-group row">
									<label for="adresse" class="col-lg-2 col-form-label">Adresse</label>
									<div class="col-lg-8">
										<textarea class="form-control" name="adresse" id="adresse"
											required style="resize: none" rows="5" cols="20"><%=c.getAdresse()%></textarea>
									</div>
								</div>

								<div class="form-group row">
									<label for="num_securite_sociale"
										class="col-lg-2 col-form-label">Numéro de sécurité
										sociale</label>
									<div class="col-lg-8">
										<input class="form-control" type="text" readonly
											id="num_securite_sociale" name="num_securite_sociale"
											value="<%=c.getNumeroSecuriteSociale()%>" size="20"
											maxlength="20" />
									</div>
								</div>

								<div class="form-group row">
									<label for="telephone" class="col-lg-2 col-form-label">Téléphone</label>
									<div class="col-lg-8">
										<input class="form-control" type="text" id="telephone"
											name="telephone" value="" size="20" maxlength="20" />
									</div>
								</div>
							</fieldset>
						</div>
					</div>

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href=".panel-poste">Poste</a>
							</h4>
						</div>
					</div>
					<div id="collapse2" class="panel-poste panel-collapse collapse">
						<div class="panel-body">
							<fieldset>
								<div class="form-group row">
									<label for="departement" class="col-lg-2 col-form-label">Département</label>
									<div class="col-lg-8">
										<select name="departement" class="form-control">
											<%
											    DepartementService departements = new DepartementService();
											    for (Departement d : departements.getListeDepartement()) {
													if (c.getDepartement().getId() == d.getId()) {
											%>
											<option selected="selected" value="<%=d.getId()%>"><%=d.getNom()%></option>
											<%
											    } else {
											%>

											<option value="<%=d.getId()%>"><%=d.getNom()%></option>
											<%
											    }
											    }
											%>
										</select>
									</div>
								</div>

								<div class="form-group row">
									<label for="poste" class="col-lg-2 col-form-label">Intitulé
										poste</label>
									<div class="col-lg-8">
										<input class="form-control" type="text" id="poste"
											name="poste" value="<%=c.getIntitulePoste()%>" size="20"
											maxlength="20" />
									</div>
								</div>
							</fieldset>
						</div>
					</div>


					<div class="panel panel-default">
						<div class="panel-heading">
							<h4 class="panel-title">
								<a data-toggle="collapse" href=".panel-banque">Coordonnées
									bancaires</a>
							</h4>
						</div>
						<div id="collapse2" class="panel-banque panel-collapse collapse">
							<div class="panel-body">
								<fieldset>

									<div class="form-group row">
										<label for="banque" class="col-lg-2 col-form-label">Banque</label>
										<div class="col-lg-8">
											<input class="form-control" type="text" id="banque"
												name="banque"
												value="<%=((c.getBanque() == null) ? "" : c.getBanque())%>"
												size="20" maxlength="20" />
										</div>
									</div>
									<div class="form-group row">
										<label for="bic" class="col-lg-2 col-form-label">Bic</label>
										<div class="col-lg-8">
											<input class="form-control" type="text" id="bic" name="bic"
												value="<%=((c.getBic() == null) ? "" : c.getBic())%>"
												size="20" maxlength="20" />
										</div>
									</div>
									<div class="form-group row">
										<label for="iban" class="col-lg-2 col-form-label">Iban</label>
										<div class="col-lg-8">
											<input class="form-control" type="text" id="iban" name="iban"
												value="<%=((c.getIban() == null) ? "" : c.getIban())%>"
												size="20" maxlength="20" />
										</div>
									</div>
								</fieldset>
							</div>
						</div>
					</div>
					<input type="hidden" name="matricule" value="<%=c.getMatricule()%>">
					<div class="form-group row">
						<div class="col-lg-12">
							<input type="submit" value="Sauvegarder" style="width: 120px"
								class="btn btn-primary pull-right" />
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>