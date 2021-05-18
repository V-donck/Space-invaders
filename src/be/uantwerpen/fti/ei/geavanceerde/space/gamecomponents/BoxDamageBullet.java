package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * bonusbox : bullets can have more damage
 */
abstract public class BoxDamageBullet extends BonusEntity{
    /**
     * create BoxDamageBullet
     * @param xcoord set x-coordinate in {@link MovementComponent}
     * set dy = 10
     */
    public BoxDamageBullet(int xcoord) {
        this.setMovementComponent(xcoord,0,0,10);
    }

    /**
     * visualise BoxDamageBullet
     */
    abstract public void visualise();

}
