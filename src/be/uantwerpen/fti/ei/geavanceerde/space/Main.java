package be.uantwerpen.fti.ei.geavanceerde.space;

import be.uantwerpen.fti.ei.geavanceerde.space.Java2D.*;
import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

/**
 * main class, starts game
 */
public class Main {

    /**
     * creates (Java2D)factory, and game,
     * starts game
     * @param args /
     */
    public static void main(String[] args) {
        AbstractFactory F = new Java2DFactory();
        Game g = new Game(F);
        g.start();
    }
}
