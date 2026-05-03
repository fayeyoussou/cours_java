package sn.l2g2.faye.jdbc.exception;

public class HibernateConfigException extends RuntimeException {
    public HibernateConfigException(String message ) {
        super(message);
    }
    public HibernateConfigException(Exception e ) {
        super(e.getMessage());
    }

}
