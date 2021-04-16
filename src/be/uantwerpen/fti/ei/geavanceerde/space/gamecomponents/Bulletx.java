package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Bulletx extends BonusEntity{
    int number;
    public Bulletx(AbstractFactory F,int number) {
        this.setMovementComponent((int) (Math.random()*F.getGameWidth()),0,0,10);
        this.number = number;
    }
    abstract public void visualise();

    public int getNumber() {
        return number;
    }
}
