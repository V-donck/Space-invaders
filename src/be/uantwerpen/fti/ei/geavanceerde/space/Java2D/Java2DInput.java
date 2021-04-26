package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.*;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Java2DInput extends Input {
    private LinkedList<Inputs> keyInputs;
    private KeyInputAdapter keyadapter;


    public Java2DInput(Java2DFactory F) {
        super();
        keyadapter =new KeyInputAdapter();
        F.getFrame().addKeyListener(keyadapter);
    }
    public boolean inputAvailable() {
        keyInputs = keyadapter.getKeyInputs();
        return keyInputs.size() > 0;
    }
    public Inputs getInput() {
        keyInputs = keyadapter.getKeyInputs();
        return keyInputs.poll();
    }


}
