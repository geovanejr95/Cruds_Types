package br.com.kedge.mylibrary.core.implementation.dao;

import br.com.kedge.mylibrary.core.iDao;
import br.com.kedge.mylibrary.core.util.cConnection;
import br.com.kedge.mylibrary.domain.cEntityDomain;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public abstract class acJdbcDao implements iDao {

    protected Connection connection;
    protected String table;
    protected String idTable;
    protected boolean ctrlTransaction = true;

    public acJdbcDao(Connection connection, String table, String idTable) {
        this.table = table;
        this.idTable = idTable;
        this.connection = connection;
    }

    public acJdbcDao(String table, String idTable) {
        this.table = table;
        this.idTable = idTable;
    }

    @Override
    public void drop(cEntityDomain objEntityDomain) {
        openConnection();
        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM ");
        sb.append(table);
        sb.append(" WHERE ");
        sb.append(idTable);
        sb.append("=");
        sb.append("?");

        closeConnection(alterSQL(sb, objEntityDomain), true);
    }

    @Override
    public void active(cEntityDomain objEntityDomain) {
        openConnection();

        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE ");
        sb.append(table);
        sb.append(" SET active=TRUE WHERE ");
        sb.append(idTable);
        sb.append("=");
        sb.append("?");

        ;
        closeConnection(alterSQL(sb, objEntityDomain), true);
    }

    @Override
    public void inactive(cEntityDomain objEntityDomain) {
        openConnection();
        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE ");
        sb.append(table);
        sb.append(" SET active=FALSE WHERE ");
        sb.append(idTable);
        sb.append("=");
        sb.append("?");

        closeConnection(alterSQL(sb, objEntityDomain), true);
    }

    protected void openConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = cConnection.getConnection();
            }
        } catch (SQLException se) {
            se.printStackTrace();
            System.out.println("Erro de SQL ao abrir a conexão com o banco de dados!");
        }
    }

    protected PreparedStatement alterSQL(StringBuilder sb, cEntityDomain objEntityDomain) {
        PreparedStatement pst = null;
        try {
            connection.setAutoCommit(false);
            pst = connection.prepareStatement(sb.toString());
            pst.setInt(1, objEntityDomain.getId());

            pst.executeUpdate();
            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
                System.out.println("Erro de SQL ao tentar efetuar o RollBack!");
            }
            e.printStackTrace();
            System.out.println("Erro de SQL ao tentar inativar o registro de Id: " + objEntityDomain.getId() +
                    " da Tabela: " + table + "!");
        }
        return pst;
    }

    protected void closeConnection(PreparedStatement pst, boolean close) {
        try {
            pst.close();
            if (close) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao tentar fechar a conexão com o banco de dados!");
        } catch (NullPointerException n) {
            n.printStackTrace();
            System.out.println("Erro de NPE tentar fechar a conexão com o banco de dados!");
        }
    }
}
