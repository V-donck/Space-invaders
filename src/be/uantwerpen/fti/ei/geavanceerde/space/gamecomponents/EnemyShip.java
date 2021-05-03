package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class EnemyShip extends Ship{

    public EnemyShip() {
        super(50);
        this.setMovementComponent(0,0,0,0);
    }

    public EnemyShip(int HP, int x, int y, int dx, int dy) {
        super(HP);
        this.setMovementComponent(x,y,dx,dy);
    }



    public abstract void visualise();

}
