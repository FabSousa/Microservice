<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- TAGS NECESSARIAS PARA UTLIZARMOS O JSTL E OS RECURSOS DO SPRING -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Categoria</title>

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
			<%@include file="../navbar/navbar.html" %>
		</header>
		
		<main  >
		
			<section id="detalhes" class="bg-light pb-5">
			<div class="container">
	        <div class="row">
	            <div class="col-lg-12">
					<form>
						<h2 class="fonte-titulo texto-cor-especial">Categoria</h2>
						<div class="form-group">
							<p class="fs-6 m-0"><b>Nome:</b> ${categoria.nomeCategoria}</p>
                        </div>
						<hr>
						<div class="d-grid gap-2 col-6">
							<a class="btn btn-primary btn-sm" href="${contextPath}/categoria">Voltar</a>
						</div>
                    </form>
	            </div>
	        </div>
	        </div>
	        </section>
		</main>
		
		<!-- APONTAMENTO PARA AS BIBLIOTECAS E JAVASCRIPT DO JQUERY E BOOTSTRAP -->
		<script src="${jquery}/jquery.min.js"></script>
		<script src="${js}/bootstrap.min.js"></script>
	</body>
</html>