package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class PlayerShip extends Ship {


    /**
     * @param PlayershipHeight for setting y-coordinate in {@link #setMovementComponent(int, int, int, int)}
     * @param PlayershipWidth for setting Width
     * @param GameWidth for setting x-coordinate in {@link #setMovementComponent(int, int, int, int)}
     * @param GameHeight for setting Height and y-coordinate in {@link #setMovementComponent(int, int, int, int)}
     */
    public PlayerShip(int PlayershipHeight, int PlayershipWidth, int GameWidth, int GameHeight) {
        super(100);
        this.setMovementComponent(GameWidth/2,(int) (GameHeight-PlayershipHeight*1.4),0,0);
        this.setWidth(PlayershipWidth);
        this.setHeight(PlayershipHeight);
    }

    /**
     * abstract: visualise Playership
     */
    public abstract void visualise();



}
