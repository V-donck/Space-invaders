package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class Input {
    public enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE, ENTER,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,BACKSPACE}
    private LinkedList<Inputs> keyInputs;
    private KeyInputAdapter keyadapter;


    public Input(AbstractFactory F) {
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
