package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.awt.*;

public class MovementComponent {
    private int xCoord;
    private int yCoord;
    private int dy;
    private int dx;

    public MovementComponent() {
    }

    public MovementComponent(int xCoord, int yCoord, int dx, int dy) {
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.dy = dy;
        this.dx = dx;
    }

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

    public int getDy() {
        return dy;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public Point getCoord(){
        Point p = new Point(xCoord,yCoord);
        return p;
    }

    public void setMovementComponent(int xCoord, int yCoord, int dx, int dy){
        this.xCoord = xCoord;
        this.yCoord = yCoord;
        this.dy = dy;
        this.dx = dx;
    }

}

