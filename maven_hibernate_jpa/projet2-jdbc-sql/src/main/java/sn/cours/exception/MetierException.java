package sn.cours.exception;

// Exception de base pour toutes les erreurs métier de l'application
public class MetierException extends RuntimeException {

    public MetierException(String message) {
        super(message);
    }

    public MetierException(String message, Throwable cause) {
        super(message, cause);
    }
}
