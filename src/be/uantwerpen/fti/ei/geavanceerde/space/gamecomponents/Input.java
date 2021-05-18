package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * abstract class input
 */
abstract public class Input {
    public enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE, ENTER,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,BACKSPACE}
    private LinkedList<Inputs> keyInputs;


    /**
     * creates Input
     */
    public Input() {}

    /**
     * check if input is available in linkedlist
     * @return boolean
     */
    abstract public boolean inputAvailable();

    /**
     * gets Inputs
     * @return Inputs
     */
    abstract public Inputs getInput();


}
