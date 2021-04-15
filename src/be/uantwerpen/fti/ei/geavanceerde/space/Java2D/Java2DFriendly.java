package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

public class Java2DFriendly extends Friendly{
    Java2DFactory F;

    public Java2DFriendly(Java2DFactory F) {
        super(F);
        this.F = F;

    }

    public void visualise(){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getFriendlyIm(),(int)(this.getMovementComponent().getxCoord()*F.getFactorx()),(int)(this.getMovementComponent().getyCoord()*F.getFactory()),null);
    }
}
