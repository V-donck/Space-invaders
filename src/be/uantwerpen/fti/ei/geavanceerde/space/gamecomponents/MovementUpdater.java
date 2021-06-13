package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.util.ArrayList;

/**
 * updates MovementComponent
 */
public class MovementUpdater {
    /**
     * creates MovementUpdater
     */
    public MovementUpdater() {
    }

    /**
     * updates ArrayList&lt;{@link MovementComponent}&gt;
     * @param list ArrayList&lt;{@link MovementComponent}&gt;
     * @return updated list ArrayList&lt;{@link MovementComponent}&gt;
     */
    public ArrayList<MovementComponent> update(ArrayList<MovementComponent> list){
        for (MovementComponent movementComponent : list) {
            movementComponent.setxCoord(movementComponent.getxCoord() + movementComponent.getDx());
            movementComponent.setyCoord(movementComponent.getyCoord() + movementComponent.getDy());
        }
        return list;
    }
}