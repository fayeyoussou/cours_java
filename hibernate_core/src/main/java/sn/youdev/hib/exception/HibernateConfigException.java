package sn.youdev.hib.exception;

public class HibernateConfigException extends RuntimeException {
    public HibernateConfigException(Exception cause) {
        super(cause.getMessage(), cause);
    }
    HibernateConfigException(String message) {
        super(message);
    }
}
