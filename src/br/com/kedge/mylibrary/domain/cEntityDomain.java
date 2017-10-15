package br.com.kedge.mylibrary.domain;

public class cEntityDomain implements iEntity {

    private Integer id;

    private boolean active;

    public cEntityDomain() {

    }

    public cEntityDomain(int id, boolean active) {
        this.setId(id);
        this.setActive(active);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
