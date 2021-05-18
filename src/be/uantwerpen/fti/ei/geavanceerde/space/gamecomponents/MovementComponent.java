package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.awt.*;

/**
 * Movementcomponent classe: x,y,dx,dy
 */
public class MovementComponent {
    private int xCoord;
    private int yCoord;
    private int dy;
    private int dx;

    /**
     * creates empty movementcomponent
     */
    public MovementComponent() {
    }

    /**
     * creates Movementcomponent
     * @param xCoord set x-coordinate
     * @param yCoord set y-coordinate
     * @param dx set dx
     * @param dy set dy
     */
    public MovementComponent(int xCoord, int yCoord, int dx, int dy) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.dy = dy;
        this.dx = dx;
    }

    /**
     * get x-coordinate
     * @return int xCoordinate
     */
    public int getxCoord() {
        return xCoord;
    }

    /**
     * set x-coordinate
     * @param xCoord x-coordinate
     */
    public void setxCoord(int xCoord) {
        this.xCoord = xCoord;
    }

    /**
     * get y-coordinate
     * @return int y-coordinate
     */
    public int getyCoord() {
        return yCoord;
    }

    /**
     * set y-coordinate
     * @param yCoord y-coordinate
     */
    public void setyCoord(int yCoord) {
        this.yCoord = yCoord;
    }

    /**
     * get Dy
     * @return int dy
     */
    public int getDy() {
        return dy;
    }

    /**
     * set dy
     * @param dy dy
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

    /**
     * get dx
     * @return int dx
     */
    public int getDx() {
        return dx;
    }

    /**
     * set dx
     * @param dx dx
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * set MovementComponent
     * @param xCoord x-coordinate
     * @param yCoord y-coordinate
     * @param dx dx
     * @param dy dy
     */
    public void setMovementComponent(int xCoord, int yCoord, int dx, int dy){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.dy = dy;
        this.dx = dx;
    }
}

