package br.com.kedge.mylibrary.web.viewhelper.implementation;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypeStreet;
import br.com.kedge.mylibrary.web.viewhelper.util.acViewHelperModels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class cTypeStreetViewHelper extends acViewHelper {

    public cTypeStreetViewHelper() {
        super(acViewHelperModels.vhtypestreet[0], acViewHelperModels.vhtypestreet[1],
                acViewHelperModels.vhtypestreet[2], acViewHelperModels.vhtypestreet[3]);
    }

    public cEntityDomain getEntity(HttpServletRequest request) {
        String operation = request.getParameter("operation");
        cTypeStreet objTypeStreet = null;

        if (!operation.equals("Visualizar")) {
            String id = request.getParameter("txtId");
            String type = request.getParameter("txtType");
            boolean active = Boolean.parseBoolean(request.getParameter("txtActive"));

            objTypeStreet = new cTypeStreet();

            if (id != null && !id.trim().equals("")) {
                objTypeStreet.setId(Integer.parseInt(id));
            }
            if (type != null && !type.trim().equals("")) {
                objTypeStreet.setType(type);
            }
            if (active) {
                objTypeStreet.setActive(true);
            } else {
                objTypeStreet.setActive(false);
            }
        } else {
            HttpSession session = request.getSession();
            cResult objResult = (cResult) session.getAttribute("objresult");
            String txtId = request.getParameter("txtId");
            int id = 0;

            if (txtId != null && !txtId.trim().equals("")) {
                id = Integer.parseInt(txtId);
            }

            for (cEntityDomain objED : objResult.getEntities()) {
                if (objED.getId() == id) {
                    objTypeStreet = (cTypeStreet) objED;
                }
            }
        }

        return objTypeStreet;
    }
}
