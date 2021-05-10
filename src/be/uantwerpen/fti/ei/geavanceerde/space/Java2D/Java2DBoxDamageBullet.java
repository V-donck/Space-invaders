package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

public class Java2DBoxDamageBullet extends BoxDamageBullet {
    Java2DFactory F;

    /**
     * creates Java2DBoxDamageBullet
     * @param F {@link Java2DFactory}: for {@link #visualise()}
     * @param xcoord super: for creating{@link BoxDamageBullet}
     */
    public Java2DBoxDamageBullet(Java2DFactory F,int xcoord) {
        super(xcoord);
        this.F = F;
    }

    /**
     * visualises Java2DBoxDamageBullet
     */
    public void visualise(){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getBoxDamageBulletIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorx()), (int) (this.getMovementComponent().getyCoord() * F.getFactory()), null);

    }
}
