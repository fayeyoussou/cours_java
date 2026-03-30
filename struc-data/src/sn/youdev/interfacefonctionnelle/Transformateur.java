package sn.youdev.interfacefonctionnelle;

@FunctionalInterface
public interface Transformateur<T,R> {
    R transform(T etat);
}
