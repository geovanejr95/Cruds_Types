<%@ page import="br.com.kedge.mylibrary.core.aplication.cResult" %>
<%@ page import="br.com.kedge.mylibrary.domain.cEntityDomain" %>
<%@ page import="br.com.kedge.mylibrary.domain.entities.cTypePhone" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: geovane95
  Date: 13/10/17
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Consulta Tipo de Telefone</title>
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
        <form action="TypePhone" method="post">
            <fieldset>
                <legend>Tipo de Telefone</legend>
                <div>
                    <label for="txtId">Id: </label>
                    <input type='text' name='txtId' id='txtId' placeholder='0'/>
                </div>
                <div>
                    <label for="txtType">Tipo: </label>
                    <input type='text' name='txtType' id='txtType' maxlength='32' placeholder='Tipo de Telefone'/>
                </div>
                <div>
                    <input type="submit" formaction="InserirTipoDeTelefone.jsp" value="Cadastrar">
                    <input type='submit' class='btn btn-success' name='operation' id='operation' value='Consultar'/>
                </div>
            </fieldset>
        </form>
        <%

            if (objResult != null && objResult.getMsg() != null) {
                out.print(objResult.getMsg());
            }

        %>
    </article>
    <article>
        <table>
            <thead>
            <tr>
                <th colspan="4"><h3>Tipos de Telefones cadastrados!</h3></th>
            </tr>
            <tr>
                <th>#ID</th>
                <th>Tipo</th>
                <th>Ativo</th>
                <th>Visualizar</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (objResult != null) {
                    List<cEntityDomain> objEntities = objResult.getEntities();
                    StringBuilder criaTabela = new StringBuilder();

                    if (objEntities != null) {
                        for (cEntityDomain objED : objEntities) {
                            cTypePhone objTP = (cTypePhone) objED;

                            criaTabela.append("<tr>");
                            criaTabela.append("<td>");
                            criaTabela.append(objTP.getId());
                            criaTabela.append("</td>");
                            criaTabela.append("<td>");
                            criaTabela.append(objTP.getType());
                            criaTabela.append("</td>");
                            criaTabela.append("<td>");
                            if (objTP.isActive()) {
                                criaTabela.append("Sim");
                            } else {
                                criaTabela.append("Não");
                            }
                            criaTabela.append("</td>");
                            criaTabela.append("<td>");
                            criaTabela.append("<a href='TypePhone?txtId=").append(objTP.getId()).append("&operation=Visualizar'><button type='button' class='btn btn-success'>Visualizar</button></a>");
                            criaTabela.append("</td>");
                            criaTabela.append("</tr>");


                            out.print(criaTabela.toString());
                        }
                    }
                } else {
                    out.print("<tr><td colspan='4'>Nenhum dado a ser mostrado!</td></tr>");
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
