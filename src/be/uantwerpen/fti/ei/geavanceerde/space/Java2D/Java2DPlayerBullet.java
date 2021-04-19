package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

public class Java2DPlayerBullet extends PlayerBullet{
    private Java2DFactory F;

    public Java2DPlayerBullet(Java2DFactory F,int damage, int x, int y, int dx, int dy) {
        super(F, damage,x,y,dx,dy);
        this.F = F;

    }
    public void visualise (){
        Graphics2D g2d = F.getG2d();
        if(this.getDamage()>=50){
            g2d.drawImage(F.getDamageBulletIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorx()), (int) (this.getMovementComponent().getyCoord() * F.getFactory()), null);
        }
        else {
            g2d.drawImage(F.getPlayerBulletIm(), (int) (this.getMovementComponent().getxCoord() * F.getFactorx()), (int) (this.getMovementComponent().getyCoord() * F.getFactory()), null);

        }
    }
}
