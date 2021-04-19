package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

public class Java2DBoxDamageBullet extends BoxDamageBullet {
    Java2DFactory F;
    public Java2DBoxDamageBullet(Java2DFactory F) {
        super(F);
        this.F = F;
    }
    public void visualise(){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getBoxDamageBulletIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorx()), (int) (this.getMovementComponent().getyCoord() * F.getFactory()), null);

    }
}
