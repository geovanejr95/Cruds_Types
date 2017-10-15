package br.com.kedge.mylibrary.core.implementation.dao;

import br.com.kedge.mylibrary.core.util.acTableModels;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypeAddress;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class cTypeAddressDao extends acJdbcDao {

    public cTypeAddressDao() {
        super(acTableModels.typeaddress[0], acTableModels.typeaddress[1]);
    }

    public void create(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cTypeAddress objTypeAddress = (cTypeAddress) objEntityDomain;

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO type_address (type, active) VALUES (?,?)";

            pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, objTypeAddress.getType());
            pst.setBoolean(2, objTypeAddress.isActive());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            while (rs.next()) {
                objTypeAddress.setId(rs.getInt(1));
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
            System.out.println("Erro ao executar a inserção de um novo tipo de endereço!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public void update(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cTypeAddress objTypeAddress = (cTypeAddress) objEntityDomain;

        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE type_address SET type=?, active=? WHERE id=?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, objTypeAddress.getType());
            pst.setBoolean(2, objTypeAddress.isActive());
            pst.setInt(3, objTypeAddress.getId());


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
                    objTypeAddress.getId() + " da Tabela type_address!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public List<cEntityDomain> retrive(cEntityDomain objEntityDomain) {

        PreparedStatement pst = null;

        cTypeAddress objTypeAddress = (cTypeAddress) objEntityDomain;

        String sql = null;

        if (objTypeAddress.getType() == null) {
            objTypeAddress.setType("");
        }

        if (objTypeAddress.getId() == null && objTypeAddress.getType().equals("")) {
            sql = "SELECT * FROM type_address";
        } else if (objTypeAddress.getId() != null && objTypeAddress.getType().equals("")) {
            sql = "SELECT * FROM type_address WHERE id=?";
        } else if (objTypeAddress.getId() == null && !objTypeAddress.getType().equals("")) {
            sql = "SELECT * FROM type_address WHERE type like ?";
        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (objTypeAddress.getId() != null && objTypeAddress.getType().equals("")) {
                pst.setInt(1, objTypeAddress.getId());
            } else if (objTypeAddress.getId() == null && !objTypeAddress.getType().equals("")) {
                pst.setString(1, "%" + objTypeAddress.getType() + "%");
            }

            ResultSet rs = pst.executeQuery();

            List<cEntityDomain> objTypesAddress = new ArrayList<cEntityDomain>();
            while (rs.next()) {
                cTypeAddress ObjTypeAddress = new cTypeAddress();
                ObjTypeAddress.setId(rs.getInt("id"));
                ObjTypeAddress.setType(rs.getString("type"));
                ObjTypeAddress.setActive(rs.getBoolean("active"));

                objTypesAddress.add(ObjTypeAddress);
            }
            return objTypesAddress;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao consultar a tabela type_address!");
        } finally {
            closeConnection(pst, true);
        }
        return null;
    }
}
