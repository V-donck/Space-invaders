package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Bullet extends Entity{
    private int damage;

    public Bullet(int damage) {
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }
}
