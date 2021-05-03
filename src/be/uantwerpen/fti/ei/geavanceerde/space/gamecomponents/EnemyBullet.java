package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class EnemyBullet extends Bullet {

    public EnemyBullet(AbstractFactory F, int damage, int x, int y, int dx, int dy) {
        super(damage);
        this.setMovementComponent(x,y,dx,dy);
    }

    public abstract void visualise();
}
