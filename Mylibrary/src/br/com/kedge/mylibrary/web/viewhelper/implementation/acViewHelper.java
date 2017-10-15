package br.com.kedge.mylibrary.web.viewhelper.implementation;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.web.viewhelper.iViewHelper;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class acViewHelper implements iViewHelper {

    protected String myClass;
    protected String urlInsert;
    protected String urlConsult;
    protected String objClass;

    public acViewHelper(String myClass, String urlInsert, String urlConsult, String objClass) {
        this.myClass = myClass;
        this.urlInsert = urlInsert;
        this.urlConsult = urlConsult;
        this.objClass = objClass;
    }

    @Override
    public void setView(cResult objResult, HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {

        RequestDispatcher rd = null;

        String operation = request.getParameter("operation");

        if (objResult.getMsg() == null) {
            if (operation.equals("Salvar")) {
                objResult.setMsg(myClass + " inserido com sucesso!");
            }

            request.getSession().setAttribute("objresult", objResult);
            rd = request.getRequestDispatcher(urlConsult);
        }

        if (objResult.getMsg() == null && operation.equals("Alterar")) {
            rd = request.getRequestDispatcher(urlConsult);
        }
        if (objResult.getMsg() == null && operation.equals("Visualizar")) {
            request.setAttribute(objClass, objResult.getEntities().get(0));
            rd = request.getRequestDispatcher(urlInsert);
        }

        if (objResult.getMsg() == null && operation.equals("Excluir")) {
            request.getSession().setAttribute("objresult", null);
            rd = request.getRequestDispatcher(urlConsult);
        }

        if (objResult.getMsg() != null) {
            if (operation.equals("Salvar") || operation.equals("Alterar")) {
                request.getSession().setAttribute("objresult", objResult);
                rd = request.getRequestDispatcher(urlConsult);
            }
        }

        if (objResult.getMsg() == null && operation.equals("Ativar")) {
            request.getSession().setAttribute("objresult", objResult);
            rd = request.getRequestDispatcher(urlConsult);
        }

        if (objResult.getMsg() == null && operation.equals("Inativar")) {
            request.getSession().setAttribute("objresult", objResult);
            rd = request.getRequestDispatcher(urlConsult);
        }

        forwardDispacher(rd, request, response);
    }

    private void forwardDispacher(RequestDispatcher rd, HttpServletRequest request,
                                  HttpServletResponse response) {
        try {
            assert rd != null;
            rd.forward(request, response);
        } catch (NullPointerException npe) {
            npe.printStackTrace();
            System.out.println("Erro ao executar o encaminhamento do request dispatcher!");
        } catch (ServletException se) {
            se.printStackTrace();
            System.out.println("Erro de Servlet ao executar o encaminhamento do request dispatcher!");
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.out.println("Erro de IO ao executar o encaminhamento do request dispatcher!");
        }
    }
}
