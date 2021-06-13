package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * EnemyShip shoots this bullet
 */
abstract public class EnemyBullet extends Bullet {

    /**
     * creates EnemyBullet
     * @param damage damage for super: {@link Bullet}
     * @param x for set {@link MovementComponent}
     * @param y for set {@link MovementComponent}
     * @param dx for set {@link MovementComponent}
     * @param dy for set {@link MovementComponent}
     */
    public EnemyBullet(int damage, int x, int y, int dx, int dy) {
        super(damage);
        this.setMovementComponent(x,y,dx,dy);
    }

    /**
     * visualises EnemyBullet
     */
    public abstract void visualise();
}