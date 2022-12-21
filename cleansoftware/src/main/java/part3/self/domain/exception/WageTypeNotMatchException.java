package part3.self.domain.exception;

public class WageTypeNotMatchException extends RuntimeException {
    public WageTypeNotMatchException() {
        super("임금 방식이 맞지 않음");
    }
}
