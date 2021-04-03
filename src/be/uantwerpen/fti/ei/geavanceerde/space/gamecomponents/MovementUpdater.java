package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.util.ArrayList;

public class MovementUpdater {
    public MovementUpdater() {
    }
/*
    MovementComponent[] update(MovementComponent[] list){
        for (int i = 0; i< list.length; i++) {
            System.out.println("in movementcomponent aan het updaten");
            list[i].setxCoord(list[i].getxCoord() + list[i].getDx());
            list[i].setyCoord(list[i].getyCoord() + list[i].getDy());
            System.out.println("in movementcomponent geupdate");
        }
        return list;
    };*/

    ArrayList<MovementComponent> update(ArrayList<MovementComponent> list){
        for(int i = 0; i< list.size(); i++) {
          // System.out.println("in movementcomponent aan het updaten");
            list.get(i).setxCoord(list.get(i).getxCoord() + list.get(i).getDx());
            list.get(i).setyCoord(list.get(i).getyCoord() + list.get(i).getDy());
           // System.out.println("in movementcomponent geupdate");
        }
        return list;
    }


}
