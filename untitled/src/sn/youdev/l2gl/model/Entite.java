package sn.youdev.l2gl.model;

public abstract class Entite {
    private final Long id;

    protected Entite(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    public abstract String afficher();

}
