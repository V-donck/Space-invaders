package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class EnemyBullet extends EnemyEntity{
    private int dammage;
    private AbstractFactory F;

    public EnemyBullet(AbstractFactory F, int dammage, int x, int y, int dx, int dy) {
        this.dammage = dammage;
        this.setMovementComponent(x,y,dx,dy);
        System.out.println(x + " " + y + " eb");
        this.F = F;
    }

    public int getDammage() {
        return dammage;
    }

    public void setDammage(int dammage) {
        this.dammage = dammage;
    }

    public abstract void visualise();
}
