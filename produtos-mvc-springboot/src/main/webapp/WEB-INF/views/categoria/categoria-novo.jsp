<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
		<meta charset="ISO-8859-1">
		<title>categorias</title>

		<!-- ATALHO PARA TRAZER A URL DE CONTEXTO DO PROJETO -->
		<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
		
		<!-- ATALHOS PARA OS ARQUIVOS ESTATICOS DO WEBJAR -->
		<spring:url value="${contextPath}/webjars/bootstrap/5.2.3/css" var="css" />
		<spring:url value="${contextPath}/webjars/jquery" var="jquery" />
		<spring:url value="${contextPath}/webjars/bootstrap/js" var="js" />

		<!-- APONTAMENTO PARA O CSS DO BOOTSTRAP -->
		<link href="${css}/bootstrap.min.css" rel="stylesheet">
		
		<!-- CSS PARA NOSAS CUSTOMIZACOES -->
		<link href="/css/style.css" rel="stylesheet">

		
		<!-- LINKS PARA USAR FONTE CUSTOMIZAVEL DO GOOGLE FONTES -->
		<link rel="preconnect" href="https://fonts.googleapis.com">
		<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
		<link href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap" rel="stylesheet">
				
	</head>
	<body>
		<header>
			<nav class="navbar navbar-dark bg-dark">
			  <div class="container-fluid">
			    <a class="navbar-brand fonte-titulo" href="${contextPath}/categorias">Categoria</a>
			  </div>
			</nav>
		</header>
		
		<main>
		<section id="formulario" class="bg-light pb-5">
		    <div class="container">
		        <div class="row">
		            <div class="col-lg-12">
		                <div class="well">
							
							<h2 class="fonte-titulo texto-cor-especial">Categoria</h2>
							
							<form:form modelAttribute="categoriaModel" action="${contextPath}/categorias" method="post">
							
								<spring:hasBindErrors name="categoriaModel">
									<div class="alert alert-danger" role="alert">
										<form:errors path="*" class="has-error" />
									</div> 
								</spring:hasBindErrors>
								
								<div class="form-group">
									<label class="control-label" for="nome">Nome:</label>
									<form:input type="text" path="nome" name="nome" id="nome" value="" class="form-control" maxlength="50" size="50" />
									<font color="red"><form:errors path="nomeCategoria" /></font><br/>
		                        </div>

								
								<a class="btn btn-secondary btn-sm" href="${contextPath}/categorias">Cancelar</a>
								<button type="submit" class="btn btn-primary btn-sm">Gravar</button>
							</form:form>
		                </div>
		            </div>
		        </div>
		    </div>
		    </section>
		</main>
		
		<script src="${jquery}/jquery.min.js"></script>
		<script src="${js}/bootstrap.min.js"></script>

</body>
</html>