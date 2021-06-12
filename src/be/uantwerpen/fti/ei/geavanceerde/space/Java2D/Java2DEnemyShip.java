package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

/**
 * the PlayerShip must shoot these EnemyShips,
 * Java2D implementation of {@link EnemyShip}
 */
public class Java2DEnemyShip extends EnemyShip{
    private Java2DFactory F;

    /**
     * creates Java2DEnemyShip
     * @param F {@link Java2DFactory} for {@link #visualise()}
     * @param hp set HP of {@link Ship}
     * @param x x-coordinate for setting {@link MovementComponent}
     * @param y y-coordinate for setting {@link MovementComponent}
     * @param dx dx for setting {@link MovementComponent}
     * @param dy dy for setting {@link MovementComponent}
     * @param level level of {@link EnemyShip}
     */
    public Java2DEnemyShip(Java2DFactory F, int hp, int x,int y, int dx, int dy,int level) {
        super(hp,x,y,dx,dy,level);
        this.F = F;
    }

    /**
     * visualises right EnemyShip (purple if level is more than 3)
     */
    public void visualise(){
        Graphics2D g2d = F.getG2d();
        if(level<3){
            g2d.drawImage(F.getEnemyShipIm(),(int) (this.getMovementComponent().getxCoord()*F.getFactorX()),(int) (this.getMovementComponent().getyCoord()*F.getFactorY()),null);
        }
        else{
            g2d.drawImage(F.getEnemyShip2Im(),(int) (this.getMovementComponent().getxCoord()*F.getFactorX()),(int) (this.getMovementComponent().getyCoord()*F.getFactorY()),null);
        }
    }
}
