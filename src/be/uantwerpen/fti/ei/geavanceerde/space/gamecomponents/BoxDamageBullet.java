package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * bonusbox : bullets can have more damage
 */
abstract public class BoxDamageBullet extends BonusEntity{

    /**
     * creates BoxDamageBullet<br>
     * set dy = 10
     * @param xCoord set x-coordinate in {@link MovementComponent}
     */
    public BoxDamageBullet(int xCoord) {
        this.setMovementComponent(xCoord,0,0,10);
    }

    /**
     * visualise BoxDamageBullet
     */
    abstract public void visualise();

}
