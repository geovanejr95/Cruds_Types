package br.com.kedge.mylibrary.core.aplication;

import br.com.kedge.mylibrary.domain.cEntityDomain;

import java.util.List;

public class cResult {
    private String msg;
    private List<cEntityDomain> entities;


    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<cEntityDomain> getEntities() {
        return entities;
    }

    public void setEntities(List<cEntityDomain> entities) {
        this.entities = entities;
    }
}