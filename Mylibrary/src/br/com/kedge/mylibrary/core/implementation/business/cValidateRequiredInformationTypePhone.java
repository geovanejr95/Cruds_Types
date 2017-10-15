package br.com.kedge.mylibrary.core.implementation.business;

import br.com.kedge.mylibrary.core.iStrategy;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypePhone;

public class cValidateRequiredInformationTypePhone implements iStrategy {
    @Override
    public String process(cEntityDomain entity) {

        if (entity instanceof cTypePhone) {
            cTypePhone objTypePhone = (cTypePhone) entity;

            String type = objTypePhone.getType();

            if (type == null || type.trim().equals("")) {
                return "O nome do tipo de telefone é obrigatório!";
            }
            if (type.length() > 32) {
                return "O nome do tipo de telefone tem que possuir até 32 caracteres!";
            }
        } else {
            return "Deve ser registrado um Tipo de Telefone!";
        }

        return null;
    }
}
