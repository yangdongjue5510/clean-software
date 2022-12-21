package part3.self.domain.exception;

public class InvalidWageRegistryException extends RuntimeException {
    public InvalidWageRegistryException(final String message) {
        super(message);
    }
}
