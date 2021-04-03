package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

public class Java2DPlayerBullet extends PlayerBullet{
    private Java2DFactory F;

    public Java2DPlayerBullet(Java2DFactory F,int dammage, int x, int y, int dx, int dy) {
        super(F, dammage,x,y,dx,dy);
        this.F = F;

    }
    public void visualise (){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getPlayerBulletIm(),this.getMovementComponent().getxCoord(),this.getMovementComponent().getyCoord(),null);
    }
}
