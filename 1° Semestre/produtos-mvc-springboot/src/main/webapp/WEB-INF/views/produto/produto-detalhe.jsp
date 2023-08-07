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

		<section id="detalhes" class="bg-light pb-5">
			<div class="container">
				<div class="row">
					<div class="col-lg-12">
						<form>
							<h2 class="fonte-titulo texto-cor-especial">Produto</h2>
							<div class="form-group">
								<p class="fs-6 m-0">
									<b>Nome:</b> ${produto.nome}
								</p>
							</div>
							<div class="form-group">
								<p class="fs-6 m-0">
									<b>SKU:</b> ${produto.sku}
								</p>
							</div>
							<div class="form-group">
								<p class="fs-6 m-0">
									<b>Categoria:</b> ${produto.categoria.nomeCategoria}
								</p>
							</div>
							<div class="form-group">
								<p class="fs-6 m-0">
									<b>Marca:</b> ${produto.marca.nomeMarca}
								</p>
							</div>
							<div class="form-group">
								<p class="fs-6 m-0">
									<b>Descrição:</b> ${produto.descricao}
								</p>
							</div>
							<div class="form-group">
								<p class="fs-6 m-0">
									<b>Preço:</b> ${produto.preco}
								</p>
							</div>
							<div class="form-group">
								<p class="fs-6 m-0">
									<b>Características:</b> ${produto.caracteristicas}
								</p>
							</div>
							<hr>
							<div class="d-grid gap-2 col-6">
								<a class="btn btn-primary btn-sm" href="${contextPath}/produtos">Voltar</a>
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