<%@page import="dev.sgp.service.DepartementService"%>
<%@page import="dev.sgp.entite.Departement"%>
<%@page import="java.util.List"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SGP - App</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.css">
</head>
<body>
	<div class="container-fluid">
		<h1>Les collaborateurs</h1>
		<form method="post" action="">
			<div class="form-group row">
				<label for="nom" class="col-lg-3 col-form-label">Rechercher
					un nom ou un prénom qui commence par:</label>
				<div class="col-lg-2">
					<input class="form-control" type="text" id="nom" name="nom"
						value="" />
				</div>

				<div class="col-lg-1">
					<input type="submit" value="Rechercher" style="width: 120px"
						class="btn btn-primary pull-right" />
				</div>
				<label class="checkbox-inline"><input type="checkbox"
					value="">Voir les collaborateurs désactivés</label>
			</div>

			<div class="form-group row">
				<label for="nom" class="col-lg-3 col-form-label">Filtrer par
					département :</label>
				<div class="col-lg-2">
					<select name="cars" class="form-control">
						<%
							DepartementService departements = new DepartementService();
							for (Departement d : departements.getListeDepartement()) {
						%>
						<option value="<%=d.getId()%>"><%=d.getNom()%></option>
						<%
							}
						%>
					</select>
				</div>
			</div>
		</form>
	</div>
	<div class="container-fluid">
		<div class="row">
			<%
				List<Collaborateur> liste = (List<Collaborateur>) request.getAttribute("liste_collaborateurs");
				if (liste != null) {
					for (Collaborateur c : liste) {
			%>
			<div class="col-lg-4" style="margin-bottom: 20px">
				<div class="clearfix"
					style="background-color: rgb(0, 0, 0, 0.05); border-radius: 5px; box-shadow: 0px 1px 4px;">
					<div
						style="background-color: rgb(0, 0, 0, 0.1); padding: 2px 0 1px 10px; box-shadow: 0px 1px 4px">
						<p><%=c.getCivilite()+" "+c.getNom().toUpperCase() + " " + c.getPrenom()%></p>
					</div>
					<div class="row" style="margin-top: 5px;">
						<img class="col-lg-4"
							src="<%=getServletContext().getContextPath()%>/photo_defaut.png"
							style="width: 100px; margin: 2px 0 0 5px;" alt="Profil" />
						<div class="col-lg-4">
							<p style="margin-bottom: 0px;">Fonction</p>
							<p style="margin-bottom: 0px;">Département</p>
							<p style="margin-bottom: 0px;">Email</p>
							<p style="margin-bottom: 0px;">Matricule</p>
						</div>
						<div class="col-lg-4">
							<p style="margin-bottom: 0px;"><%=c.getIntitulePoste()%></p>
							<p style="margin-bottom: 0px;"><%=c.getDepartement().getNom()%></p>
							<p style="margin-bottom: 0px;"><%=c.getEmailPro()%></p>
							<p style="margin-bottom: 0px;"><%=c.getMatricule()%></p>
						</div>
					</div>
					<a  style="margin: 0 30px 5px 0" href="/editer?<%=""+c.getMatricule() %>" class="btn btn-info pull-right" role="button" aria-pressed="true">Editer</a>
				</div>
			</div>
			<%
				}
				}
			%>
		</div>
	</div>
</body>
</html>