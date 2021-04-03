package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Playership extends PlayerEntity {
    private int HP;
    private AbstractFactory F;



    public Playership(AbstractFactory F) {
        this.HP = 100;
        this.F = F;
        this.setMovementComponent(500/2,1000/2-150,0,0);
        this.setWidth(F.getPlayershipWidth());
        this.setHeight(F.getPlayershipHeigth());
    }

    public abstract void visualise();


}
