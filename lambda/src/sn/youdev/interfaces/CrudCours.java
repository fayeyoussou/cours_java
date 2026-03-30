package sn.youdev.interfaces;

public interface CrudCours {
    Cours creer(Cours personne);
    Cours[] lister();
    Cours update(Cours personne);
    void delete(Cours personne);
}
