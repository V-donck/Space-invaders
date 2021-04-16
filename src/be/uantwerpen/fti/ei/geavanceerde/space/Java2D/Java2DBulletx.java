package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

public class Java2DBulletx extends Bulletx{
    Java2DFactory F;
    public Java2DBulletx(Java2DFactory F, int number) {
        super(F,number);
        this.F = F;
    }

    public void visualise(){
        Graphics2D g2d = F.getG2d();
        if(this.getNumber()==2) {
            g2d.drawImage(F.getBulletx2Im(), (int) (this.getMovementComponent().getxCoord() * F.getFactorx()), (int) (this.getMovementComponent().getyCoord() * F.getFactory()), null);
        }
        if(this.getNumber()==3) {
            g2d.drawImage(F.getBulletx3Im(), (int) (this.getMovementComponent().getxCoord() * F.getFactorx()), (int) (this.getMovementComponent().getyCoord() * F.getFactory()), null);
        }
    }
}
