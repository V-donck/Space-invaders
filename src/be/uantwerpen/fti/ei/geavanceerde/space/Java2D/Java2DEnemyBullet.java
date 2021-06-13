package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.EnemyBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.Bullet;

import java.awt.*;

/**
 * EnemyShip shoots this bullet,
 * Java2D implementation of {@link EnemyBullet}
 */
public class Java2DEnemyBullet extends EnemyBullet {
    private Java2DFactory F;

    /**
     * creates Java2DEnemyBullet
     * @param F {@link Java2DFactory} for {@link #visualise()}
     * @param damage super: for creating {@link EnemyBullet} and {@link Bullet}
     * @param x super: for creating {@link EnemyBullet}
     * @param y super: for creating {@link EnemyBullet}
     * @param dx super: for creating {@link EnemyBullet}
     * @param dy super: for creating {@link EnemyBullet}
     */
    public Java2DEnemyBullet(Java2DFactory F,int damage, int x, int y, int dx, int dy) {
        super(damage,x,y,dx,dy);
        this.F = F;
    }

    /**
     * visualise EnemyBullet in right colour ( red if damage is less than 50 els purple)
     */
    public void visualise () {
        Graphics2D g2d = F.getG2d();
        if (getDamage() < 50) {
            g2d.drawImage(F.getEnemyBulletIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorX()), (int) (this.getMovementComponent().getyCoord() * F.getFactorY()), null);
        }
        else{
            g2d.drawImage(F.getEnemyBullet2Im(), (int) (this.getMovementComponent().getxCoord() * F.getFactorX()), (int) (this.getMovementComponent().getyCoord() * F.getFactorY()), null);
        }
    }
}