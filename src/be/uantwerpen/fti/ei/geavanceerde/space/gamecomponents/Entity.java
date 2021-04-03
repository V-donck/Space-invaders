package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Entity {
    /*
    private int xCoord;
    private int yCoord;
    private int length;
    private int width;
    */
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

    /*
    public int getxCoord() {
        return xCoord;
    }

    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    public int getyCoord() {
        return yCoord;
    }

    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

     */
}
