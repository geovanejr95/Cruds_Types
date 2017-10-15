package br.com.kedge.mylibrary.core.implementation.dao;

import br.com.kedge.mylibrary.core.util.acTableModels;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cTypePhone;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class cTypePhoneDao extends acJdbcDao {

    public cTypePhoneDao() {
        super(acTableModels.typephone[0], acTableModels.typephone[1]);
    }

    public void create(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cTypePhone objTypePhone = (cTypePhone) objEntityDomain;

        try {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO type_phone (type,active) VALUES (?,?)";

            pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, objTypePhone.getType());
            pst.setBoolean(2, objTypePhone.isActive());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();
            while (rs.next()) {
                objTypePhone.setId(rs.getInt(1));
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
            System.out.println("Erro ao executar a inserção de um novo Tipo de Telefone!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public void update(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cTypePhone objTypePhone = (cTypePhone) objEntityDomain;

        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE type_phone SET type=?, active=? WHERE id=?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, objTypePhone.getType());
            pst.setBoolean(2, objTypePhone.isActive());
            pst.setInt(3, objTypePhone.getId());

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
                    objTypePhone.getId() + " da Tabela Type_phone!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public List<cEntityDomain> retrive(cEntityDomain objEntityDomain) {

        PreparedStatement pst;

        cTypePhone objTypePhone = (cTypePhone) objEntityDomain;

        String sql = null;

        if (objTypePhone.getType() == null) {
            objTypePhone.setType("");
        }

        if (objTypePhone.getId() == null && objTypePhone.getType().equals("")) {
            sql = "SELECT * FROM type_phone";
        } else if (objTypePhone.getId() != null && objTypePhone.getType().equals("")) {
            sql = "SELECT * FROM type_phone WHERE id=?";
        } else if (objTypePhone.getId() == null && !objTypePhone.getType().equals("")) {
            sql = "SELECT * FROM type_phone WHERE type like ?";
        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (objTypePhone.getId() != null && objTypePhone.getType().equals("")) {
                pst.setInt(1, objTypePhone.getId());
            } else if (objTypePhone.getId() == null && !objTypePhone.getType().equals("")) {
                pst.setString(1, "%" + objTypePhone.getType() + "%");
            }

            ResultSet rs = pst.executeQuery();

            List<cEntityDomain> objTypesPhone = new ArrayList<cEntityDomain>();
            while (rs.next()) {
                cTypePhone ObjTypePhone = new cTypePhone();
                ObjTypePhone.setId(rs.getInt("id"));
                ObjTypePhone.setType(rs.getString("type"));
                ObjTypePhone.setActive(rs.getBoolean("active"));

                objTypesPhone.add(ObjTypePhone);
            }
            return objTypesPhone;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao consultar a tabela type_phone!");
        }
        return null;
    }
}
