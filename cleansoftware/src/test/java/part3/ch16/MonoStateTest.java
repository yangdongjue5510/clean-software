package part3.ch16;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class MonoStateTest {

    @Test
    void testInstance() {
        MonoState monoState = new MonoState();
        for (int x = 0; x < 10; x++) {
            monoState.setX(x);
            assertThat(x).isEqualTo(monoState.getX());
        }
    }

    @Test
    void testInstancesBehaveAsOne() {
        final MonoState m1 = new MonoState();
        final MonoState m2 = new MonoState();
        for (int x = 0; x < 10; x++) {
            m1.setX(x);
            assertThat(x).isEqualTo(m2.getX());
        }
    }
}
