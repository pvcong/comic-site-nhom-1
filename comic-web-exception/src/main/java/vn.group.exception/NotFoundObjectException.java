package vn.group.exception;

public class NotFoundObjectException extends RuntimeException{
    public NotFoundObjectException(Integer id) {
        super("No id " + id + " found in the database" );
    }
}
