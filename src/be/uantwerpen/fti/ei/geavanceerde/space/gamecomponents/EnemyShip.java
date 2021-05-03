package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class EnemyShip extends Ship{
    protected int level;

    public EnemyShip() {
        super(50);
        this.setMovementComponent(0,0,0,0);
        this.level = 1;
    }

    public EnemyShip(int HP, int x, int y, int dx, int dy, int level) {
        super(HP);
        this.setMovementComponent(x,y,dx,dy);
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public abstract void visualise();

}
