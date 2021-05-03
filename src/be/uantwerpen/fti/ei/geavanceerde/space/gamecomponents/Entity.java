package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Entity {

    private MovementComponent movcomp;
    private int height;
    private int width;
    public MovementComponent getMovementComponent(){
        return movcomp;
    }

    public Entity() {
        this.movcomp = new MovementComponent(1,1,0,0);
    }

    public void setMovementComponent(int xCoord, int yCoord, int dx, int dy){
        movcomp.setMovementComponent(xCoord, yCoord, dx, dy);
    }

    public int getHeight() {
        return height;
    }
    public int getWidth() {
        return width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
