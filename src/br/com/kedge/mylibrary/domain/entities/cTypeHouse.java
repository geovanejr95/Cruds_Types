package br.com.kedge.mylibrary.domain.entities;

import br.com.kedge.mylibrary.domain.cEntityDomain;

public class cTypeHouse extends cEntityDomain {

    private String type;

    public cTypeHouse() {
    }

    public cTypeHouse(int id, boolean active, String type) {
        super(id, active);

        this.setType(type);
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
