package sn.youdev.model;

public class Note {
    private final Etudiant etudiant;
    private final Module module;
    private double valeur;

    public Note(Etudiant etudiant, Module module, double valeur) {
        if (etudiant == null) {
            throw new IllegalArgumentException("Etudiant obligatoire");
        }
        if (module == null) {
            throw new IllegalArgumentException("Module obligatoire");
        }
        this.etudiant = etudiant;
        this.module = module;
        setValeur(valeur);
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public Module getModule() {
        return module;
    }

    public double getValeur() {
        return valeur;
    }

    public void setValeur(double valeur) {
        if (valeur < 0 || valeur > 20) {
            throw new IllegalArgumentException("Note doit etre entre 0 et 20");
        }
        this.valeur = valeur;
    }

    public double points() {
        return valeur * module.getCoefficient();
    }
}
