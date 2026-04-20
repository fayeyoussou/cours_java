package sn.youdev.java.avance.enums;

public enum TypeConteneur {
    VINGT(20),QUARANTE(40);
    private final int pied;

    public int getPied() {
        return pied;
    }

    TypeConteneur(int pied) {
        this.pied = pied;
    }


}
