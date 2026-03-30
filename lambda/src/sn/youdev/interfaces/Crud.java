package sn.youdev.interfaces;

public interface Crud<T> {
    T creer(T personne);
    T[] lister();
    T[] update(T personne);
    void delete(T personne);
}
