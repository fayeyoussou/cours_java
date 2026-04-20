package sn.youdev.exception;

// Wrapper qui traduit une IOException technique en erreur métier de service
public class ServiceException extends Exception {

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
