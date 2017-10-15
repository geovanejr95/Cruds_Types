package br.com.kedge.mylibrary.web.viewhelper.implementation;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypeHouse;
import br.com.kedge.mylibrary.web.viewhelper.util.acViewHelperModels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class cTypeHouseViewHelper extends acViewHelper {
    public cTypeHouseViewHelper() {
        super(acViewHelperModels.vhtypehouse[0], acViewHelperModels.vhtypehouse[1],
                acViewHelperModels.vhtypehouse[2], acViewHelperModels.vhtypehouse[3]);
    }

    public cEntityDomain getEntity(HttpServletRequest request) {
        String operation = request.getParameter("operation");
        cTypeHouse objTypeHouse = null;

        if (!operation.equals("Visualizar")) {
            String id = request.getParameter("txtId");
            String type = request.getParameter("txtType");
            boolean active = Boolean.parseBoolean(request.getParameter("txtActive"));

            objTypeHouse = new cTypeHouse();

            if (id != null && !id.trim().equals("")) {
                objTypeHouse.setId(Integer.parseInt(id));
            }
            if (type != null && !type.trim().equals("")) {
                objTypeHouse.setType(type);
            }
            if (active) {
                objTypeHouse.setActive(true);
            } else {
                objTypeHouse.setActive(false);
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
                    objTypeHouse = (cTypeHouse) objED;
                }
            }
        }

        return objTypeHouse;
    }
}
