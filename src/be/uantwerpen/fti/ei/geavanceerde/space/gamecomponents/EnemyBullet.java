package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class EnemyBullet extends Bullet {

    /**
     * creates EnemyBullet
     * @param damage super: {@link Bullet}
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
     * abstract: visualises EnemyBullet
     */
    public abstract void visualise();
}
