package sn.youdev.exception;

// Exception levée quand aucun étudiant ne correspond au matricule recherché
public class EtudiantInexistantException extends Exception {

    public EtudiantInexistantException(String message) {
        super(message);
    }
    public EtudiantInexistantException(Integer idEtudiant) {
        super("L'etudiant matricule "+idEtudiant+" n'existe pas");
    }
}
