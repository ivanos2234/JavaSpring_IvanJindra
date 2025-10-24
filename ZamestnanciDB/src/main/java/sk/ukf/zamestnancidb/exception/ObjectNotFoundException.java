package sk.ukf.zamestnancidb.exception;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException(String objectType, Object id) {
        super(objectType + " with id " + id + " not found");
    }
}
