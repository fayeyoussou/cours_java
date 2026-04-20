package sn.youdev.exception;

// Exception métier levée quand une note est hors de la plage autorisée [0, 20]
public class NoteInvalideException extends Exception {
    private final double valeurRefusee;

    public NoteInvalideException(double valeur) {
        super("Note invalide : " + valeur + " (limites: 0-20)");
        this.valeurRefusee = valeur;
    }

    public NoteInvalideException(String message) {
        super(message);
        this.valeurRefusee = -1;
    }

    public double getValeurRefusee() { return valeurRefusee; }
}
