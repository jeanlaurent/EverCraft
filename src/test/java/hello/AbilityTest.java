package hello;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AbilityTest {

    @Test
    public void should_have_default_value_of_10() {
        assertThat(new Ability().value()).isEqualTo(10);
    }

    @Test
    public void should_not_be_higher_than_20() {
        assertThat(new Ability(30).value()).isEqualTo(20);
    }

    @Test
    public void should_not_be_lower_than_1() {
        assertThat(new Ability(-10).value()).isEqualTo(1);
    }

    @Test
    public void check_modifiers() {
        assertThat(new Ability( 1).modifier()).isEqualTo(-5);
        assertThat(new Ability( 2).modifier()).isEqualTo(-4);
        assertThat(new Ability( 3).modifier()).isEqualTo(-4);
        assertThat(new Ability( 4).modifier()).isEqualTo(-3);
        assertThat(new Ability( 5).modifier()).isEqualTo(-3);
        assertThat(new Ability( 6).modifier()).isEqualTo(-2);
        assertThat(new Ability( 7).modifier()).isEqualTo(-2);
        assertThat(new Ability( 8).modifier()).isEqualTo(-1);
        assertThat(new Ability( 9).modifier()).isEqualTo(-1);
        assertThat(new Ability(10).modifier()).isEqualTo( 0);
        assertThat(new Ability(11).modifier()).isEqualTo( 0);
        assertThat(new Ability(12).modifier()).isEqualTo( 1);
        assertThat(new Ability(13).modifier()).isEqualTo( 1);
        assertThat(new Ability(14).modifier()).isEqualTo( 2);
        assertThat(new Ability(15).modifier()).isEqualTo( 2);
        assertThat(new Ability(16).modifier()).isEqualTo( 3);
        assertThat(new Ability(17).modifier()).isEqualTo( 3);
        assertThat(new Ability(18).modifier()).isEqualTo( 4);
        assertThat(new Ability(19).modifier()).isEqualTo( 4);
        assertThat(new Ability(20).modifier()).isEqualTo( 5);
    }

}
