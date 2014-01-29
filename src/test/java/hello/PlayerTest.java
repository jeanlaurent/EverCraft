package hello;

import org.junit.Test;

import static hello.Alignment.EVIL;
import static hello.Alignment.GOOD;
import static org.assertj.core.api.Assertions.assertThat;


public class PlayerTest {

    @Test
    public void should_set_and_get_the_character_name() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getName()).isEqualTo("Foo");
    }

    @Test
    public void should_set_and_get_the_alignement() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getAlignment()).isEqualTo(GOOD);
    }

    @Test
    public void should_have_an_armorclass_wich_default_to_10() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getArmorClass()).isEqualTo(10);
    }

    @Test
    public void should_have_5_default_hit_points() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getHitPoint()).isEqualTo(5);
    }

    @Test
    public void should_be_able_to_attack() {
        Player player = new Player("Foo", GOOD);
        Player defender = new Player("Bar", EVIL);
        assertThat(player.attack(defender, 20)).isTrue();
    }

    @Test
    public void should_be_able_to_attack_and_miss() {
        Player player = new Player("Foo", GOOD);
        Player defender = new Player("Bar", EVIL);
        assertThat(player.attack(defender, 9)).isFalse();
        assertThat(defender.getHitPoint()).isEqualTo(5);
    }

    @Test
    public void should_be_able_to_damage_when_hit() {
        Player player = new Player("Foo", GOOD);
        Player defender = new Player("Bar", EVIL);
        assertThat(player.attack(defender, 15)).isTrue();
        assertThat(defender.getHitPoint()).isEqualTo(4);
    }

    @Test
    public void should_be_able_to_do_critical_damage_when_hit() {
        Player player = new Player("Foo", GOOD);
        Player defender = new Player("Bar", EVIL);
        assertThat(player.attack(defender, 20)).isTrue();
        assertThat(defender.getHitPoint()).isEqualTo(3);
    }

    @Test
    public void player_should_be_alive() {
        assertThat(new Player("foo", EVIL).isDead()).isFalse();
    }

    @Test
    public void player_should_be_dead_when_hitPoint_reach_0() {
        Player player = new Player("Foo", GOOD);
        player.takeHit(5);
        assertThat(player.isDead()).isTrue();
    }

    @Test
    public void should_have_strength_ability_which_default_to_10() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getStrength().value()).isEqualTo(10);
    }

    @Test
    public void should_have_dexterity_ability_which_default_to_10() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getDexterity().value()).isEqualTo(10);
    }

    @Test
    public void should_have_constitution_ability_which_default_to_10() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getConstitution().value()).isEqualTo(10);
    }

    @Test
    public void should_have_wisdom_ability_which_default_to_10() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getWisdom().value()).isEqualTo(10);
    }

    @Test
    public void should_have_Intelligence_ability_which_default_to_10() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getIntelligence().value()).isEqualTo(10);
    }

    @Test
    public void should_have_charisma_ability_which_default_to_10() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getCharisma().value()).isEqualTo(10);
    }

    @Test
    public void should_have_constitution_modifiers_applied_to_hitPoint() {
        Player player = new Player("Foo", GOOD, 10, 10, 20, 10, 10, 10);
        assertThat(player.getHitPoint()).isEqualTo(10);
    }

    @Test
    public void should_have_constitution_modifiers_applied_to_hitPoint_cant_go_below_1() {
        Player player = new Player("Foo", GOOD, 10, 10, 1, 10, 10, 10);
        assertThat(player.getHitPoint()).isEqualTo(1);
    }

    @Test
    public void should_have_dexterity_modifiers_applied_to_armorclass() {
        Player player = new Player("Foo", GOOD, 10, 20, 10, 10, 10, 10);
        assertThat(player.getArmorClass()).isEqualTo(15);
    }

    @Test
    public void should_have_modifiers_applied_to_toHitRoll_and_damage() {
        Player player = new Player("Foo", GOOD, 14, 10, 10, 10, 10, 10);
        Player defender = new Player("Bar", EVIL);
        assertThat(player.attack(defender, 9)).isTrue();
        assertThat(defender.getHitPoint()).isEqualTo(2);
    }

    @Test
    public void should_have_modifiers_applied_to_to_Criticaldamage() {
        Player player = new Player("Foo", GOOD, 14, 10, 10, 10, 10, 10);
        Player defender = new Player("Bar", EVIL, 10, 10, 14, 10, 10, 10);
        assertThat(player.attack(defender, 20)).isTrue();
        assertThat(defender.getHitPoint()).isEqualTo(1);
    }

    @Test
    public void should_have_modifiers_applied_to_damage_but_never_below_1() {
        Player player = new Player("Foo", GOOD, 1, 10, 10, 10, 10, 10);
        Player defender = new Player("Bar", EVIL);
        assertThat(player.attack(defender, 19)).isTrue();
        assertThat(defender.getHitPoint()).isEqualTo(4);
    }

    @Test
    public void should_gain_xp_when_hitting_another_player() {
        Player player = new Player("Foo", GOOD);
        Player defender = new Player("Bar", EVIL);
        player.attack(defender, 10);
        assertThat(player.getExperiencePoints()).isEqualTo(10);
    }

    @Test
    public void should_ding_level_every_1000xp() {
        Player player = new Player("Foo", GOOD);
        assertThat(player.getLevel()).isEqualTo(1);
        player.gainXP(1000);
        assertThat(player.getLevel()).isEqualTo(2);
        player.gainXP(1000);
        assertThat(player.getLevel()).isEqualTo(3);
    }

    @Test
    public void should_increase_hitPoint_by_5_when_dinging() {
        Player player = new Player("Foo", GOOD);
        player.gainXP(1000);
        assertThat(player.getHitPoint()).isEqualTo(10);
    }

    @Test
    public void should_increase_hitPoint_by_5_when_dinging_taking_modifiers_into_account() {
        Player player = new Player("Foo", GOOD, 10, 10, 20, 10, 10, 10);
        player.gainXP(1000);
        assertThat(player.getHitPoint()).isEqualTo(20);
    }

    @Test
    public void should_increase_toHitModifier_by_1_when_dinging_on_even_number() {
        Player player = new Player("Foo", GOOD);
        Player defender = new Player("Bar", EVIL);
        assertThat(player.attack(defender, 9)).isFalse();
        player.gainXP(1000);
        assertThat(player.attack(defender, 9)).isTrue();
    }

    @Test
    public void should_increase_toHitModifier_by_1_when_dinging_on_even_number_with_level4() {
        Player player = new Player("Foo", GOOD);
        Player defender = new Player("Bar", EVIL);
        player.gainXP(1000);
        assertThat(player.attack(defender, 8)).isFalse();
        player.gainXP(2000);
        assertThat(player.attack(defender, 8)).isTrue();
    }

}
