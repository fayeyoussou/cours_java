package sn.l2gl.youssoupha.exception;

public class EtudiantNonTrouveException extends RuntimeException{
    public EtudiantNonTrouveException(String matricule) {
        super("L'étudiant avec le matricule %s n'a pas ete trouvée".formatted(matricule));
    }
}
