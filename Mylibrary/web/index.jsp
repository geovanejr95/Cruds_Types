<%--
  Created by IntelliJ IDEA.
  User: geovane95
  Date: 05/10/17
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%

%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css"/>
    <style type="text/css">
        .jumbotron, .navbar {
            margin-top: 0px;
            margin-bottom: 0px;
        }
    </style>
    <title>My Library</title>
</head>
<body>
<header class="jumbotron">
    <h1>My Library</h1>
</header>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#MyNavbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#ListarLivros" data-toggle="tab">My Library</a>
        </div>

        <div class="collapse navbar-collapse" id="MyNavbar">
            <ul class="nav navbar-nav">
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Livros
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="Livro.jsp">Inserir Livro</a></li>
                        <li><a href="ListarLivros.jsp">Listar Livros</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Categorias
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="Categoria.jsp">Inserir Categorias</a></li>
                        <li><a href="ListarCategorias.jsp">Listar Categorias</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Usuarios
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="Cliente.jsp">Inserir Cliente</a></li>
                        <li><a href="ListarClientes.jsp">Listar Clientes</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Niveis
                        de Acesso
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="InserirNivelDeAcesso.jsp">Inserir Nivel de Acesso</a></li>
                        <li><a href="ListarClientes.jsp">Listar Clientes</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Bandeiras
                        de Cartões<span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="BandeiraDeCartoes.jsp">Inserir Bandeira de Cartão</a></li>
                        <li><a href="ListarBandeirasDeCartoes.jsp">Listar Bandeira de Cartão</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Autores
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="InserirAutor.jsp">Inserir Autor</a></li>
                        <li><a href="Author?operation=Listar">Listar Autores</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Tipos
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="InserirTipoDeEndereco.jsp">Inserir Tipo de Endereço</a></li>
                        <li class="divider"></li>
                        <li><a href="InserirTipoDeResidencia.jsp">Inserir Tipo de Residencia</a></li>
                        <li class="divider"></li>
                        <li><a href="InserirTipoDeLogradouro.jsp">Inserir Tipo de Logradouro</a></li>
                        <li class="divider"></li>
                        <li><a href="InserirTipoDeTelefone.jsp">Inserir Tipo de Telefone</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Editoras
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="Editora.jsp">Inserir Editora</a></li>
                        <li><a href="Listar Editoras.jsp">Listar Editoras</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Grupo
                        de Precificações <span class="caret"></span></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="GrupoDePrecificacao.jsp">Inserir Grupo de Precificação</a></li>
                        <li><a href="ListarGruposDePrecificacao.jsp">Listar Grupo de Precificações</a></li>
                    </ul>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="Logout.jsp">Logout</a></li>
            </ul>
        </div>
    </div>
</nav>
<section>
    <article>
    </article>
</section>
<footer>
    <script src="./js/jQuery.js"></script>
    <script src="./js/bootstrap.min.js"></script>
</footer>
</body>
</html>

<!--
<section class="tab-content" id="MyContent">
<article id="createbook" class="tab-pane fade">
<div class="row text-center">
<div class="col-md-3"></div>
<div class="col-md-5 row">
<fieldset>
<legend>Livro</legend>
<form class="form-horizontal col-md-12 row" method="post">
<label class="control-label col-md-12" for="title">Titulo</label>
<input type="text" class="col-md-12" name="title" id="title"/>
<label class="control-label col-md-12" for="year">Ano de Lançamento</label>
<input type="number" class="col-md-12" name="year" id="year"/>
<label class="control-label col-md-12" for="numpage">Numero de Paginas</label>
<input type="text" class="col-md-12" name="numpage" id="numpage"/>
<label class="control-label col-md-12" for="sinopsis">Sinopse</label>
<input type="text" class="col-md-12" name="sinopsis" id="sinopsis"/>
<label class="control-label col-md-12" for="barcode">Código de Barras</label>
<input type="text" class="col-md-12" name="barcode" id="barcode"/>
<label class="control-label col-md-12" for="value">Valor</label>
<input type="text" class="col-md-12" name="value" id="value"/>
<label class="control-label col-md-12" for="isbn">ISBN</label>
<input type="text" class="col-md-12" name="isbn" id="isbn"/>
<label class="control-label col-md-12" for="length">Profundidade</label>
<input type="text" class="col-md-12" name="length" id="length"/>
<label class="control-label col-md-12" for="height">Altura</label>
<input type="text" class="col-md-12" name="height" id="height"/>
<label class="control-label col-md-12" for="width">Largura</label>
<input type="text" class="col-md-12" name="width" id="width"/>
<label class="control-label col-md-12" for="weigth">Peso</label>
<input type="text" class="col-md-12" name="weigth" id="weigth"/>
<label class="control-label col-md-12" for="pricinggroup">Grupo de Precificação</label>
<select name="pricinggroup" id="pricinggroup">
<option value="1">Estudos</option>
<option value="2">Ficção</option>
<option value="3">Documentarios</option>
<option value="4">Promocionais</option>
<option value="5">Brasileiros</option>
</select>
</form>
</fieldset>
</div>
<div class="col-md-4"></div>
</div>
</article>
<article id="dropbook" class="tab-pane fade">
drop book
</article>
<article id="listbooks" class="tab-pane fade active in">
list book
</article>
<article id="createcategory" class="tab-pane fade">

</article>
<article id="dropcategory" class="tab-pane fade">

</article>
<article id="listcategorys" class="tab-pane fade">

</article>
<article id="createuser" class="tab-pane fade">

</article>
<article id="dropuser" class="tab-pane fade">

</article>
<article id="listusers" class="tab-pane fade">

</article>
<article id="createauthor" class="tab-pane fade">

</article>
<article id="dropauthor" class="tab-pane fade">

</article>
<article id="listauthors" class="tab-pane fade">

</article>
<article id="createeditor" class="tab-pane fade">

</article>
<article id="dropeditor" class="tab-pane fade">

</article>
<article id="listeditors" class="tab-pane fade">

</article>
<article id="createpricinggroup" class="tab-pane fade">

</article>
<article id="droppricinggroup" class="tab-pane fade">

</article>
<article id="listpricinggroups" class="tab-pane fade">

</article>
<article id="createbannercard" class="tab-pane fade">
<div class="row text-center">
<div class="col-md-3"></div>
<div class="col-md-5 row">
<fieldset>
<legend>Bandeiras de Cartões</legend>
<form class="form-horizontal col-md-12 row" action="Servlet" method="post">
<div class="form-group">
<label class="control-label col-md-12" for="txtBanner">Bandeira</label>
<input type="text" class="col-md-12" name="txtBanner" id="txtBanner"/>
</div>
<div class="form-group">
<input type="submit" id="operation" name="operation" value="create_BannerCard"/>
<input type="submit" id="operation" name="operation" value="drop_BannerCard"/>
</div>
</form>
</fieldset>
</div>
<div class="col-md-4"></div>
</div>
</article>-->