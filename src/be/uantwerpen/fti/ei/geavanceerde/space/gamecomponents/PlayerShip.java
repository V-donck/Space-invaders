package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class PlayerShip extends Ship {




    public PlayerShip(int PlayershipHeight, int PlayershipWidth, int GameWidth, int GameHeight) {
        super(100);
        this.setMovementComponent(GameWidth/2,(int) (GameHeight-PlayershipHeight*1.4),0,0);
        this.setWidth(PlayershipWidth);
        this.setHeight(PlayershipHeight);
    }

    public abstract void visualise();



}
