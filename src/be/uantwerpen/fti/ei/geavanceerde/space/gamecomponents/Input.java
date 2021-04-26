package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

abstract public class Input {
    public enum Inputs {LEFT, RIGHT, UP, DOWN, SPACE, ENTER,A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z,BACKSPACE}
    private LinkedList<Inputs> keyInputs;



    public Input() {}
    abstract public boolean inputAvailable();
    abstract public Inputs getInput();


}
