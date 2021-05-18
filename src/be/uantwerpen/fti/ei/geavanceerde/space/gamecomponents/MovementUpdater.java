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
    ArrayList<MovementComponent> update(ArrayList<MovementComponent> list){
        for(int i = 0; i< list.size(); i++) {
            list.get(i).setxCoord(list.get(i).getxCoord() + list.get(i).getDx());
            list.get(i).setyCoord(list.get(i).getyCoord() + list.get(i).getDy());
        }
        return list;
    }

}
