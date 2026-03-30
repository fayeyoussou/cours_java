package sn.youdev.model;

public class EtudiantModele extends Etudiant {
    private String parcours;

    public EtudiantModele(String matricule, String nom, String prenom, String parcours) {
        super(matricule, nom, prenom);
        setParcours(parcours);
    }

    public String getParcours() {
        return parcours;
    }

    public void setParcours(String parcours) {
        if (parcours == null || parcours.isBlank()) {
            throw new IllegalArgumentException("Parcours invalide");
        }
        this.parcours = parcours.trim();
    }
}
