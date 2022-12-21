package part3.self.domain;

import part3.self.domain.exception.WageTypeNotMatchException;
import java.util.Arrays;

public enum WageType {
    HOURLY("H"),
    SALARIED("S"),
    COMMISSIONED("C");

    private final String wageTypeCommand;

    WageType(final String wageTypeCommand) {
        this.wageTypeCommand = wageTypeCommand;
    }

    public static WageType of(final String input) {
        return Arrays.stream(values())
                .filter(wageType -> wageType.wageTypeCommand.equals(input))
                .findFirst()
                .orElseThrow(WageTypeNotMatchException::new);
    }
}
