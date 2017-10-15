package br.com.kedge.mylibrary.web.viewhelper.implementation;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cAccessLevel;
import br.com.kedge.mylibrary.web.viewhelper.iViewHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class cAccessLevelViewHelper implements iViewHelper {

    public cEntityDomain getEntity(HttpServletRequest request) {

        String operation = request.getParameter("operation");
        cAccessLevel objAccessLevel = null;

        if (!operation.equals("Visualizar")) {
            String id = request.getParameter("txtId");
            String access = request.getParameter("txtAccess");
            boolean active = Boolean.parseBoolean(request.getParameter("txtActive"));

            objAccessLevel = new cAccessLevel();

            if (access != null && !access.trim().equals("")) {
                objAccessLevel.setAccess(access);
            }
            if (id != null && !id.trim().equals("")) {
                objAccessLevel.setId(Integer.parseInt(id));
            }
            if (active) {
                objAccessLevel.setActive(true);
            } else {
                objAccessLevel.setActive(false);
            }
        } else {
            HttpSession session = request.getSession();
            cResult objResult = (cResult) session.getAttribute("objResult");
            String txtId = request.getParameter("txtId");
            int id = 0;

            if (txtId != null && !txtId.trim().equals("")) {
                id = Integer.parseInt(txtId);
            }

            for (cEntityDomain objED : objResult.getEntities()) {
                if (objED.getId() == id) {
                    objAccessLevel = (cAccessLevel) objED;
                }
            }
        }

        return objAccessLevel;
    }


    public void setView(cResult objResult, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = null;

        String operation = request.getParameter("operation");

        if (objResult.getMsg() == null) {
            if (operation.equals("Salvar")) {
                objResult.setMsg("Nivel de Acesso inserido com sucesso!");
            }

            request.getSession().setAttribute("objresult", objResult);
            rd = request.getRequestDispatcher("ConsultarNivelDeAcesso.jsp");
        }

        if (objResult.getMsg() == null && operation.equals("Alterar")) {
            rd = request.getRequestDispatcher("ConsultarNivelDeAcesso.jsp");
        }

        if (objResult.getMsg() == null && operation.equals("Visualizar")) {
            request.setAttribute("objaccesslevel", objResult.getEntities().get(0));
            rd = request.getRequestDispatcher("NivelDeAcesso.jsp");
        }

        if (objResult.getMsg() == null && operation.equals("Excluir")) {
            request.getSession().setAttribute("objresult", null);
            rd = request.getRequestDispatcher("ConsultarNivelDeAcesso.jsp");
        }

        if (objResult.getMsg() != null) {
            if (operation.equals("Salvar") || operation.equals("Alterar")) {
                request.getSession().setAttribute("objresult", objResult);
                rd = request.getRequestDispatcher("ConsultarNivelDeAcesso.jsp");
            }
        }

        if (objResult.getMsg() == null && operation.equals("Ativar")) {
            rd = request.getRequestDispatcher("ListarNiveisDeAcesso.jsp");
        }

        if (objResult.getMsg() == null && operation.equals("Inativar")) {
            rd = request.getRequestDispatcher("ListarNiveisDeAcesso.jsp");
        }

        System.out.println(rd.toString());
        rd.forward(request, response);
    }
}
