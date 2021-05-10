package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Ship extends Entity {
    private int HP;

    /**
     * @param hp hp of Ship
     */
    public Ship(int hp) {
        this.HP = hp;
    }

    /**
     * @return Hp from ship
     */
    public int getHP() {
        return HP;
    }

    /**
     * @param HP set hp of ship to HP
     */
    public void setHP(int HP) {
        this.HP = HP;
    }
}
