package sn.l2g2.faye.jdbc.exception;

import sn.l2g2.faye.jdbc.model.Etudiant;

public class EtudiantException extends RuntimeException {
    EtudiantException(String message) {
        super(message);
    }
    public EtudiantException(String message, Throwable cause) {
        super(message, cause);
    }
    public EtudiantException(Etudiant e){
        super("erreur sur l'etudiant"+e);
    }

}
