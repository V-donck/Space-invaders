package be.uantwerpen.fti.ei.geavanceerde.space;

import be.uantwerpen.fti.ei.geavanceerde.space.Java2D.*;
import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

public class Main {

    public static void main(String[] args) {
        AbstractFactory F = new Java2DFactory();
        Game g = new Game(F);
        g.start();
    }
}
