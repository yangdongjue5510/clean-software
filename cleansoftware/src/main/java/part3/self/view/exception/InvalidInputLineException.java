package part3.self.view.exception;

public class InvalidInputLineException extends RuntimeException {
    public InvalidInputLineException(final String reason) {
        super("입력이 잘못됐음 : " + reason);
    }
}
