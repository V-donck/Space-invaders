package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

/**
 * Ship that player controls
 */
abstract public class PlayerShip extends Ship {

    /**
     * creates PlayerShip
     * @param playerShipHeight for setting y-coordinate in {@link #setMovementComponent(int, int, int, int)}
     * @param gameWidth for setting x-coordinate in {@link #setMovementComponent(int, int, int, int)}
     * @param gameHeight for setting Height and y-coordinate in {@link #setMovementComponent(int, int, int, int)}
     */
    public PlayerShip(int playerShipHeight, int gameWidth, int gameHeight) {
        super(100);
        this.setMovementComponent(gameWidth/2,(int) (gameHeight-playerShipHeight*1.4),0,0);
    }

    /**
     * visualise Playership
     */
    public abstract void visualise();
}
