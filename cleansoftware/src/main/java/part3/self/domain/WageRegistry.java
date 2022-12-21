package part3.self.domain;

import part3.self.domain.exception.InvalidWageRegistryException;

public class WageRegistry {

    private final WageType wageType;
    private final Long wage;
    private final Double feeRate;

    public WageRegistry(final WageType wageType, final Long wage, final Double feeRate) {
        if (wage == null || wage < 0) {
            throw new InvalidWageRegistryException("급여가 음수면 안됨");
        }
        if (!wageType.equals(WageType.COMMISSIONED) && feeRate != null) {
            throw new InvalidWageRegistryException("수수료율을 활용할 수 없는 경우인데 수수료율이 빈 값이어야 함");
        }
        if (wageType.equals(WageType.COMMISSIONED) && feeRate == null) {
            throw new InvalidWageRegistryException("임금 방식이 수수료율을 받아야 하는 경우 수수료율이 빈 값이면 안됨");
        }
        this.wageType = wageType;
        this.wage = wage;
        this.feeRate = feeRate;
    }

    public static class Builder {
        private WageType wageType;
        private Long wage;
        private Double feeRate;

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

        public WageRegistry build() {
            return new WageRegistry(wageType, wage, feeRate);
        }
    }

    public static Builder builder() {
        return new Builder();
    }
}
