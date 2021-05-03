package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Friendly extends  BonusEntity{

    public Friendly(int xcoord) {
        this.setMovementComponent(xcoord,0,0,10);
    }

    public abstract void visualise();

}
