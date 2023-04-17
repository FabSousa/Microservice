<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>
<html>

<head>
<meta charset="ISO-8859-1">
<title>Produtos</title>

<!-- ATALHO PARA TRAZER A URL DE CONTEXTO DO PROJETO -->
<c:set value="${pageContext.request.contextPath}" var="contextPath" />

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
<link
	href="https://fonts.googleapis.com/css2?family=Pacifico&display=swap"
	rel="stylesheet">

</head>
<body>
	<header>
		<nav class="navbar navbar-dark bg-dark">
			<div class="container-fluid">
				<a class="navbar-brand fonte-titulo" href="${contextPath}/produtos">Produtos</a>
			</div>
		</nav>
	</header>

	<main>
		<section id="formulario" class="bg-light pb-5">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<div class="well">

							<h2 class="fonte-titulo texto-cor-especial">Produto</h2>

							<form:form modelAttribute="produtoModel"
								action="${contextPath}/produtos" method="post">

								<spring:hasBindErrors name="produtoModel">
									<div class="alert alert-danger" role="alert">
										<form:errors path="*" class="has-error" />
									</div>
								</spring:hasBindErrors>

								<div class="form-group">
									<label class="control-label" for="nome">Nome:</label>
									<form:input type="text" name="nome" id="nome" path="nome"
										class="form-control" maxlength="50" size="50" />
									<font color="red"><form:errors path="nome" /></font><br />
								</div>
								<div class="form-group">
									<label class="control-label" for="sku">SKU:</label>
									<form:input type="text" name="sku" id="sku" path="sku"
										class="form-control" maxlength="50" size="50" />
									<font color="red"><form:errors path="sku" /></font><br />
								</div>

								<div class="form-group">
									<label class="control-label" for="categoria">Categorias:</label>
									<form:select path="categoria.idCategoria">
										<form:options items="${categorias}" itemValue="idCategoria"
											itemLabel="nomeCategoria" />
									</form:select>
								</div>

								<div class="form-group">
									<label class="control-label" for="marca">Marcas:</label>
									<form:select path="marca.idMarca">
										<form:options items="${marcas}" itemValue="idMarca"
											itemLabel="nomeMarca" />
									</form:select>
								</div>

								<div class="form-group">
									<label class="control-label" for="descricao">Descrição:</label>
									<form:textarea name="descricao" id="descricao" path="descricao"
										class="form-control" rows="4" cols="100" />
									<font color="red"><form:errors path="descricao" /></font><br />
								</div>

								<div class="form-group">
									<label class="control-label" for="preco">Preço:</label>
									<form:input type="number" name="preco" id="preco" path="preco"
										step=".01" class="form-control" />
									<font color="red"><form:errors path="preco" /></font><br />
								</div>

								<div class="form-group">
									<label class="control-label" for="caracteristicas">Características:</label>
									<form:textarea name="caracteristicas" id="caracteristicas"
										path="caracteristicas" class="form-control" rows="4"
										cols="100" />
									<font color="red"><form:errors path="caracteristicas" /></font><br />
								</div>
								<hr>

								<a class="btn btn-secondary btn-sm"
									href="${contextPath}/produtos">Cancelar</a>
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