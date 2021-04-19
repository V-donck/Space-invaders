package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class BoxDamageBullet extends BonusEntity{
    public BoxDamageBullet(AbstractFactory F) {
        this.setMovementComponent((int) (Math.random()*F.getGameWidth()),0,0,10);
    }
    abstract public void visualise();

}
