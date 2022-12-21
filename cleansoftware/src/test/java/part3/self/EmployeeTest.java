package part3.self;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import part3.self.domain.Employee;
import part3.self.domain.WageType;
import part3.self.domain.exception.EmployeeConstructFailureException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

@DisplayName("직원은 ")
class EmployeeTest {

    @Test
    @DisplayName("반드시 직원 번호를 가져야 한다.")
    void constructEmployee_exception_nullId() {
        // given, when, then
        assertThatThrownBy(() -> Employee.builder()
                .id(null)
                .name("name")
                .address("address")
                .wageType(WageType.HOURLY)
                .wage(1000L)
                .build())
                .isExactlyInstanceOf(EmployeeConstructFailureException.class)
                .hasMessageContaining("직원번호");
    }

    @DisplayName("이름이 공백이거나 빈 문자열이거나 null 일 수 없다.")
    @ParameterizedTest(name = "이름이 {0} 일 수 없다.")
    @NullSource
    @EmptySource
    @ValueSource(strings = {" "})
    void constructEmployee_exception_emptyOrBlankOrNullName(final String invalidName) {
        // given, when, then
        assertThatThrownBy(() -> Employee.builder()
                .id(1L)
                .name(invalidName)
                .address("address")
                .wageType(WageType.HOURLY)
                .wage(1000L)
                .build())
                .isExactlyInstanceOf(EmployeeConstructFailureException.class)
                .hasMessageContaining("이름");
    }

    @DisplayName("주소가 공백이거나 빈 문자열일 수 없다.")
    @ParameterizedTest(name = "주소가 {0} 일 수 없다.")
    @NullSource
    @EmptySource
    @ValueSource(strings = {" "})
    void constructEmployee_exception_emptyOrBlankOrNullAddress(final String invalidAddress) {
        // given, when, then
        assertThatThrownBy(() -> Employee.builder()
                .id(1L)
                .name("name")
                .address(invalidAddress)
                .wageType(WageType.HOURLY)
                .wage(1000L)
                .build())
                .isExactlyInstanceOf(EmployeeConstructFailureException.class)
                .hasMessageContaining("주소");
    }

}
