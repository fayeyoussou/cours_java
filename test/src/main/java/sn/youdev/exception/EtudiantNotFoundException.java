package sn.youdev.exception;

// Levée quand aucun étudiant ne correspond à l'identifiant recherché
public class EtudiantNotFoundException extends RuntimeException {
    public EtudiantNotFoundException(String matricule) {
        super("Aucun étudiant trouvé avec le matricule : " + matricule);
    }
}
