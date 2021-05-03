package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class BoxDamageBullet extends BonusEntity{
    public BoxDamageBullet(int xcoord) {
        this.setMovementComponent(xcoord,0,0,10);
    }
    abstract public void visualise();

}
