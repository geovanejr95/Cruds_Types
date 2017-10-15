package br.com.kedge.mylibrary.core.implementation.business;

import br.com.kedge.mylibrary.core.iStrategy;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypeAddress;

public class cValidateRequiredInformationTypeAddress implements iStrategy {
    @Override
    public String process(cEntityDomain objEntityDomain) {

        if (objEntityDomain instanceof cTypeAddress) {
            cTypeAddress objTypeAddress = (cTypeAddress) objEntityDomain;

            String type = objTypeAddress.getType();

            if (type == null || type.trim().equals("")) {
                return "O nome do tipo de endereço é obrigatório!";
            }
            if (type.length() > 32) {
                return "O nome do tipo de endereço tem que possuir até 32 caracteres!";
            }
        } else {
            return "Deve ser registrado um Tipo de endereço!";
        }

        return null;
    }
}
