package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents.Input;

import java.util.LinkedList;

/**
 * Input for Java2d, keylistener added to frame,
 * Java2D implementation of {@link Input}
 */
public class Java2DInput extends Input {
    private LinkedList<Inputs> keyInputs;
    private KeyInputAdapter keyadapter;

    /**
     * creates Java2DInput
     * @param F {@link Java2DFactory} to add the keylistener to
     */
    public Java2DInput(Java2DFactory F) {
        super();
        keyadapter =new KeyInputAdapter();
        F.getFrame().addKeyListener(keyadapter);
    }

    /**
     * checks if an input is available
     * @return boolean: true or false
     */
    public boolean inputAvailable() {
        keyInputs = keyadapter.getKeyInputs();
        return keyInputs.size() > 0;
    }

    /**
     * returns keyinputs
     * @return keyinputs
     */
    public Inputs getInput() {
        keyInputs = keyadapter.getKeyInputs();
        return keyInputs.poll();
    }
}