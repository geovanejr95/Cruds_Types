package br.com.kedge.mylibrary.web.viewhelper.implementation;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypeAddress;
import br.com.kedge.mylibrary.web.viewhelper.util.acViewHelperModels;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class cTypeAddressViewHelper extends acViewHelper {
    public cTypeAddressViewHelper() {
        super(acViewHelperModels.vhtypeaddress[0], acViewHelperModels.vhtypeaddress[1],
                acViewHelperModels.vhtypeaddress[2], acViewHelperModels.vhtypeaddress[3]);
    }

    public cEntityDomain getEntity(HttpServletRequest request) {
        String operation = request.getParameter("operation");
        cTypeAddress objTypeAddress = null;

        if (!operation.equals("Visualizar")) {
            String id = request.getParameter("txtId");
            String type = request.getParameter("txtType");
            boolean active = Boolean.parseBoolean(request.getParameter("txtActive"));

            objTypeAddress = new cTypeAddress();

            if (id != null && !id.trim().equals("")) {
                objTypeAddress.setId(Integer.parseInt(id));
            }
            if (type != null && !type.trim().equals("")) {
                objTypeAddress.setType(type);
            }
            if (active) {
                objTypeAddress.setActive(true);
            } else {
                objTypeAddress.setActive(false);
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
                    objTypeAddress = (cTypeAddress) objED;
                }
            }
        }

        return objTypeAddress;
    }
}
