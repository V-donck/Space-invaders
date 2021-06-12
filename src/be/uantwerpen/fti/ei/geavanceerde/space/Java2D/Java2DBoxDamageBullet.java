package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

/**
 * bonusbox : bullets can have more damage,
 * this is the java2D implementation of {@link BoxDamageBullet}
 */
public class Java2DBoxDamageBullet extends BoxDamageBullet {
    private Java2DFactory F;

    /**
     * creates Java2DBoxDamageBullet
     * @param F {@link Java2DFactory}: for {@link #visualise()}
     * @param xCoord super: for creating{@link BoxDamageBullet}
     */
    public Java2DBoxDamageBullet(Java2DFactory F,int xCoord) {
        super(xCoord);
        this.F = F;
    }

    /**
     * visualises Java2DBoxDamageBullet
     */
    public void visualise(){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getBoxDamageBulletIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorX()), (int) (this.getMovementComponent().getyCoord() * F.getFactorY()), null);

    }
}
