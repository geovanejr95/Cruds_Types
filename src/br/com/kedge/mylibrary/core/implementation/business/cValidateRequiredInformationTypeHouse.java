package br.com.kedge.mylibrary.core.implementation.business;

import br.com.kedge.mylibrary.core.iStrategy;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypeHouse;

public class cValidateRequiredInformationTypeHouse implements iStrategy {
    @Override
    public String process(cEntityDomain objEntityDomain) {

        if (objEntityDomain instanceof cTypeHouse) {
            cTypeHouse objTypeHouse = (cTypeHouse) objEntityDomain;

            String type = objTypeHouse.getType();

            if (type == null || type.trim().equals("")) {
                return "O nome do tipo de Residencia é obrigatório!";
            }
            if (type.length() > 32) {
                return "O nome do tipo de Residencia tem que possuir até 32 caracteres!";
            }
        } else {
            return "Deve ser registrado um Tipo de residencia!";
        }

        return null;
    }
}
