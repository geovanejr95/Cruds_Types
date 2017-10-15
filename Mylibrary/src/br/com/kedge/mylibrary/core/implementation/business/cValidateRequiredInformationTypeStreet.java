package br.com.kedge.mylibrary.core.implementation.business;

import br.com.kedge.mylibrary.core.iStrategy;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypeStreet;

public class cValidateRequiredInformationTypeStreet implements iStrategy {
    @Override
    public String process(cEntityDomain objEntityDomain) {

        if (objEntityDomain instanceof cTypeStreet) {
            cTypeStreet objTypeStreet = (cTypeStreet) objEntityDomain;

            String type = objTypeStreet.getType();

            if (type == null || type.trim().equals("")) {
                return "O nome do tipo de Logradouro é obrigatório!";
            }
            if (type.length() > 32) {
                return "O nome do tipo de Logradouro tem que possuir até 32 caracteres!";
            }
        } else {
            return "Deve ser registrado um Tipo de logradouro!";
        }

        return null;
    }
}
