<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        <%@ taglib prefix = "c" uri ="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Aplicacion gestion de usuarios</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
</head>
<body>
	<header>
				<nav class="navbar navbar-expand-mb navbar-dark" style="background-color:tomato"> 
					<div>
						<a href="#" class="navbar-brand"> Gestion de Usuarios</a>											
					</div>
					
					<ul class="navbar-nav">
						<li><a href="<%= request.getContextPath() %>/list" class="nav-link">Usuarios</a></li>
					</ul>
				</nav>
	</header>
	<br>
	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">
				
				<c:if test = "${usuario != null}">
						<form action="update" method="post">
					</c:if>
					<c:if test= "$usuario == null">
						<form action="insert" method = "post">
					</c:if>
					
					<caption>
					
					<h2>
						<c:if test = "${usuario !=null }">
						Editar Usuario
						</c:if>	
						<c:if test= "$usuario == null">
						Agregar Nuevo Usuario
					</c:if>					
					</h2>
					</caption>
					
					<c:if test = "${usuario !=null }">
						<input type ="hidden" name = "id" value"<c:out value = '$(usuario.id)' />"/>
					</c:if>
					
					<fieldset class="form-group">
						<label>Nombre de Usuario</label><input type="text" value"<c:out value='$(usuario.nombre)'/>" class = "form-control" name="nombre" requerid="required"/>
					</fieldset>
					<fieldset class="form-group">
						<label>Email de Usuario</label><input type="text" value"<c:out value='$(usuario.email)'/>" class = "form-control" name="email"/>
					</fieldset>
					<fieldset class="form-group">
						<label>Pais de Usuario</label><input type="text" value"<c:out value='$(usuario.pais)'/>" class = "form-control" name="pais"/>
					</fieldset>
					
					<button type= "submit" class="btn btn-success">Guardar</button>
						
			</div>
		</div>
	
	</div>
	
	
	
</body>
</html>