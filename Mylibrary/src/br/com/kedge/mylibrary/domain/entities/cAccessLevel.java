package br.com.kedge.mylibrary.domain.entities;

import br.com.kedge.mylibrary.domain.cEntityDomain;

public class cAccessLevel extends cEntityDomain {

    private String access;

    public cAccessLevel() {
    }

    public cAccessLevel(int id, boolean active, String access) {
        super(id, active);

        this.setAccess(access);
    }

    public String getAccess() {
        return access;
    }

    public void setAccess(String access) {
        this.access = access;
    }
}
