package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * bonus: Friendly Enemy ship : don't shoot this object
 */
abstract public class Friendly extends  BonusEntity{

    /**
     * creates Friendly
     * @param xcoord x-coordinate for setting {@link MovementComponent}
     */
    public Friendly(int xcoord) {
        this.setMovementComponent(xcoord,0,0,10);
    }

    /**
     * visualises Friendly
     */
    public abstract void visualise();

}
