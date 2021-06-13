package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.PlayerBullet;
import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.Bullet;
import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.MovementComponent;

import java.awt.*;

/**
 * Player shoots this Bullet,
 * Java2D implementation of {@link PlayerBullet}
 */
public class Java2DPlayerBullet extends PlayerBullet{
    private Java2DFactory F;

    /**
     * creates new Java2DPlayerBullet
     * @param F {@link Java2DFactory} for {@link #visualise()}
     * @param damage super: for creating {@link PlayerBullet} and {@link Bullet}
     * @param x super: for creating {@link PlayerBullet} used for {@link MovementComponent}
     * @param y super: for creating {@link PlayerBullet} used for {@link MovementComponent}
     * @param dx super: for creating {@link PlayerBullet} used for {@link MovementComponent}
     * @param dy super: for creating {@link PlayerBullet} used for {@link MovementComponent}
     */
    public Java2DPlayerBullet(Java2DFactory F,int damage, int x, int y, int dx, int dy) {
        super(damage,x,y,dx,dy);
        this.F = F;
    }

    /**
     * visualises Java2DPlayerBullet
     * if damage of bullet is bigger or equal than 50 : blue bullet
     * else green bullet
     */
    public void visualise (){
        Graphics2D g2d = F.getG2d();
        if(this.getDamage()>=50){
            g2d.drawImage(F.getDamageBulletIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorX()), (int) (this.getMovementComponent().getyCoord() * F.getFactorY()), null);
        }
        else {
            g2d.drawImage(F.getPlayerBulletIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorX()), (int) (this.getMovementComponent().getyCoord() * F.getFactorY()), null);
        }
    }
}