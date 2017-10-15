package br.com.kedge.mylibrary.core.implementation.dao;

import br.com.kedge.mylibrary.core.util.acTableModels;
import br.com.kedge.mylibrary.domain.cEntityDomain;
import br.com.kedge.mylibrary.domain.entities.cAccessLevel;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class cAccessLevelDao extends acJdbcDao {
    public cAccessLevelDao() {
        super(acTableModels.accesslevel[0], acTableModels.accesslevel[1]);
    }

    public void create(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cAccessLevel objAccessLevel = (cAccessLevel) objEntityDomain;

        try {
            connection.setAutoCommit(false);
            StringBuilder sql = new StringBuilder();
            sql.append("INSERT INTO accesslevel (access,active) VALUES (?,?)");

            pst = connection.prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);
            pst.setString(1, objAccessLevel.getAccess());
            pst.setBoolean(2, objAccessLevel.isActive());

            pst.executeUpdate();

            ResultSet rs = pst.getGeneratedKeys();

            while (rs.next()) {
                objAccessLevel.setId(rs.getInt(1));
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
            System.out.println("Erro ao executar a inserção de um novo Nivel de acesso!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public void update(cEntityDomain objEntityDomain) {
        openConnection();
        PreparedStatement pst = null;
        cAccessLevel objAccessLevel = (cAccessLevel) objEntityDomain;

        try {
            connection.setAutoCommit(false);

            String sql = "UPDATE accesslevel SET access=?, active=? WHERE id=?";

            pst = connection.prepareStatement(sql);
            pst.setString(1, objAccessLevel.getAccess());
            pst.setBoolean(2, objAccessLevel.isActive());
            pst.setInt(3, objAccessLevel.getId());

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
                    objAccessLevel.getId() + " da Tabela AccessLevel!");
        } finally {
            closeConnection(pst, true);
        }
    }

    @Override
    public List<cEntityDomain> retrive(cEntityDomain objEntityDomain) {

        PreparedStatement pst = null;

        cAccessLevel objAccessLevel = (cAccessLevel) objEntityDomain;

        String sql = null;

        if (objAccessLevel.getAccess() == null) {
            objAccessLevel.setAccess("");
        }

        if (objAccessLevel.getId() == null && objAccessLevel.getAccess().equals("")) {
            sql = "SELECT * FROM accesslevel";
        } else if (objAccessLevel.getId() != null && objAccessLevel.getAccess().equals("")) {
            sql = "SELECT * FROM accesslevel WHERE id=?";
        } else if (objAccessLevel.getId() == null && !objAccessLevel.getAccess().equals("")) {
            sql = "SELECT * FROM accesslevel WHERE access like ?";
        }

        try {
            openConnection();
            pst = connection.prepareStatement(sql);

            if (objAccessLevel.getId() != null && objAccessLevel.getAccess().equals("")) {
                pst.setInt(1, objAccessLevel.getId());
            } else if (objAccessLevel.getId() == null && !objAccessLevel.getAccess().equals("")) {
                pst.setString(1, "%" + objAccessLevel.getAccess() + "%");
            }

            ResultSet rs = pst.executeQuery();

            List<cEntityDomain> objAccessLevels = new ArrayList<cEntityDomain>();
            while (rs.next()) {
                cAccessLevel ObjAccessLevel = new cAccessLevel();
                ObjAccessLevel.setId(rs.getInt("id"));
                ObjAccessLevel.setAccess(rs.getString("access"));
                ObjAccessLevel.setActive(rs.getBoolean("active"));

                objAccessLevels.add(ObjAccessLevel);
            }
            return objAccessLevels;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao consultar a tabela accesslevel!");
        } finally {
            closeConnection(pst, true);
        }
        return null;
    }


    public cEntityDomain getAccessLevelById(cEntityDomain objEntityDomain) {
        cAccessLevelDao objAccessLevelDao = new cAccessLevelDao();

        cAccessLevel objAccessLevel = (cAccessLevel) objEntityDomain;

        objAccessLevel = (cAccessLevel) objAccessLevelDao.retrive(objAccessLevel).get(0);

        return objAccessLevel;
    }
}
