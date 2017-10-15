package br.com.kedge.mylibrary.web.command;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;

public interface iCommand {

    public cResult execute(cEntityDomain objEntityDomain);
}
