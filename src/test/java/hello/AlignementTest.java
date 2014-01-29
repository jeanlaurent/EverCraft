package hello;

import org.junit.Test;

import static hello.Alignment.*;
import static org.assertj.core.api.Assertions.assertThat;

public class AlignementTest {

    @Test
    public void should_have_good_neutral_and_evil() {
        assertThat(values()).containsOnly(GOOD, EVIL, NEUTRAL);
    }

}
