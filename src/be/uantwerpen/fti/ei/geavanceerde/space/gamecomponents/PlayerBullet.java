package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class PlayerBullet extends PlayerEntity{
    private int damage;
    private AbstractFactory F;

    public PlayerBullet(AbstractFactory F, int damage, int x, int y, int dx, int dy) {
        this.damage = damage;
        this.setMovementComponent(x,y,dx,dy);
        System.out.println(x + " " + y + " pb");
        this.F = F;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public abstract void visualise();


}
