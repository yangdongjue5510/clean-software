package part3.self;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import part3.self.domain.WageRegistry;
import part3.self.domain.WageType;
import part3.self.domain.exception.InvalidWageRegistryException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("임금 정보는")
class WageRegistryTest {

    @ParameterizedTest(name = "급여가 {0}일 수 없다.")
    @NullSource
    @ValueSource(longs = {-1})
    @DisplayName("급여가 음수일 수 없다.")
    void constructEmployee_exception_negativeOrNullWage(final Long invalidWage) {
        // given, when, then
        assertThatThrownBy(() -> WageRegistry.builder()
                .wageType(WageType.HOURLY)
                .wage(invalidWage)
                .build())
                .isExactlyInstanceOf(InvalidWageRegistryException.class)
                .hasMessageContaining("급여");
    }

    @ParameterizedTest(name = "{0}인 경우 수수료율을 가질 수 없다.")
    @EnumSource(value = WageType.class, names = {"HOURLY", "SALARIED"})
    @DisplayName("월급과 시급만 임금으로 받는 경우 수수료율을 가질 수 없다.")
    void constructEmployee_exception_hourlyAndSalariedCannotHaveFeeRate(final WageType invalidWageType) {
        // given, when, then
        assertThatThrownBy(() -> WageRegistry.builder()
                .wageType(invalidWageType)
                .wage(1000L)
                .feeRate(0.5)
                .build())
                .isExactlyInstanceOf(InvalidWageRegistryException.class)
                .hasMessageContaining("수수료율");
    }

    @Test
    @DisplayName("수수료율을 받을 수 있는 경우 수수료율이 null이면 안된다.")
    void constructEmployee_exception_commissionedWithoutFeeRate() {
        // given, when, then
        assertThatThrownBy(() -> WageRegistry.builder()
                .wageType(WageType.COMMISSIONED)
                .wage(1000L)
                .feeRate(null)
                .build())
                .isExactlyInstanceOf(InvalidWageRegistryException.class)
                .hasMessageContaining("수수료율");
    }
}
