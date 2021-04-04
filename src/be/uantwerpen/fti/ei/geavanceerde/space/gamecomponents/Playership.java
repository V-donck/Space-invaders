package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Playership extends PlayerEntity {
    private int HP;
    private AbstractFactory F;



    public Playership(AbstractFactory F,int PlayershipHeigth) {
        this.HP = 100;
        this.F = F;
        this.setMovementComponent(F.getGameWidth()/2,F.getGameHeight()-PlayershipHeigth*2,0,0);/// hier nog 300 aanpassen
        this.setWidth(F.getPlayershipWidth());
        this.setHeight(F.getPlayershipHeigth());
    }

    public abstract void visualise();

    public void setHP(int HP){
        this.HP = HP;
    }
    public int getHP(){
        return HP;
    }


}
