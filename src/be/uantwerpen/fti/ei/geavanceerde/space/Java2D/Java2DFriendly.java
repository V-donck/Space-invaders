package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

/**
 * bonus: Friendly Enemy ship,<br>
 * Java2D implementation of {@link Friendly}
 */
public class Java2DFriendly extends Friendly{
    private Java2DFactory F;

    /**
     * creates Java2DFriendly
     * @param F {@link Java2DFactory}: for {@link #visualise()}
     * @param xcoord x-coordinate for setting {@link MovementComponent} of {@link Bulletx}
     */
    public Java2DFriendly(Java2DFactory F,int xcoord) {
        super(xcoord);
        this.F = F;
    }

    /**
     * visualises Java2DFriendly
     */
    public void visualise(){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getFriendlyIm(),(int)(this.getMovementComponent().getxCoord()*F.getFactorX()),(int)(this.getMovementComponent().getyCoord()*F.getFactorY()),null);
    }
}

