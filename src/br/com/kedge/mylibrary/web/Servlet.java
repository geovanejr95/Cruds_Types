package br.com.kedge.mylibrary.web;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.web.command.iCommand;
import br.com.kedge.mylibrary.web.command.implementation.*;
import br.com.kedge.mylibrary.web.viewhelper.iViewHelper;
import br.com.kedge.mylibrary.web.viewhelper.implementation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends HttpServlet {

    public static Map<String, iCommand> commands;
    public static Map<String, iViewHelper> vhs;

    public Servlet() {
        /* Utilizando o command para chamar a fachada e indexando cada command
         * pela operação garantimos que esta servelt atenderá qualquer operação */
        commands = new HashMap<String, iCommand>();


        commands.put("Salvar", new cCreateCommand());
        commands.put("Excluir", new cDropCommand());
        commands.put("Consultar", new cRetriveCommand());
        commands.put("Visualizar", new cViewCommand());
        commands.put("Alterar", new cUpdateCommand());
        commands.put("Ativar", new cActiveCommand());
        commands.put("Inativar", new cInactiveCommand());


    	/* Utilizando o ViewHelper para tratar especificações de qualquer tela e indexando
    	 * cada viewhelper pela url em que esta servlet é chamada no form
    	 * garantimos que esta servelt atenderá qualquer entidade */

        vhs = new HashMap<String, iViewHelper>();
    	/*A chave do mapa é o mapeamento da servlet para cada form que
    	 * está configurado no web.xml e sendo utilizada no action do html
    	 */
        vhs.put("/mylibrary/AccessLevel", new cAccessLevelViewHelper());
        vhs.put("/mylibrary/TypeAddress", new cTypeAddressViewHelper());
        vhs.put("/mylibrary/TypeHouse", new cTypeHouseViewHelper());
        vhs.put("/mylibrary/TypePhone", new cTypePhoneViewHelper());
        vhs.put("/mylibrary/TypeStreet", new cTypeStreetViewHelper());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcessRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doProcessRequest(request, response);
    }

    private void doProcessRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        //Obtêm a uri que invocou esta servlet (O que foi definido no methdo do form html)
        String uri = request.getRequestURI();

        //Obtêm a operação executada
        String operation = request.getParameter("operation");

        //Obtêm um viewhelper indexado pela uri que invocou esta servlet
        iViewHelper vh = vhs.get(uri);

        //O viewhelper retorna a entidade especifica para a tela que chamou esta servlet
        cEntityDomain objEntityDomain = vh.getEntity(request);

        //Obtêm o command para executar a respectiva operação
        iCommand objCommand = commands.get(operation);

        /*Executa o command que chamará a fachada para executar a operação requisitada
         * o retorno é uma instância da classe resultado que pode conter mensagens derro
         * ou entidades de retorno
         */
        cResult objResult = objCommand.execute(objEntityDomain);

        /*
         * Executa o método setView do view helper específico para definir como deverá ser apresentado
         * o resultado para o usuário
         */
        vh.setView(objResult, request, response);
    }
}
