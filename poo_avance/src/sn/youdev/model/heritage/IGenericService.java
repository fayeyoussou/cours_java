package sn.youdev.model.heritage;

public interface IGenericService<T> {
    T creer(T professeur);
    T modifer(T professeur);
    T supprimer(T professeur);
    T[] lister();
}
