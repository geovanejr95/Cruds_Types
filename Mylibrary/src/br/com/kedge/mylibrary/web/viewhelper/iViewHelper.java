package br.com.kedge.mylibrary.web.viewhelper;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface iViewHelper {

    cEntityDomain getEntity(HttpServletRequest request);

    void setView(cResult objResult, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException;
}
