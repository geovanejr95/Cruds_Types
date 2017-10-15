package br.com.kedge.mylibrary.core.implementation.business;

import br.com.kedge.mylibrary.core.iStrategy;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cAccessLevel;

public class cValidateRequiredInformationAccessLevel implements iStrategy {
    @Override
    public String process(cEntityDomain entity) {

        if (entity instanceof cAccessLevel) {
            cAccessLevel objAccessLevel = (cAccessLevel) entity;

            String access = objAccessLevel.getAccess();
            boolean active = objAccessLevel.isActive();

            if (access == null || access.trim().equals("")) {
                return "O nome do nivel de acesso é obrigatório!";
            }
        } else {
            return "Deve ser registrado um Nivel de acesso!";
        }

        return null;
    }
}
