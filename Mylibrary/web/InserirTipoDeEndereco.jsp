<%@ page import="br.com.kedge.mylibrary.domain.entities.cTypeAddress" %><%--
  Created by IntelliJ IDEA.
  User: geovane95
  Date: 14/10/17
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Inserir Tipo De Endereço</title>
</head>
<body>
<% cTypeAddress objTypeAddress = (cTypeAddress) request.getAttribute("objtypeaddress"); %>
<header>

</header>
<nav>

</nav>
<section>
    <article>
        <form action="TypeAddress" method="post">
            <fieldset>
                <legend>Tipo de Endereço</legend>
                <div>
                    <label for="txtId">Id: </label>
                    <input type='text' name='txtId' id='txtId' placeholder='0'
                            <%
                                if (objTypeAddress != null) {
                                    out.print("value='" + objTypeAddress.getId() + "' readonly");
                                }
                            %>
                    />
                </div>
                <div>
                    <label for="txtType">Tipo de Endereço: </label>
                    <input type='text' name='txtType' id='txtType' maxlength='32' placeholder='Tipo de Endereço'
                            <%
                                if (objTypeAddress != null) {
                                    out.print("value='" + objTypeAddress.getType() + "'");
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
                                    if (objTypeAddress != null && !objTypeAddress.isActive()) {
                                        out.print("checked");
                                    }
                                %>
                        />Não
                    </label>
                </div>
                <div>
                    <%
                        if (objTypeAddress != null) {
                            out.print("<input type='submit' class='btn btn-primary' name='operation' id='operation' value='Alterar'/>");
                            if (objTypeAddress.isActive()) {
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
