package sn.youdev.java.avance.model.record;


public record Note(double valeur)  implements Affichable{
    // Constructeur compact (pas de parenthèses)
    public Note {
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("Note invalide : " + valeur);
        }
        // Normalisation possible : valeur = Math.round(valeur);
    }

    @Override
    public void afficher() {
        IO.println("Note : "+valeur);
    }
}