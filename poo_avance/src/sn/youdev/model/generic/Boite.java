package sn.youdev.model.generic;

// ============================================================
// EXEMPLE 1 : Classe générique de base
// Une boîte qui peut contenir N'IMPORTE QUEL type d'objet
// Sans générique, on serait obligé de créer BoiteString, BoiteInteger, etc.
// ============================================================
public class Boite<T> {
    private T element;

    public Boite(T element) {
        this.element = element;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    @Override
    public String toString() {
        return "Boite contient : " + element;
    }
}
