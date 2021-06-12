package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * bonusbox, 10 hp extra
 */
abstract public class BoxHp extends BonusEntity{
    int hp;

    /**
     * creates BoxHp<br>
     * set dy = 10
     * @param xCoord set x-coordinate in {@link MovementComponent}
     */
    public BoxHp(int xCoord,int hp) {
        this.setMovementComponent(xCoord,0,0,10);
        this.hp = hp;
    }

    /**
     * get hp
     * @return int hp
     */
    public int getHp(){
        return hp;
    }

    /**
     * visualise BoxHp
     */
    abstract public void visualise();
}
