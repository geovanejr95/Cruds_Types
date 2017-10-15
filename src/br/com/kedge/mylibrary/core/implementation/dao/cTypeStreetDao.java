package br.com.kedge.mylibrary.core.implementation.dao;

import br.com.kedge.mylibrary.core.util.acTableModels;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypeStreet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class cTypeStreetDao extends acJdbcDao {

    public cTypeStreetDao() {
        super(acTableModels.typestreet[0], acTableModels.typestreet[1]);
    }

    public void create(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cTypeStreet objTypeStreet = (cTypeStreet) objEntityDomain;

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO type_street (type, active) VALUES (?,?)";

            pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, objTypeStreet.getType());
            pst.setBoolean(2, objTypeStreet.isActive());

            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();

            while (rs.next()) {
                objTypeStreet.setId(rs.getInt(1));
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
            System.out.println("Erro ao executar a inserção de um novo tipo de logradouro!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public void update(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cTypeStreet objTypeStreet = (cTypeStreet) objEntityDomain;

        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE type_street SET type=?, active=? WHERE id=?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, objTypeStreet.getType());
            pst.setBoolean(2, objTypeStreet.isActive());
            pst.setInt(3, objTypeStreet.getId());

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
                    objTypeStreet.getId() + " da Tabela type_street!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public List<cEntityDomain> retrive(cEntityDomain objEntityDomain) {

        PreparedStatement pst = null;

        cTypeStreet objTypeStreet = (cTypeStreet) objEntityDomain;

        String sql = null;

        if (objTypeStreet.getType() == null) {
            objTypeStreet.setType("");
        }

        if (objTypeStreet.getId() == null && objTypeStreet.getType().equals("")) {
            sql = "SELECT * FROM type_street";
        } else if (objTypeStreet.getId() != null && objTypeStreet.getType().equals("")) {
            sql = "SELECT * FROM type_street WHERE id=?";
        } else if (objTypeStreet.getId() == null && !objTypeStreet.getType().equals("")) {
            sql = "SELECT * FROM type_street WHERE type like ?";
        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (objTypeStreet.getId() != null && objTypeStreet.getType().equals("")) {
                pst.setInt(1, objTypeStreet.getId());
            } else if (objTypeStreet.getId() == null && !objTypeStreet.getType().equals("")) {
                pst.setString(1, "%" + objTypeStreet.getType() + "%");
            }

            ResultSet rs = pst.executeQuery();

            List<cEntityDomain> objTypeStreets = new ArrayList<cEntityDomain>();
            while (rs.next()) {
                cTypeStreet ObjTypeStreet = new cTypeStreet();
                ObjTypeStreet.setId(rs.getInt("id"));
                ObjTypeStreet.setType(rs.getString("type"));
                ObjTypeStreet.setActive(rs.getBoolean("active"));

                objTypeStreets.add(ObjTypeStreet);
            }
            return objTypeStreets;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao consultar a tabela type_street!");
        } finally {
            closeConnection(pst, true);
        }
        return null;
    }
}
