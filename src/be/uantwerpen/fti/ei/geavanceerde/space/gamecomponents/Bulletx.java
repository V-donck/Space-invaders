package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Bulletx extends BonusEntity{
    int number;
    public Bulletx(int number,int xcoord) {
        this.setMovementComponent(xcoord,0,0,10);
        this.number = number;
    }
    abstract public void visualise();

    public int getNumber() {
        return number;
    }
}
