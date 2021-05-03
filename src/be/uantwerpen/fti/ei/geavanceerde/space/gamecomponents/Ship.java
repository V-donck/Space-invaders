package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Ship extends Entity {
    private int HP;

    public Ship(int hp) {
        this.HP = hp;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }
}
