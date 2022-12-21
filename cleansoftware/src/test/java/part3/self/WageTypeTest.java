package part3.self;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import part3.self.domain.WageType;
import part3.self.domain.exception.WageTypeNotMatchException;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
@DisplayName("임금 방식은")
class WageTypeTest {

    @ParameterizedTest(name = "{0}은 {1}로 생성할 수 있다.")
    @MethodSource(value = "inputWithMatchedWageType")
    @DisplayName("입력값이 H, S, C인 경우 시급, 월급, 월급+판매 수수료 방식으로 변환한다.")
    void constructWageType(final String input, final WageType actual) {
        // given, when
        final WageType expect = WageType.of(input);

        // then
        assertThat(expect).isEqualTo(actual);
    }

    private static Stream<Arguments> inputWithMatchedWageType() {
        return Stream.of(
                Arguments.arguments("H", WageType.HOURLY),
                Arguments.arguments("S", WageType.SALARIED),
                Arguments.arguments("C", WageType.COMMISSIONED)
        );
    }

    @ParameterizedTest(name = "{0}는 임금 유형으로 생성 할 수 없다.")
    @NullSource
    @ValueSource(strings = {"a", "ㄱ"})
    @DisplayName("입력값이 H, S, C으로만 생성 가능하다.")
    void constructWageType_exception_notMatchedWageType(final String invalidInput) {
        // given, when, then
        assertThatThrownBy(() -> WageType.of(invalidInput))
                .isExactlyInstanceOf(WageTypeNotMatchException.class)
                .hasMessageContaining("임금 방식이 맞지 않음");
    }
}
