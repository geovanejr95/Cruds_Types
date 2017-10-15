package br.com.kedge.mylibrary.core;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;

public interface iFacade {

    public cResult create(cEntityDomain objEntityDomain);

    public cResult update(cEntityDomain objEntityDomain);

    public cResult drop(cEntityDomain objEntityDomain);

    public cResult retrive(cEntityDomain objEntityDomain);

    public cResult view(cEntityDomain objEntityDomain);

    public cResult active(cEntityDomain objEntityDomain);

    public cResult inactive(cEntityDomain objEntityDomain);

}
