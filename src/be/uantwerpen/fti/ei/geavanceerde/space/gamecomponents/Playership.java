package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Playership extends PlayerEntity {
    private int HP;
    private AbstractFactory F;



    public Playership(AbstractFactory F) {
        this.HP = 100;
        this.F = F;
        this.setMovementComponent(F.getGameWidth()/2,F.getGameHeight()-300,0,0);/// hier nog 300 aanpassen
        this.setWidth(F.getPlayershipWidth());
        this.setHeight(F.getPlayershipHeigth());
    }

    public abstract void visualise();


}
