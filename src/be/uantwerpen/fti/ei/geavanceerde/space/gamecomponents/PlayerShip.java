package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * Ship that player controls
 */
abstract public class PlayerShip extends Ship {

    /**
     * @param PlayershipHeight for setting y-coordinate in {@link #setMovementComponent(int, int, int, int)}
     * @param GameWidth for setting x-coordinate in {@link #setMovementComponent(int, int, int, int)}
     * @param GameHeight for setting Height and y-coordinate in {@link #setMovementComponent(int, int, int, int)}
     */
    public PlayerShip(int PlayershipHeight, int GameWidth, int GameHeight) {
        super(100);
        this.setMovementComponent(GameWidth/2,(int) (GameHeight-PlayershipHeight*1.4),0,0);
    }

    /**
     * abstract: visualise Playership
     */
    public abstract void visualise();



}
