package sn.youdev.java.avance.model.record;

public record Rectangle(int largeur, int hauteur) {
    // Le constructeur compact peut vérifier les valeurs.
    public Rectangle {
        if (largeur <= 0 || hauteur <= 0) {
            throw new IllegalArgumentException("Dimensions invalides");
        }
    }

    // Un record peut avoir un constructeur secondaire.
    public Rectangle(int cote) {
        this(cote, cote);
    }

    // Un record peut aussi avoir des méthodes d'instance.
    public int aire() {
        return largeur * hauteur;
    }
}
