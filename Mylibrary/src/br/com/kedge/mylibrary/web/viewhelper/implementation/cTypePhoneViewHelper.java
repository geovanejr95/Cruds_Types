package br.com.kedge.mylibrary.web.viewhelper.implementation;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypePhone;
import br.com.kedge.mylibrary.web.viewhelper.util.acViewHelperModels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class cTypePhoneViewHelper extends acViewHelper {

    public cTypePhoneViewHelper() {
        super(acViewHelperModels.vhtypephone[0], acViewHelperModels.vhtypephone[1],
                acViewHelperModels.vhtypephone[2], acViewHelperModels.vhtypephone[3]);
    }

    public cEntityDomain getEntity(HttpServletRequest request) {
        String operation = request.getParameter("operation");
        cTypePhone objTypePhone = null;

        if (!operation.equals("Visualizar")) {
            String id = request.getParameter("txtId");
            String type = request.getParameter("txtType");
            boolean active = Boolean.parseBoolean(request.getParameter("txtActive"));

            objTypePhone = new cTypePhone();

            if (id != null && !id.trim().equals("")) {
                objTypePhone.setId(Integer.parseInt(id));
            }
            if (type != null && !type.trim().equals("")) {
                objTypePhone.setType(type);
            }
            if (active) {
                objTypePhone.setActive(true);
            } else {
                objTypePhone.setActive(false);
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
                    objTypePhone = (cTypePhone) objED;
                }
            }
        }

        return objTypePhone;
    }
}
