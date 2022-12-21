package part3.self.view.exception;

public class CommandNotFoundException extends RuntimeException {
    public CommandNotFoundException() {
        super("적절한 커맨드를 찾지 못했다.");
    }
}
