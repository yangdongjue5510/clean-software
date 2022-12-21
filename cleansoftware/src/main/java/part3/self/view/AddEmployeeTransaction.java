package part3.self.view;

import java.util.Map;
import part3.self.domain.Employee;
import part3.self.domain.WageType;
import part3.self.view.exception.InvalidInputLineException;

public class AddEmployeeTransaction implements Transaction {
    private final Long id;
    private final String name;
    private final String address;
    private final Long wage;
    private final WageType wageType;
    private final Double feeRate;

    public AddEmployeeTransaction(final String line) {
        validate(line);
        final String[] tokens = line.split(" ");
        this.id = Long.parseLong(tokens[1]);
        this.name = tokens[2].substring(1, tokens[2].length() - 1);
        this.address = tokens[3].substring(1, tokens[3].length() - 1);
        this.wageType = WageType.of(tokens[4]);
        this.wage = Long.parseLong(tokens[5]);
        this.feeRate = wageType == WageType.COMMISSIONED ? Double.valueOf(tokens[6]) : null;
    }

    private void validate(final String line) {
        if (line == null || line.isBlank()) {
            throw new InvalidInputLineException("빈 트랜잭션은 올 수 없음");
        }
        final String[] tokens = line.split(" ");
        final Command command = Command.of(tokens[0]);
        if (command != Command.ADD) {
            throw new InvalidInputLineException("커맨드가 잘못 적용");
        }
        validateTokenCount(tokens);
    }

    private void validateTokenCount(final String[] tokens) {
        if (tokens.length < 5) {
            throw new InvalidInputLineException("트랜잭션 매개변수 갯수가 맞지 않음");
        }
        final WageType wageType = WageType.of(tokens[4]);
        if (wageType == WageType.COMMISSIONED && tokens.length != 7) {
            throw new InvalidInputLineException("수수료 임금 방식의 매개변수 갯수가 맞지 않음");
        }
        if (wageType != WageType.COMMISSIONED && tokens.length != 6) {
            throw new InvalidInputLineException("월급 및 시급 방식의 매개변수 갯수가 맞지 않음");
        }
    }

    @Override
    public void execute(Map<Long, Employee> employees) {
        final Employee employee = Employee.builder()
                .id(id)
                .name(name)
                .address(address)
                .wage(wage)
                .wageType(wageType)
                .feeRate(feeRate)
                .build();
        employees.put(id, employee);
    }
}
