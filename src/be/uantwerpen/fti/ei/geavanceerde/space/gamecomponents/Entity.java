package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

abstract public class Entity {

    private MovementComponent movcomp;
    private int height;
    private int width;


    /**
     * generate Entity
     * makes new {@link MovementComponent} movecomp and set it to (1,1,0,0)
     */
    public Entity() {
        this.movcomp = new MovementComponent(1,1,0,0);
    }

    /**
     * set {@link MovementComponent} to this parameters
     * @param xCoord set x-coordinate of {@link MovementComponent}
     * @param yCoord set y-coordinate of {@link MovementComponent}
     * @param dx set dx of {@link MovementComponent}
     * @param dy set dy of {@link MovementComponent}
     */
    public void setMovementComponent(int xCoord, int yCoord, int dx, int dy){
        movcomp.setMovementComponent(xCoord, yCoord, dx, dy);
    }

    /**
     * @return {@link MovementComponent} movecomp
     */
    public MovementComponent getMovementComponent(){
        return movcomp;
    }

// dit alles weg?????
    // en dus ook height en width in entity?
    /**
     * @return height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @return width
     */
    public int getWidth() {
        return width;
    }

    /**
     * @param height set Height
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @param width set Width
     */
    public void setWidth(int width) {
        this.width = width;
    }

}
