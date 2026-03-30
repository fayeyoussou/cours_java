package sn.youdev.interfaces;

public interface CrudPersonne {
    Personne creer(Personne personne);
    Personne[] lister();
    Personne[] update(Personne personne);
    void delete(Personne personne);
}
