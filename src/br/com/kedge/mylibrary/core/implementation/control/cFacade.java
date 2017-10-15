package br.com.kedge.mylibrary.core.implementation.control;

import br.com.kedge.mylibrary.core.aplication.cResult;
import br.com.kedge.mylibrary.core.iDao;
import br.com.kedge.mylibrary.core.iFacade;
import br.com.kedge.mylibrary.core.iStrategy;
import br.com.kedge.mylibrary.core.implementation.business.*;
import br.com.kedge.mylibrary.core.implementation.dao.*;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class cFacade implements iFacade {

    /**
     * Mapa de DAOS, será indexado pelo nome da entidade
     * O valor é uma instância do DAO para uma dada entidade;
     */
    private Map<String, iDao> daos;

    /**
     * Mapa para conter as regras de negócio de todas operações por entidade;
     * O valor é um mapa que de regras de negócio indexado pela operação
     */
    private Map<String, Map<String, List<iStrategy>>> rns;

    private cResult objResult;

    public cFacade() {
        /* Intânciando o Map de DAOS */
        daos = new HashMap<String, iDao>();

        /* Intânciando o Map de Regras de Negócio */
        rns = new HashMap<String, Map<String, List<iStrategy>>>();

        /* Criando instâncias dos DAOs a serem utilizados*/
        cAccessLevelDao objAccessLevelDao = new cAccessLevelDao();
        cTypeAddressDao objTypeAddressDao = new cTypeAddressDao();
        cTypeHouseDao objTypeHouseDao = new cTypeHouseDao();
        cTypePhoneDao objTypePhoneDao = new cTypePhoneDao();
        cTypeStreetDao objTypeStreetDao = new cTypeStreetDao();

        /* Adicionando cada dao no MAP indexando pelo nome da classe */
        daos.put(cAccessLevel.class.getName(), objAccessLevelDao);
        daos.put(cTypeAddress.class.getName(), objTypeAddressDao);
        daos.put(cTypeHouse.class.getName(), objTypeHouseDao);
        daos.put(cTypePhone.class.getName(), objTypePhoneDao);
        daos.put(cTypeStreet.class.getName(), objTypeStreetDao);

        /* Criando instâncias de regras de negócio a serem utilizados*/
        cValidateRequiredInformationAccessLevel objVRIAccessLevel = new cValidateRequiredInformationAccessLevel();
        cValidateRequiredInformationTypeAddress objVRITypeAddress = new cValidateRequiredInformationTypeAddress();
        cValidateRequiredInformationTypeHouse objVRITypeHouse = new cValidateRequiredInformationTypeHouse();
        cValidateRequiredInformationTypePhone objVRITypePhone = new cValidateRequiredInformationTypePhone();
        cValidateRequiredInformationTypeStreet objVRITypeStreet = new cValidateRequiredInformationTypeStreet();

        /* Criando uma lista para conter as regras de negócio
         * quando a operação for create
		 */
        List<iStrategy> rnsCreateAccessLevel = new ArrayList<iStrategy>();
        List<iStrategy> rnsCreateTypeAddress = new ArrayList<iStrategy>();
        List<iStrategy> rnsCreateTypeHouse = new ArrayList<iStrategy>();
        List<iStrategy> rnsCreateTypePhone = new ArrayList<iStrategy>();
        List<iStrategy> rnsCreateTypeStreet = new ArrayList<iStrategy>();


        /* Criando uma lista para conter as regras de negócio
         * quando a operação for update
		 */
        List<iStrategy> rnsUpdateAccessLevel = new ArrayList<iStrategy>();
        List<iStrategy> rnsUpdateTypeAddress = new ArrayList<iStrategy>();
        List<iStrategy> rnsUpdateTypeHouse = new ArrayList<iStrategy>();
        List<iStrategy> rnsUpdateTypePhone = new ArrayList<iStrategy>();
        List<iStrategy> rnsUpdateTypeStreet = new ArrayList<iStrategy>();


        /* Criando uma lista para conter as regras de negócio
         * quando a operação for drop
		 */
        List<iStrategy> rnsDropAccessLevel = new ArrayList<iStrategy>();
        List<iStrategy> rnsDropTypeAddress = new ArrayList<iStrategy>();
        List<iStrategy> rnsDropTypeHouse = new ArrayList<iStrategy>();
        List<iStrategy> rnsDropTypePhone = new ArrayList<iStrategy>();
        List<iStrategy> rnsDropTypeStreet = new ArrayList<iStrategy>();


        /* Criando uma lista para conter as regras de negócio
         * quando a operação for inactive
		 */
        List<iStrategy> rnsInactiveAccessLevel = new ArrayList<iStrategy>();
        List<iStrategy> rnsInactiveTypeAddress = new ArrayList<iStrategy>();
        List<iStrategy> rnsInactiveTypeHouse = new ArrayList<iStrategy>();
        List<iStrategy> rnsInactiveTypePhone = new ArrayList<iStrategy>();
        List<iStrategy> rnsInactiveTypeStreet = new ArrayList<iStrategy>();


        /* Criando uma lista para conter as regras de negócio
         * quando a operação for active
		 */
        List<iStrategy> rnsActiveAccessLevel = new ArrayList<iStrategy>();
        List<iStrategy> rnsActiveTypeAddress = new ArrayList<iStrategy>();
        List<iStrategy> rnsActiveTypeHouse = new ArrayList<iStrategy>();
        List<iStrategy> rnsActiveTypePhone = new ArrayList<iStrategy>();
        List<iStrategy> rnsActiveTypeStreet = new ArrayList<iStrategy>();

        /* Adicionando as regras a serem utilizadas na operação salvar do fornecedor*/
        rnsCreateAccessLevel.add(objVRIAccessLevel);
        rnsCreateTypeAddress.add(objVRITypeAddress);
        rnsCreateTypeHouse.add(objVRITypeHouse);
        rnsCreateTypePhone.add(objVRITypePhone);
        rnsCreateTypeStreet.add(objVRITypeStreet);

        /* Cria o mapa que poderá conter todas as listas de regras de negócio específica
         * por operação  do access level
		 */
        Map<String, List<iStrategy>> rnsAccessLevel = new HashMap<String, List<iStrategy>>();
        Map<String, List<iStrategy>> rnsTypeAddress = new HashMap<String, List<iStrategy>>();
        Map<String, List<iStrategy>> rnsTypeHouse = new HashMap<String, List<iStrategy>>();
        Map<String, List<iStrategy>> rnsTypePhone = new HashMap<String, List<iStrategy>>();
        Map<String, List<iStrategy>> rnsTypeStreet = new HashMap<String, List<iStrategy>>();

        /*
         * Adiciona a lista de regras de todas as operações no mapa de Tipo de Endereços
		 */
        rnsTypeAddress.put("Salvar", rnsCreateTypeAddress);
        rnsTypeAddress.put("Alterar", rnsUpdateTypeAddress);
        rnsTypeAddress.put("Excluir", rnsDropTypeAddress);
        rnsTypeAddress.put("Inativar", rnsInactiveTypeAddress);
        rnsTypeAddress.put("Ativar", rnsActiveTypeAddress);

        /*
         * Adiciona a lista de regras de todas as operações no mapa de Tipo de Ruas
		 */
        rnsTypeStreet.put("Salvar", rnsCreateTypeStreet);
        rnsTypeStreet.put("Alterar", rnsUpdateTypeStreet);
        rnsTypeStreet.put("Excluir", rnsDropTypeStreet);
        rnsTypeStreet.put("Inativar", rnsInactiveTypeStreet);
        rnsTypeStreet.put("Ativar", rnsActiveTypeStreet);

        /*
         * Adiciona a lista de regras de todas as operações no mapa de Tipo de Telefones
		 */
        rnsTypePhone.put("Salvar", rnsCreateTypePhone);
        rnsTypePhone.put("Alterar", rnsUpdateTypePhone);
        rnsTypePhone.put("Excluir", rnsDropTypePhone);
        rnsTypePhone.put("Inativar", rnsInactiveTypePhone);
        rnsTypePhone.put("Ativar", rnsActiveTypePhone);

        /*
         * Adiciona a lista de regras de todas as operações no mapa de Tipo de Casas
		 */
        rnsTypeHouse.put("Salvar", rnsCreateTypeHouse);
        rnsTypeHouse.put("Alterar", rnsUpdateTypeHouse);
        rnsTypeHouse.put("Excluir", rnsDropTypeHouse);
        rnsTypeHouse.put("Inativar", rnsInactiveTypeHouse);
        rnsTypeHouse.put("Ativar", rnsActiveTypeHouse);



        rnsAccessLevel.put("Salvar", rnsCreateAccessLevel);
        rnsAccessLevel .put("Alterar", rnsUpdateAccessLevel );
        rnsAccessLevel .put("Excluir", rnsDropAccessLevel );
        rnsAccessLevel .put("Inativar", rnsInactiveAccessLevel );
        rnsAccessLevel .put("Ativar", rnsActiveAccessLevel );

        /* Adiciona o mapa com as regras indexadas pelas operações no mapa geral indexado
         * pelo nome da entidade
		 */
        rns.put(cAccessLevel.class.getName(), rnsAccessLevel);
        rns.put(cTypeAddress.class.getName(), rnsTypeAddress);
        rns.put(cTypeHouse.class.getName(), rnsTypeHouse);
        rns.put(cTypePhone.class.getName(), rnsTypePhone);
        rns.put(cTypeStreet.class.getName(), rnsTypeStreet);


    }

    @Override
    public cResult create(cEntityDomain objEntityDomain) {
        objResult = new cResult();

        String nmClass = objEntityDomain.getClass().getName();

        String msg = executeRules(objEntityDomain, "Salvar");

        if (msg == null) {
            iDao dao = daos.get(nmClass);
            try {
                dao.create(objEntityDomain);
                List<cEntityDomain> objEntitiesDomain = new ArrayList<cEntityDomain>();
                objEntitiesDomain.add(objEntityDomain);
                objResult.setEntities(objEntitiesDomain);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro de SQL ao obter as entidades!");
                objResult.setMsg("Não foi possivel realizar o registro!");
            }
        } else {
            objResult.setMsg(msg);
        }
        return objResult;
    }

    @Override
    public cResult update(cEntityDomain objEntityDomain) {

        objResult = new cResult();

        String nmClass = objEntityDomain.getClass().getName();

        String msg = executeRules(objEntityDomain, "Alterar");

        if (msg == null) {
            iDao dao = daos.get(nmClass);
            try {
                dao.update(objEntityDomain);
                List<cEntityDomain> objEntitiesDomain = new ArrayList<cEntityDomain>();
                objEntitiesDomain.add(objEntityDomain);
                objResult.setEntities(objEntitiesDomain);
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao executar a alteração de um registro!");
                objResult.setMsg("Não foi possivel realizar a alteração do registro!");
            }
        } else {
            objResult.setMsg(msg);
        }

        return objResult;
    }

    @Override
    public cResult drop(cEntityDomain objEntityDomain) {

        objResult = new cResult();

        String nmClass = objEntityDomain.getClass().getName();

        String msg = executeRules(objEntityDomain, "Excluir");

        if (msg == null) {
            iDao dao = daos.get(nmClass);
            try {
                dao.drop(objEntityDomain);
                List<cEntityDomain> objEntitiesDomain = new ArrayList<cEntityDomain>();
                objEntitiesDomain.add(objEntityDomain);
                objResult.setEntities(objEntitiesDomain);
            } catch (SQLException e) {
                e.printStackTrace();
                objResult.setMsg("Não foi excluir o registro!");
                System.out.println("Erro ao excluir um registro!");
            }
        } else {
            objResult.setMsg(msg);
        }

        return objResult;
    }

    @Override
    public cResult retrive(cEntityDomain objEntityDomain) {

        objResult = new cResult();

        String nmClass = objEntityDomain.getClass().getName();

        String msg = executeRules(objEntityDomain, "Consultar");

        if (msg == null) {
            iDao dao = daos.get(nmClass);
            try {
                objResult.setEntities(dao.retrive(objEntityDomain));
            } catch (SQLException e) {
                e.printStackTrace();
                objResult.setMsg("Não foi possivel realizar a consulta!");
                System.out.println("Erro ao efetuar a consulta!");
            }
        } else {
            objResult.setMsg(msg);
        }

        return objResult;
    }

    @Override
    public cResult view(cEntityDomain objEntityDomain) {

        objResult = new cResult();

        objResult.setEntities(new ArrayList<cEntityDomain>(1));
        objResult.getEntities().add(objEntityDomain);
        return objResult;
    }

    @Override
    public cResult active(cEntityDomain objEntityDomain) {

        objResult = new cResult();

        String nmClass = objEntityDomain.getClass().getName();

        String msg = executeRules(objEntityDomain, "Ativar");

        if (msg == null) {
            iDao dao = daos.get(nmClass);
            try {
                dao.active(objEntityDomain);
                List<cEntityDomain> objEntitiesDomain = new ArrayList<cEntityDomain>();
                objEntitiesDomain.add(objEntityDomain);
                objResult.setEntities(objEntitiesDomain);
            } catch (SQLException e) {
                e.printStackTrace();
                objResult.setMsg("Não foi excluir o registro!");
                System.out.println("Erro ao excluir um registro!");
            }
        } else {
            objResult.setMsg(msg);
        }

        return objResult;
    }

    @Override
    public cResult inactive(cEntityDomain objEntityDomain) {

        objResult = new cResult();

        String nmClass = objEntityDomain.getClass().getName();

        String msg = executeRules(objEntityDomain, "Inativar");

        if (msg == null) {
            iDao dao = daos.get(nmClass);
            try {
                dao.inactive(objEntityDomain);
                List<cEntityDomain> objEntitiesDomain = new ArrayList<cEntityDomain>();
                objEntitiesDomain.add(objEntityDomain);
                objResult.setEntities(objEntitiesDomain);
            } catch (SQLException e) {
                e.printStackTrace();
                objResult.setMsg("Não foi possivel inativar o registro!");
                System.out.println("Erro ao inativar um registro!");
            }
        } else {
            objResult.setMsg(msg);
        }

        return objResult;
    }

    private String executeRules(cEntityDomain objEntityDomain, String operation) {

        String nmClass = objEntityDomain.getClass().getName();

        StringBuilder msg = new StringBuilder();

        Map<String, List<iStrategy>> rulesOperation = rns.get(nmClass);

        if (rulesOperation != null) {
            List<iStrategy> rules = rulesOperation.get(operation);

            if (rules != null) {
                for (iStrategy s : rules) {
                    String m = s.process(objEntityDomain);

                    if (m != null) {
                        msg.append(m);
                        msg.append("\n");
                    }
                }
            }
        }

        if (msg.length() > 0) {
            return msg.toString();
        } else {
            return null;
        }
    }
}
