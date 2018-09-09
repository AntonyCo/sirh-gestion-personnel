<%@page import="java.util.List"%>
<%@page import="dev.sgp.entite.Collaborateur"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>SGP - App</title>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/bootstrap-3.3.7-dist/css/bootstrap.css">
	</head>
	<body>
		<h1>Les collaborateurs</h1>
		<div class="container-fluid">
			<div class="row">
					<%
						List<Collaborateur> liste = (List<Collaborateur>) request.getAttribute("liste_collaborateurs");
						if(liste != null){
							for (Collaborateur c : liste) {
					%>
					<div class="col-lg-3"  style="margin-bottom:5px">
						<div class="panel-user" style="background-color:rgb(0,0,0,0.2);">
							<div style="background-color:rgb(0,0,0,0.2); padding: 2px 0 1px 10px;">
								<p><%=c.getNom().toUpperCase()+" "+c.getPrenom()%></p>
							</div>
							<div class="row">
								<img class="col-lg-4" src="<%=getServletContext().getContextPath()%>/photo_defaut.png" style="width:100px; margin: 2px 0 0 5px;" alt="Profil"/>
								<div class="col-lg-4">
									<p style="margin-bottom:0px;">Fonction</p>
									<p style="margin-bottom:0px;">Département</p>
									<p style="margin-bottom:0px;">Email</p>
									<p style="margin-bottom:0px;">Téléphone</p>
								</div>
								<div class="col-lg-4">
									<p style="margin-bottom:0px;"><%=c.getMatricule() %></p>
									<p style="margin-bottom:0px;"><%=c.getMatricule() %></p>
									<p style="margin-bottom:0px;"><%=c.getEmailPro() %></p>
									<p style="margin-bottom:0px;"><%=c.getMatricule() %></p>
								</div>
							</div>
							<button style="text-align:right" type="button" class="btn btn-info pull-right">Editer</button>
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