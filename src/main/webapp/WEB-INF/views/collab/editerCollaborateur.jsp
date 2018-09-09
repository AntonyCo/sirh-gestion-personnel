<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>SGP - App</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.css">
	</head>
	<body>
		<% Collaborateur c = (Collaborateur) request.getAttribute("collaborateur"); %>
		<div class="container">
			<div class="row">
				<img class="col-lg-2" src="<%=request.getContextPath()+"/"+c.getPhoto()%>" style="width: 250px; margin: 2px 0 0 5px;" alt="Profil" />
				<div class="col-lg-9">
					<div class="row">
						<h1 class="col-lg-8"><%=c.getNom().toUpperCase()+" "+c.getPrenom()+" - "+c.getMatricule()%></h1>
						<label class="checkbox col-lg-4" style="margin-top:30px"><input type="checkbox" name="desactive" value="">Désactiver</label>
					</div>
					<form method="post" action="nouveau">	   
	       	        	<div class="form-group row">
			                <label for="civilite" class="col-lg-2 col-form-label">Civilité</label>
							<div class="col-lg-8">
								<select name="civilite" class="form-control">
									<option value="Mr.">Mr.</option>
									<option value="Mme.">Mme.</option>
									<option value="Autre">Autre</option>
								</select>
							</div>
						</div>
						 
					    <div class="form-group row">
			                <label for="nom" class="col-lg-2 col-form-label">Nom</label>
			                <div class="col-lg-8">
			                	<input class="form-control" type="text" id="nom" name="nom" value=""/>
		                	</div>
			            </div>
				
						<div class="form-group row">
			                <label for="prenom" class="col-lg-2 col-form-label">Prenom</label>
			                <div class="col-lg-8">
			                	<input class="form-control" type="text" id="prenom" name="prenom" value="" size="20" maxlength="20" />
		                	</div>
		                </div>
		                
		                <div class="form-group row">
			                <label for="date_naissance" class="col-lg-2 col-form-label">Date de naissance</label>
			                <div class="col-lg-8">
			                	<input class="form-control" type="date" id="date_naissance" name="date_naissance" maxlength="10" placeholder="01/01/1970" value="" size="20" maxlength="20" />
		                	</div>
		                </div>
		                
		                <div class="form-group row">
			                <label for="adresse" class="col-lg-2 col-form-label">Adresse</label>
			                <div class="col-lg-8">
			                	<textarea class="form-control" name="adresse" id="adresse" style="resize:none"rows="5" cols="20"></textarea>  
		                	</div>
		                </div>
		                
		                <div class="form-group row">
			                <label for="num_securite_sociale" class="col-lg-2 col-form-label">Numéro de sécurité sociale</label>
			                <div class="col-lg-8">
			                	<input class="form-control" type="text" id="num_securite_sociale" name="num_securite_sociale" value="" size="20" maxlength="20" />
		                	</div>
		                </div>
		                
	       	        	<div class="form-group row">
			                <label for="departement" class="col-lg-2 col-form-label">Département</label>
							<div class="col-lg-8">
								<select name="departement" class="form-control">
	
								</select>
							</div>
						</div>
						
		                <div class="form-group row">
			                <label for="poste" class="col-lg-2 col-form-label">Intitulé poste</label>
			                <div class="col-lg-8">
			                	<input class="form-control" type="text" id="poste" name="poste" value="" size="20" maxlength="20" />
		                	</div>
		                </div>
		                
		                <div class="form-group row">
		                	<div  class="col-lg-2 col-form-label"></div>
			                <div class="col-lg-8">
			                	<input type="submit" value="Créer" style="width:120px" class="btn btn-primary pull-right"/>
			                </div>
		                </div>
					</form>
				</div>
			</div>
		</div>
	</body>
</html>