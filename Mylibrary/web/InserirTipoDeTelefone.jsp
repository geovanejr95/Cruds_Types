<%@ page import="br.com.kedge.mylibrary.domain.entities.cTypePhone" %>
<%@ page import="br.com.kedge.mylibrary.web.viewhelper.util.acViewHelperModels" %><%--
  Created by IntelliJ IDEA.
  User: geovane95
  Date: 14/10/17
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Inserir Tipo De Telefone</title>
</head>
<body>
<% cTypePhone objTypePhone = (cTypePhone) request.getAttribute(acViewHelperModels.vhtypephone[3]); %>
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
                    <input type='text' name='txtId' id='txtId' placeholder='0'
                            <%
                                if (objTypePhone != null) {
                                    out.print("value='" + objTypePhone.getId() + "' readonly");
                                }
                            %>
                    />
                </div>
                <div>
                    <label for="txtType">Tipo de Telefone: </label>
                    <input type='text' name='txtType' id='txtType' maxlength='32' placeholder='Tipo de Telefone'
                            <%
                                if (objTypePhone != null) {
                                    out.print("value='" + objTypePhone.getType() + "'");
                                }
                            %>
                    />
                </div>
                <div>
                    <label for="txtActive1" for="txtActive2">Ativo: </label>
                    <label>
                        <input type='radio' name='txtActive' id='txtActive1' value='true' checked/>Sim
                    </label>
                    <label>
                        <input type='radio' name='txtActive' id='txtActive2' value='false'
                                <%
                                    if (objTypePhone != null && !objTypePhone.isActive()) {
                                        out.print("checked");
                                    }
                                %>
                        />Não
                    </label>
                </div>
                <div>
                    <%
                        if (objTypePhone != null) {
                            out.print("<input type='submit' class='btn btn-primary' name='operation' id='operation' value='Alterar'/>");
                            if (objTypePhone.isActive()) {
                                out.print("<input type='submit' class='btn btn-danger' name='operation' id='operation' value='Inativar'/>");
                            } else {
                                out.print("<input type='submit' class='btn btn-danger' name='operation' id='operation' value='Ativar'/>");
                            }
                        } else {
                            out.print("<input type='submit' class='btn btn-success' name='operation' id='operation' value='Salvar'/>");
                        }

                    %>
                </div>
            </fieldset>
        </form>
    </article>
</section>
<footer>

</footer>

</body>
</html>
