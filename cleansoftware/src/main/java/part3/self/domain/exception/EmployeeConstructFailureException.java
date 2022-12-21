package part3.self.domain.exception;

public class EmployeeConstructFailureException extends RuntimeException {
    public EmployeeConstructFailureException(final String reason) {
        super("직원 생성 실패 : " + reason);
    }
}
