package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.EnemyBullet;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

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
     * visualise EnemyBullet
     * if damage of bullet less than 50 :red bullet
     * else: purple bullet
     */
    public void visualise () {
        Graphics2D g2d = F.getG2d();
        if (getDamage() < 50) {
            g2d.drawImage(F.getEnemyBulletIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorx()), (int) (this.getMovementComponent().getyCoord() * F.getFactory()), null);
        }
        else{
            g2d.drawImage(F.getEnemyBullet2Im(), (int) (this.getMovementComponent().getxCoord() * F.getFactorx()), (int) (this.getMovementComponent().getyCoord() * F.getFactory()), null);
        }
    }
}
