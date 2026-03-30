package sn.youdev.interfacefonctionnelle;
@FunctionalInterface
public interface Verificateur<T> {
    boolean verifier(T etat);
}
