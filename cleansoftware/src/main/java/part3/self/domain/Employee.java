package part3.self.domain;

import part3.self.domain.exception.EmployeeConstructFailureException;

public class Employee {
    private final Long id;
    private final String name;
    private final String address;
    private final WageRegistry wageRegistry;

    public Employee(final Long id, final String name, final String address, final WageType wageType, final Long wage,
                    final Double feeRate) {
        if (id == null) {
            throw new EmployeeConstructFailureException("직원번호 없음");
        }
        if (name == null || name.isBlank()) {
            throw new EmployeeConstructFailureException("이름이 없거나 공백이면 안됨");
        }
        if (address == null || address.isBlank()) {
            throw new EmployeeConstructFailureException("주소가 없거나 공백이면 안됨");
        }
        this.wageRegistry = WageRegistry.builder()
                .wageType(wageType)
                .wage(wage)
                .feeRate(feeRate)
                .build();
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public static class Builder {
        private Long id;
        private String name;
        private String address;
        private WageType wageType;
        private Long wage;
        private Double feeRate;

        public Builder id(final Long id) {
            this.id = id;
            return this;
        }

        public Builder name(final String name) {
            this.name = name;
            return this;
        }

        public Builder address(final String address) {
            this.address = address;
            return this;
        }

        public Builder wageType(final WageType wageType) {
            this.wageType = wageType;
            return this;
        }

        public Builder wage(final Long wage) {
            this.wage = wage;
            return this;
        }

        public Builder feeRate(final Double feeRate) {
            this.feeRate = feeRate;
            return this;
        }

        public Employee build() {
           return new Employee(this.id, this.name, this.address, this.wageType, this.wage, feeRate);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
