<%@page import="java.util.List"%>
<%@page import="dev.sgp.service.DepartementService"%>
<%@page import="dev.sgp.entite.Departement"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>SGP - App</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.css">
	</head>
	<body>
	
		<div class="container">
			 <h1>Ajouter un collaborateur</h1>
			 <br/><br/>
			 <form method="post" action="nouveau">	   
       	        	<div class="form-group row">
		                <label for="civilite" class="col-lg-2 col-form-label">Civilité</label>
						<div class="col-lg-5">
							<select name="civilite" class="form-control">
								<option value="Mr.">Mr.</option>
								<option value="Mme.">Mme.</option>
								<option value="Autre">Autre</option>
							</select>
						</div>
					</div>
					 
				    <div class="form-group row">
		                <label for="nom" class="col-lg-2 col-form-label">Nom</label>
		                <div class="col-lg-5">
		                	<input class="form-control" type="text" id="nom" name="nom" value=""/>
	                	</div>
		            </div>
			
					<div class="form-group row">
		                <label for="prenom" class="col-lg-2 col-form-label">Prenom</label>
		                <div class="col-lg-5">
		                	<input class="form-control" type="text" id="prenom" name="prenom" value="" size="20" maxlength="20" />
	                	</div>
	                </div>
	                
	                <div class="form-group row">
		                <label for="date_naissance" class="col-lg-2 col-form-label">Date de naissance</label>
		                <div class="col-lg-5">
		                	<input class="form-control" type="date" id="date_naissance" name="date_naissance" maxlength="10" placeholder="01/01/1970" value="" size="20" maxlength="20" />
	                	</div>
	                </div>
	                
	                <div class="form-group row">
		                <label for="adresse" class="col-lg-2 col-form-label">Adresse</label>
		                <div class="col-lg-5">
		                	<textarea class="form-control" name="adresse" id="adresse" style="resize:none"rows="5" cols="20"></textarea>  
	                	</div>
	                </div>
	                
	                <div class="form-group row">
		                <label for="num_securite_sociale" class="col-lg-2 col-form-label">Numéro de sécurité sociale</label>
		                <div class="col-lg-5">
		                	<input class="form-control" type="text" id="num_securite_sociale" name="num_securite_sociale" value="" size="20" maxlength="20" />
	                	</div>
	                </div>
	                
       	        	<div class="form-group row">
		                <label for="departement" class="col-lg-2 col-form-label">Département</label>
						<div class="col-lg-5">
							<select name="departement" class="form-control">
								<%DepartementService departements = new DepartementService(); 
			                	 	for(Departement d : departements.getListeDepartement()){
			                	 %>
								<option value="<%=d.getId() %>"><%=d.getNom() %></option>
								<%
					        	 	}
								 %>
							</select>
						</div>
					</div>
					
	                <div class="form-group row">
		                <label for="poste" class="col-lg-2 col-form-label">Intitulé poste</label>
		                <div class="col-lg-5">
		                	<input class="form-control" type="text" id="poste" name="poste" value="" size="20" maxlength="20" />
	                	</div>
	                </div>
	                
	                <div class="form-group row">
	                	<div  class="col-lg-2 col-form-label"></div>
		                <div class="col-lg-5">
		                	<input type="submit" value="Créer" style="width:120px" class="btn btn-primary pull-right"/>
		                </div>
	                </div>
	                
			</form>
			
			<div>
				<%
				List<String> listeErreur = (List<String>) request.getAttribute("liste_erreur");
				if(listeErreur != null){
					for(String s : listeErreur){
				%>
					<p style="color:red"><%=s%></p>
				<%
					}
				}
				%>
			</div>
		</div>
	</body>
</html>