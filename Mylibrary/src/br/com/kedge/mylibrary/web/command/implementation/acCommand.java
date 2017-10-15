package br.com.kedge.mylibrary.web.command.implementation;

import br.com.kedge.mylibrary.core.iFacade;
import br.com.kedge.mylibrary.core.implementation.control.cFacade;
import br.com.kedge.mylibrary.web.command.iCommand;

public abstract class acCommand implements iCommand {
    protected iFacade objFacade = new cFacade();
}
