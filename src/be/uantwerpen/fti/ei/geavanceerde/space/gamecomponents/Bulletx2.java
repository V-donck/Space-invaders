package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Bulletx2 extends BonusEntity{
    public Bulletx2(AbstractFactory F) {
        this.setMovementComponent((int) (Math.random()*F.getGameWidth()),0,0,10);
    }
    abstract public void visualise();
}
