package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class EnemyShip extends EnemyEntity{
    private int HP;
    private AbstractFactory F;

    public EnemyShip() {
        this.HP = 50;
        this.setMovementComponent(0,200,0,0);
    }

    public EnemyShip(AbstractFactory F, int HP, int x, int y, int dx, int dy) {
        this.F = F;
        this.HP = HP;
        this.setMovementComponent(x,y,dx,dy);
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getHP(){
        return HP;
    }

    public abstract void visualise();

}
