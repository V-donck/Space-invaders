package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.Bulletx;

import java.awt.*;

/**
 * Bonusbox: Playership can shoots more bullets : (number) of bullets,
 * this is the java2D implementation of {@link Bulletx}
 */
public class Java2DBulletx extends Bulletx{
    private Java2DFactory F;

    /**
     * creates Java2DBulletx
     * @param F {@link Java2DFactory}: for {@link #visualise()}
     * @param number numer of bullets that shoot at the same time: 2 or 3
     * @param xCoord x-coordinate for setting MovementComponent of {@link Bulletx}
     */
    public Java2DBulletx(Java2DFactory F, int number,int xCoord) {
        super(number,xCoord);
        this.F = F;
    }

    /**
     * visualises Java2DBulletx with right number in the box
     */
    public void visualise(){
        Graphics2D g2d = F.getG2d();
        if(this.getNumber()==2) {
            g2d.drawImage(F.getBulletx2Im(), (int) (this.getMovementComponent().getxCoord() * F.getFactorX()), (int) (this.getMovementComponent().getyCoord() * F.getFactorY()), null);
        }
        if(this.getNumber()==3) {
            g2d.drawImage(F.getBulletx3Im(), (int) (this.getMovementComponent().getxCoord() * F.getFactorX()), (int) (this.getMovementComponent().getyCoord() * F.getFactorY()), null);
        }
    }
}