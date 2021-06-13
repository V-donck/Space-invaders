package be.uantwerpen.fti.ei.geavanceerde.space;

import be.uantwerpen.fti.ei.geavanceerde.space.Java2D.Java2DFactory;
import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.Game;
import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.AbstractFactory;

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