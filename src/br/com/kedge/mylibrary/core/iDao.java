package br.com.kedge.mylibrary.core;

import br.com.kedge.mylibrary.domain.cEntityDomain;

import java.sql.SQLException;
import java.util.List;

public interface iDao {
    public void create(cEntityDomain entity) throws SQLException;

    public void update(cEntityDomain entity) throws SQLException;

    public void drop(cEntityDomain entity) throws SQLException;

    public void active(cEntityDomain entity) throws SQLException;

    public void inactive(cEntityDomain entity) throws SQLException;

    public List<cEntityDomain> retrive(cEntityDomain entity) throws SQLException;
}
