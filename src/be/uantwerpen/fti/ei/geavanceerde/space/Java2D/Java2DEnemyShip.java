package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

public class Java2DEnemyShip extends EnemyShip{
    private Java2DFactory F;

    public Java2DEnemyShip(Java2DFactory F) {
        this.F = F;
    }

    public Java2DEnemyShip(Java2DFactory F, int hp, int x,int y, int dx, int dy) {
        this.F = F;
        this.setHP(hp);
        this.setMovementComponent(x,y,dx,dy);
    }

    public void visualise(){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getEnemyShipIm(),(int) (this.getMovementComponent().getxCoord()*F.getFactorx()),(int) (this.getMovementComponent().getyCoord()*F.getFactory()),null);
    }
}
