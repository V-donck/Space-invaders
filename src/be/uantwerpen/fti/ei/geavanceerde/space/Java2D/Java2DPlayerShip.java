package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

public class Java2DPlayerShip extends PlayerShip {
    private Java2DFactory F;

    /**
     * creates Java2DPlayerShip
     * @param F {@link Java2DFactory}: for {@link #visualise()} this {@link Java2DPlayerShip}
     * @param PlayershipHeight super: for creating {@link PlayerShip}
     * @param GameWidth super: for creating {@link PlayerShip}
     * @param GameHeight super: for creating {@link PlayerShip}
     */
    public Java2DPlayerShip(Java2DFactory F, int PlayershipHeight, int GameWidth, int GameHeight) {
        super(PlayershipHeight, GameWidth, GameHeight);
        this.F = F;

    }

    /**
     * visualises Java2DPlayership
     */
    public void visualise (){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getPlayerShipIm(),(int)(this.getMovementComponent().getxCoord()*F.getFactorx()),(int)(this.getMovementComponent().getyCoord()*F.getFactory()),null);

    }
}
