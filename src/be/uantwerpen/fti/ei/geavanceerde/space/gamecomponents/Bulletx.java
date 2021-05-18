package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * Bonusbox: Playership can shoots more bullets : (number) of bullets
 */
abstract public class Bulletx extends BonusEntity{
    int number;

    /**
     * creates Bulletx
     * @param number numer of bullets that shoot at the same time: 2 or 3
     * @param xcoord x-coordinate for setting {@link MovementComponent}
     */
    public Bulletx(int number,int xcoord) {
        this.setMovementComponent(xcoord,0,0,10);
        this.number = number;
    }

    /**
     * visualises Bulletx
     */
    abstract public void visualise();

    /**
     * gets number
     * @return int Number
     */
    public int getNumber() {
        return number;
    }
}
