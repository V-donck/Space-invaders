package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.EnemyBullet;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.*;

public class Java2DEnemyBullet extends EnemyBullet {
    private Java2DFactory F;

    public Java2DEnemyBullet(Java2DFactory F,int damage, int x, int y, int dx, int dy) {
        super(F, damage,x,y,dx,dy);
        this.F = F;

    }

    public void visualise (){
        Graphics2D g2d = F.getG2d();
        g2d.drawImage(F.getEnemyBulletIm(),(int) (this.getMovementComponent().getxCoord()*F.getFactorx()),(int)(this.getMovementComponent().getyCoord()*F.getFactory()),null);
    }
}
