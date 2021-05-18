package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * super class for specific bullets
 */
abstract public class Bullet extends Entity{
    private int damage;

    /**
     * creates Bullet
     * @param damage set damage
     */
    public Bullet(int damage) {
        this.damage = damage;
    }

    /**
     * returns damage of bullet
     * @return damage
     */
    public int getDamage() {
        return damage;
    }

    /**
     * set damage
     * @param damage set damage
     */
    public void setDamage(int damage) {
        this.damage = damage;
    }
}
