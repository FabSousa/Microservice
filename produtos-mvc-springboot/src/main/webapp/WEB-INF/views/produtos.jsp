<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- TAGS NECESSARIAS PARA UTLIZARMOS O JSTL E OS RECURSOS DO SPRING -->
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Produtos</title>
		
		<!-- ATALHO PARA TRAZER A URL DE CONTEXTO DO PROJETO -->
		<c:set value="${pageContext.request.contextPath}" var="contextPath"/>
		
		<!-- ATALHOS PARA OS ARQUIVOS ESTATICOS DO WEBJAR -->
		<spring:url value="${contextPath}/webjars/bootstrap/css" var="css" />
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
			    <a class="navbar-brand fonte-titulo" href="${contextPath}/produtos">Produtos</a>
			  </div>
			</nav>
		</header>
		
		<main class="container" >
			<section id="titulo">
		    	<div> 
        			<div class="row">
            			<div class="col-md-12">
							<h2 class="fonte-titulo texto-cor-especial">Produtos</h2>
							<a class="btn btn-secondary" href="${contextPath}/produto/new">Novo Produto</a>
							<c:if test = "${not empty message}">
								<h3 class="alert alert-warning"> ${message} </h3>
							</c:if>
						</div>
					</div>
				</div>
			</section>
			
			<section id="tabela">
			<table class="table table-striped table-hover">
			<thead>
			    <tr>
			      <th scope="col">Nome</th>
			      <th scope="col">Preço</th>
			      <th scope="col">Ações</th>
			    </tr>
			  </thead>
			  <tbody>
			  <c:forEach items="${produtos}" var="produto">
				    <tr>
				      <td>${produto.nome}</td>
				      <td>${produto.preco}</td>
				      <td>
				          <a href="${contextPath}/produto/${produto.id}"  class="btn btn-success btn-sm">Detalhes</a>
					      <a href="${contextPath}/produto/edit/${produto.id}"  class="btn btn-warning btn-sm">Editar</a>
					      <a href="${contextPath}/produto/delete/${produto.id}"  class="btn btn-danger btn-sm">Excluir</a>
					  </td>
				    </tr>
			    </c:forEach>
			  </tbody>
			</table>
			</section>
		</main>
		
		<!-- APONTAMENTO PARA AS BIBLIOTECAS E JAVASCRIPT DO JQUERY E BOOTSTRAP -->
		<script src="${jquery}/jquery.min.js"></script>
		<script src="${js}/bootstrap.min.js"></script>
	</body>
</html>