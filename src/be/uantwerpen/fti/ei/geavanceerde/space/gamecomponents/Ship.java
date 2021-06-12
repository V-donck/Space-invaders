package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * superclass: Ship
 */
abstract public class Ship extends Entity {
    private int HP;

    /**
     * creates Ship
     * @param hp hp of Ship
     */
    public Ship(int hp) {
        this.HP = hp;
    }

    /**
     * get hp
     * @return Hp from ship
     */
    public int getHP() {
        return HP;
    }

    /**
     * set hp
     * @param HP set hp of ship to HP
     */
    public void setHP(int HP) {
        this.HP = HP;
    }
}
