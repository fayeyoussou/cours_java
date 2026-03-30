package sn.youdev.implementation;

import sn.youdev.interfaces.Crud;
import sn.youdev.interfaces.Personne;

public class CrudPersonne implements Crud<Personne> {
    @Override
    public Personne creer(Personne personne) {
        return null;
    }

    @Override
    public Personne[] lister() {
        return new Personne[0];
    }

    @Override
    public Personne[] update(Personne personne) {
        return new Personne[0];
    }

    @Override
    public void delete(Personne personne) {

    }
}
