package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

/**
 * bonusbox, 10 hp extra<br>
 *     Java2D implementation of BoxHp
 */
public class Java2DBoxHp extends BoxHp{
    private Java2DFactory F;

    /**
     * creates Java2DBoxHp
     * @param F {@link Java2DFactory}: for {@link #visualise()}
     * @param xCoord super: for creating{@link BoxDamageBullet}
     */
    public Java2DBoxHp(Java2DFactory F,int xCoord, int hp) {
        super(xCoord,hp);
        this.F = F;
    }

    /**
     * visualises Java2DBoxHp
     */
    public void visualise(){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getBoxHpIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorX()), (int) (this.getMovementComponent().getyCoord() * F.getFactorY()), null);
    }
}
