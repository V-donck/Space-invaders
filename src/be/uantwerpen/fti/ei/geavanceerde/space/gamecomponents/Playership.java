package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Playership extends PlayerEntity {
    private int HP;
    private AbstractFactory F;



    public Playership(AbstractFactory F,int PlayershipHeight) {
        this.HP = 100;
        this.F = F;
        this.setMovementComponent(F.getGameWidth()/2,(int) (F.getGameHeight()-PlayershipHeight*1.4),0,0);/// hier nog 300 aanpassen
        this.setWidth(F.getPlayershipWidth());
        this.setHeight(F.getPlayershipHeight());
    }

    public abstract void visualise();

    public void setHP(int HP){
        this.HP = HP;
    }
    public int getHP(){
        return HP;
    }


}
