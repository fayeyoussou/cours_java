package sn.l2gl.youssoupha.hibernate_simple.exception;

/**
 * Exception métier levée lorsqu'on tente d'insérer un étudiant dont le matricule
 * est déjà utilisé. Non vérifiée (RuntimeException) : le contrôleur l'attrape
 * pour afficher un message à l'utilisateur.
 */
public class MatriculeDejaExistantException extends RuntimeException {

    public MatriculeDejaExistantException(String matricule) {
        super("Le matricule « " + matricule + " » existe déjà.");
    }
}
