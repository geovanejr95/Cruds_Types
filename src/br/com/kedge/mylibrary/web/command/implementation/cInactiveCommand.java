package br.com.kedge.mylibrary.web.command.implementation;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;

public class cInactiveCommand extends acCommand {

    public cResult execute(cEntityDomain objEntityDomain) {
        return objFacade.inactive(objEntityDomain);
    }
}
