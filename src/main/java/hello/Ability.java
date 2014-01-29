package hello;

public class Ability {
    private final int value;

    public Ability() {
        this.value = 10;
    }

    public Ability(int value) {
        if (value > 20) {
            value = 20;
        }
        if (value < 1) {
            value = 1;
        }
        this.value = value;
    }

    public int value() {
        return value;
    }

    public int modifier() {
        return value / 2 - 5;
    }
}
