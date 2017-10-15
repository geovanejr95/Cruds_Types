<%--
  Created by IntelliJ IDEA.
  User: geovane95
  Date: 09/10/17
  Time: 16:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="br.com.kedge.mylibrary.domain.entities.cAccessLevel" %>
<html>
<head>
    <title>Nivel de Acesso</title>
</head>
<body>
<% cAccessLevel objAccessLevel = (cAccessLevel) request.getAttribute("objaccesslevel"); %>
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
                    <input type='text' name='txtId' id='txtId' placeholder='0'
                            <%
                                if (objAccessLevel != null) {
                                    out.print("value='" + objAccessLevel.getId() + "' readonly>");
                                }
                            %>
                    />
                </div>
                <div>
                    <label for="txtAccess">Accesso: </label>
                    <input type='text' name='txtAccess' id='txtAccess' placeholder='Nivel de Acesso'
                            <%
                                if (objAccessLevel != null) {
                                    out.print("value='" + objAccessLevel.getAccess() + "'>");
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
                                    if (objAccessLevel != null && !objAccessLevel.isActive()) {
                                        out.print("checked");
                                    }
                                %>
                        />Não
                    </label>
                </div>
                <div>
                    <%
                        if (objAccessLevel != null) {
                            out.print("<input type='submit' class='btn btn-primary' name='operation' id='operation' value='Alterar'/>");
                            if (objAccessLevel.isActive()) {
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
