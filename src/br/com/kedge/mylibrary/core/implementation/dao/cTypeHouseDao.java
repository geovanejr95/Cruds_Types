package br.com.kedge.mylibrary.core.implementation.dao;

import br.com.kedge.mylibrary.core.util.acTableModels;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypeHouse;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class cTypeHouseDao extends acJdbcDao {

    public cTypeHouseDao() {
        super(acTableModels.typehouse[0], acTableModels.typehouse[1]);
    }

    public void create(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cTypeHouse objTypeHouse = (cTypeHouse) objEntityDomain;

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO type_house (type,active) VALUES (?,?)";

            pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, objTypeHouse.getType());
            pst.setBoolean(2, objTypeHouse.isActive());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            while (rs.next()) {
                objTypeHouse.setId(rs.getInt(1));
            }

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
                System.out.println("Erro de SQL ao tentar efetuar o rollback!");
            }
            e.printStackTrace();
            System.out.println("Erro ao executar a inserção de um novo tipo de residencia!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public void update(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cTypeHouse objTypeHouse = (cTypeHouse) objEntityDomain;

        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE type_house SET type=?, active=? WHERE id=?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, objTypeHouse.getType());
            pst.setBoolean(2, objTypeHouse.isActive());
            pst.setInt(3, objTypeHouse.getId());

            pst.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
                System.out.println("Erro ao tentar executar o rollback!");
            }
            e.printStackTrace();
            System.out.println("Erro ao tentar alterar o registro de Id: " +
                    objTypeHouse.getId() + " da Tabela type_house!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public List<cEntityDomain> retrive(cEntityDomain objEntityDomain) {

        PreparedStatement pst;

        cTypeHouse objTypeHouse = (cTypeHouse) objEntityDomain;

        String sql = null;

        if (objTypeHouse.getType() == null) {
            objTypeHouse.setType("");
        }

        if (objTypeHouse.getId() == null && objTypeHouse.getType().equals("")) {
            sql = "SELECT * FROM type_house";
        } else if (objTypeHouse.getId() != null && objTypeHouse.getType().equals("")) {
            sql = "SELECT * FROM type_house WHERE id=?";
        } else if (objTypeHouse.getId() == null && !objTypeHouse.getType().equals("")) {
            sql = "SELECT * FROM type_house WHERE type like ?";
        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (objTypeHouse.getId() != null && objTypeHouse.getType().equals("")) {
                pst.setInt(1, objTypeHouse.getId());
            } else if (objTypeHouse.getId() == null && !objTypeHouse.getType().equals("")) {
                pst.setString(1, "%" + objTypeHouse.getType() + "%");
            }

            ResultSet rs = pst.executeQuery();

            List<cEntityDomain> objTypesHouse = new ArrayList<cEntityDomain>();
            while (rs.next()) {
                cTypeHouse ObjTypeHouse = new cTypeHouse();
                ObjTypeHouse.setId(rs.getInt("id"));
                ObjTypeHouse.setType(rs.getString("type"));
                ObjTypeHouse.setActive(rs.getBoolean("active"));

                objTypesHouse.add(ObjTypeHouse);
            }
            return objTypesHouse;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao consultar a tabela type_house!");
        }
        return null;
    }
}
