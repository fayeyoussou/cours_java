package sn.youdev.model;

@FunctionalInterface
public interface Affichable {
     void afficher(String message);
    default public void showMessage(String message){
        afficher(message);
    }
}
