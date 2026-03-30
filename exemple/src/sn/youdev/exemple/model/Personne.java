package sn.youdev.exemple.model;

import java.util.Objects;

public final  class Personne {
    String nom;
    final String matricule;

    public Personne(String matricule) {
        this.matricule = matricule;
    }

    public  void afficher(){}

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Personne personne = (Personne) o;
        return matricule == personne.matricule;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(matricule);
    }
}
