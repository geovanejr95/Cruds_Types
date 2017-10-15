<%@ page import="br.com.kedge.mylibrary.core.aplication.cResult" %>
<%@ page import="br.com.kedge.mylibrary.domain.cEntityDomain" %>
<%@ page import="br.com.kedge.mylibrary.domain.entities.cAccessLevel" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: geovane95
  Date: 09/10/17
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Consulta Nivel de Acesso</title>
</head>
<body>
<%
    cResult objResult = (cResult) session.getAttribute("objresult");
%>
<header>

</header>
<nav>

</nav>
<section>
    <article>
        <form action="AccessLevel" method="post">
            <fieldset>
                <legend>Nivel De Acesso</legend>
                <div>
                    <label for="txtId">Id: </label>
                    <input type='text' name='txtId' id='txtId' placeholder='0'/>
                </div>
                <div>
                    <label for="txtAccess">Accesso: </label>
                    <input type='text' name='txtAccess' id='txtAccess' placeholder='Nivel de Acesso'/>
                </div>
                <div>
                    <label for="txtActive1" for="txtActive2">Ativo: </label>
                    <label>
                        <input type='radio' name='txtActive' id='txtActive1' value='true'/>Sim
                    </label>
                    <label>
                        <input type='radio' name='txtActive' id='txtActive2' value='false'/>Não
                    </label>
                </div>
                <div>
                    <input type='submit' formaction="InserirNivelDeAcesso.jsp" class='btn btn-default'
                           value='Cadastrar'/>
                    <input type='submit' class='btn btn-info' name='operation' id='operation' value='Consultar'/>
                </div>
            </fieldset>
        </form>
        <%

            if (objResult != null && objResult.getMsg() != null) {
                out.print(objResult.getMsg());
            }

        %>
        <table>
            <thead>
            <tr>
                <th colspan="5"><h3>Niveis de Acesso cadastrados!</h3></th>
            </tr>
            <tr>
                <th>#ID</th>
                <th>Acesso</th>
                <th>Ativo</th>
                <th>Ativar/Inativar</th>
                <th>Visualizar</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (objResult != null) {
                    List<cEntityDomain> entidades = objResult.getEntities();
                    StringBuilder criaTabela = new StringBuilder();

                    for (cEntityDomain objED : entidades) {
                        cAccessLevel objAL = (cAccessLevel) objED;
                        criaTabela.append("<tr>");
                        criaTabela.append("<td>");
                        criaTabela.append(objAL.getId());
                        criaTabela.append("</td>");
                        criaTabela.append("<td>");
                        criaTabela.append(objAL.getAccess());
                        criaTabela.append("</td>");
                        criaTabela.append("<td>");
                        if (objAL.isActive()) {
                            criaTabela.append("Sim");
                        } else {
                            criaTabela.append("Não");
                        }
                        criaTabela.append("</td>");
                        criaTabela.append("<td>");
                        if (objAL.isActive()) {
                            criaTabela.append("<a href='AccessLevel?id=" + objAL.getId() + "operation=Inativar'><button type='button' class='btn btn-danger'>Inativar</button></a>");
                        } else {
                            criaTabela.append("<a href='AccessLevel?id=" + objAL.getId() + "operation=Ativar'><button type='button' class='btn btn-warning'>Ativar</button></a>");
                        }
                        criaTabela.append("</td>");
                        criaTabela.append("<td>");
                        criaTabela.append("<a href='AccessLevel?id=" + objAL.getId() + "operation=Visualizar'><button type='button' class='btn btn-success'>Visualizar</button></a>");
                        criaTabela.append("</td>");
                        criaTabela.append("</tr>");


                        out.print(criaTabela.toString());
                    }
                } else {
                    out.print("<tr><td colspan='5'>Nenhum dado a ser mostrado!</td></tr>");
                }
            %>
            </tbody>
        </table>
    </article>
</section>
<footer>

</footer>
</body>
</html>
