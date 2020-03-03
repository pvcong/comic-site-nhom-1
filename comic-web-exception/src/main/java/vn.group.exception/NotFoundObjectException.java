package vn.group.exception;

public class NotFoundObjectException extends RuntimeException{
    public NotFoundObjectException(String id) {
        super("No " + id + " found in the database" );
    }
}
