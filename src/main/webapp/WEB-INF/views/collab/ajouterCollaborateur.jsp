<%@page import="java.util.List"%>
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
			
			 <form method="post" action="nouveau">
	                <label for="nom">Nom</label>
	                <input type="text" id="nom" name="nom" value="" size="20" maxlength="20" />
	                <br />
	
	                <label for="nom">Prenom</label>
	                <input type="text" id="prenom" name="prenom" value="" size="20" maxlength="20" />
	                <br />
	                
	                <label for="nom">Date de naissance</label>
	                <input type="text" id="date_naissance" name="date_naissance" value="" size="20" maxlength="20" />
	                <br />
	                
	                <label for="nom">Adresse</label>
	                <textarea name="adresse" id="adresse" rows="5" cols="20">
	      			</textarea>  
	                <br />
	                
	                <label for="nom">Numéro de sécurité sociale</label>
	                <input type="text" id="num_securite_sociale" name="num_securite_sociale" value="" size="20" maxlength="20" />
	                <br />
	                
	                <input type="submit" value="Créer"/>
			</form>
		</div>
	</body>
</html>