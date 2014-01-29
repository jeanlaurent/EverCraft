package hello;

import static java.lang.Math.exp;
import static java.lang.Math.max;

public class Player {
    private static final int CRITICAL_ROLL = 20;
    private static final int DEFAULT_ARMORCLASS = 10;
    private static final int DEFAULT_HITPOINT = 5;
    private static final int DEFAULT_LEVEL = 1;
    private static final int HITPOINT_INCREASE = 5;

    private final String name;
    private final Alignment alignment;
    private final Ability strength;
    private final Ability dexterity;
    private final Ability constitution;
    private final Ability wisdom;
    private final Ability intelligence;
    private final Ability charisma;
    private int armorClass;
    private int hitPoint;
    private int experiencePoints;
    private int level;

    public Player(String name, Alignment alignment) {
        this.name = name;
        this.alignment = alignment;
        this.strength = new Ability();
        this.dexterity = new Ability();
        this.constitution = new Ability();
        this.wisdom = new Ability();
        this.intelligence = new Ability();
        this.charisma = new Ability();
        armorClass = DEFAULT_ARMORCLASS;
        hitPoint = DEFAULT_HITPOINT;
        experiencePoints = 0;
        level = DEFAULT_LEVEL;
    }

    public Player(String name,
                  Alignment alignment,
                  int strength,
                  int dexterity,
                  int constitution,
                  int wisdom,
                  int intelligence,
                  int charisma) {
        this.name = name;
        this.alignment = alignment;
        this.strength = new Ability(strength);
        this.dexterity = new Ability(dexterity);
        this.constitution = new Ability(constitution);
        this.wisdom = new Ability(wisdom);
        this.intelligence = new Ability(intelligence);
        this.charisma = new Ability(charisma);
        armorClass = DEFAULT_ARMORCLASS + this.dexterity.modifier();
        hitPoint = max(DEFAULT_HITPOINT + this.constitution.modifier(), 1);
        experiencePoints = 0;
        level = DEFAULT_LEVEL;
    }

    public String getName() {
        return name;
    }

    public Alignment getAlignment() {
        return alignment;
    }

    public int getArmorClass() {
        return armorClass;
    }

    public int getHitPoint() {
        return hitPoint;
    }

    public void gainXP(int xp) {
        experiencePoints += xp;
        computeLevel();
    }

    public boolean attack(Player defender, int roll) {
        if (roll + toHitModifier() < defender.getArmorClass()) {
            return false;
        }
        gainXP(10);
        defender.takeHit(computeDamage(roll));
        return true;
    }

    private int toHitModifier() {
        return strength.modifier() + level / 2;
    }

    private void computeLevel() {
        int newLevel = (experiencePoints / 1000) + 1;
        if (newLevel > level) {
            level = newLevel;
            hitPoint += HITPOINT_INCREASE + constitution.modifier();
        }
    }

    private int computeDamage(int roll) {
        int damage = max(1 + strength.modifier(), 1);
        if (roll >= CRITICAL_ROLL) {
            damage *= 2;
        }
        return damage;
    }

    public void takeHit(int damage) {
        hitPoint -= damage;
    }

    public boolean isDead() {
        return hitPoint <= 0;
    }

    public Ability getStrength() {
        return strength;
    }

    public Ability getDexterity() {
        return dexterity;
    }

    public Ability getConstitution() {
        return constitution;
    }

    public Ability getWisdom() {
        return wisdom;
    }

    public Ability getIntelligence() {
        return intelligence;
    }

    public Ability getCharisma() {
        return charisma;
    }

    public int getExperiencePoints() {
        return experiencePoints;
    }

    public int getLevel() {
        return level;
    }
}
