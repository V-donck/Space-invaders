package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Friendly extends  BonusEntity{
    AbstractFactory F;
    public Friendly(AbstractFactory F) {
        this.F = F;
        this.setMovementComponent((int) (Math.random()*F.getGameWidth())-F.getEnemyshipWidth(),0,0,10);
    }

    public abstract void visualise();

}
