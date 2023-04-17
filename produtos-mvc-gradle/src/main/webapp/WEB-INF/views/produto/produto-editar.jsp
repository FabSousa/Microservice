<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
		
		<!-- CSS PARA NOSSAS CUSTOMIZACOES -->
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
    
    <main class="container">
	
		<section id="formulario">
	        <!-- Call to Action Well -->
	        <div class="row">
	            <div class="col-lg-12">
	                <div class="well">
						
						<h2 class="fonte-titulo texto-cor-especial">Produto</h2>
						
						<form:form modelAttribute="produtoModel" action="${contextPath}/produto/${produtoModel.id}" method="post">
						
							<spring:hasBindErrors name="produtoModel">
								<div>
									<form:errors path="*" class="has-error"></form:errors>
								</div>
							</spring:hasBindErrors>
							
						    <div class="form-group">
								<form:input type="hidden" name="id" id="id" path="id" value="${produto.id}" />
	                        </div>
							
							<div class="form-group">
								<label class="control-label" for="nome">Nome:</label>
								<form:input type="text" name="nome" id="nome" path="nome" value="${produto.nome}" class="form-control" maxlength="50" size="50" oninvalid="this.setCustomValidity('Enter User Name Here')" oninput="setCustomValidity('')"/>
	                       		<font color="red"><form:errors path="nome"/></font>
	                        </div>
	                        <div class="form-group">
									<label class="control-label" for="idCategoria">Categoria:</label>
									 <form:select path="categoria.idCategoria">
		                             <form:options items="${categorias}" itemValue="idCategoria" itemLabel="nomeCategoria"></form:options>
		                             </form:select>
		                    </div>
		                    <div class="form-group">
									<label class="control-label" for="idMarca">Marcas:</label>
									 <form:select path="marca.idMarca">
		                             <form:options items="${marcas}" itemValue="idMarca" itemLabel="nomeMarca"></form:options>
		                             </form:select>
		                    </div>
	                        <div class="form-group">
								<label class="control-label" for="nome">SKU:</label>
								<form:input type="text" name="sku" id="sku" path="sku" value="${produto.sku}" class="form-control" maxlength="50" size="50" />
								<font color="red"><form:errors path="nome"/></font>
							</div>
							
							<div class="form-group">
								<label class="control-label" for="mesnagem">Descrição:</label>
								<form:textarea id="descricao" class="form-control" name="descricao" path="descricao" rows="4" cols="100"></form:textarea>
								<font color="red"><form:errors path="descricao"/></font>
							</div>
							
							<div class="form-group">
								<label class="control-label" for="preco">Preço:</label>
								<form:input type="number" step="any" min="0" name="preco" path="preco" id="preco" value="${produto.preco}" class="form-control" maxlength="14" size="15" />
								<font color="red"><form:errors path="preco"/></font>
							</div>
							
							<div class="form-group">
								<label class="control-label" for="mesnagem">Características:</label>
								<form:textarea id="mensagem" class="form-control" name="caracteristicas" path="caracteristicas"  rows="4" cols="100"></form:textarea>
								<font color="red"><form:errors path="caracteristicas"/></font>
							</div>
							<hr>
							
							<a class="btn btn-secondary btn-sm" href="${contextPath}/produto">Cancelar</a>
							<form:button type="submit" class="btn btn-primary btn-sm">Gravar</form:button>
						</form:form>
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