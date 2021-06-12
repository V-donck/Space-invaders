package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * Player shoots this Bullet
 */
abstract public class PlayerBullet extends Bullet {

    /**
     * creates PlayerBullet
     * @param damage the damage of the {@link Bullet}
     * @param x x-coordinate for setting {@link MovementComponent}
     * @param y y-coordinate for setting {@link MovementComponent}
     * @param dx dx for setting {@link MovementComponent}
     * @param dy dy for setting {@link MovementComponent}
     */
    public PlayerBullet(int damage, int x, int y, int dx, int dy) {
        super(damage);
        this.setMovementComponent(x,y,dx,dy);
    }

    /**
     * visualises PlayerBullet
     */
    public abstract void visualise();
}
