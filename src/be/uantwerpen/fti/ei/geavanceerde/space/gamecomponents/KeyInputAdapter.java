package be.uantwerpen.fti.ei.geavanceerde.space.gamecomponents;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyInputAdapter extends KeyAdapter {
    private LinkedList<Input.Inputs> keyInputs;
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                keyInputs.add(Input.Inputs.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                keyInputs.add(Input.Inputs.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                keyInputs.add(Input.Inputs.DOWN);
                break;
            case KeyEvent.VK_UP:
                keyInputs.add(Input.Inputs.UP);
                break;
            case KeyEvent.VK_SPACE:
                keyInputs.add(Input.Inputs.SPACE);
                break;
            case KeyEvent.VK_ENTER:
                keyInputs.add(Input.Inputs.ENTER);
                break;
            case KeyEvent.VK_A:
                keyInputs.add(Input.Inputs.A);
                break;
            case KeyEvent.VK_B:
                keyInputs.add(Input.Inputs.B);
                break;
            case KeyEvent.VK_C:
                keyInputs.add(Input.Inputs.C);
                break;
            case KeyEvent.VK_D:
                keyInputs.add(Input.Inputs.D);
                break;
            case KeyEvent.VK_E:
                keyInputs.add(Input.Inputs.E);
                break;
            case KeyEvent.VK_F:
                keyInputs.add(Input.Inputs.F);
                break;
            case KeyEvent.VK_G:
                keyInputs.add(Input.Inputs.G);
                break;
            case KeyEvent.VK_H:
                keyInputs.add(Input.Inputs.H);
                break;
            case KeyEvent.VK_I:
                keyInputs.add(Input.Inputs.I);
                break;
            case KeyEvent.VK_J:
                keyInputs.add(Input.Inputs.J);
                break;
            case KeyEvent.VK_K:
                keyInputs.add(Input.Inputs.K);
                break;
            case KeyEvent.VK_L:
                keyInputs.add(Input.Inputs.L);
                break;
            case KeyEvent.VK_M:
                keyInputs.add(Input.Inputs.M);
                break;
            case KeyEvent.VK_N:
                keyInputs.add(Input.Inputs.N);
                break;
            case KeyEvent.VK_O:
                keyInputs.add(Input.Inputs.O);
                break;
            case KeyEvent.VK_P:
                keyInputs.add(Input.Inputs.P);
                break;
            case KeyEvent.VK_Q:
                keyInputs.add(Input.Inputs.Q);
                break;
            case KeyEvent.VK_R:
                keyInputs.add(Input.Inputs.R);
                break;
            case KeyEvent.VK_S:
                keyInputs.add(Input.Inputs.S);
                break;
            case KeyEvent.VK_T:
                keyInputs.add(Input.Inputs.T);
                break;
            case KeyEvent.VK_U:
                keyInputs.add(Input.Inputs.U);
                break;
            case KeyEvent.VK_V:
                keyInputs.add(Input.Inputs.V);
                break;
            case KeyEvent.VK_W:
                keyInputs.add(Input.Inputs.W);
                break;
            case KeyEvent.VK_X:
                keyInputs.add(Input.Inputs.X);
                break;
            case KeyEvent.VK_Y:
                keyInputs.add(Input.Inputs.Y);
                break;
            case KeyEvent.VK_Z:
                keyInputs.add(Input.Inputs.Z);
                break;
            case KeyEvent.VK_BACK_SPACE:
                keyInputs.add(Input.Inputs.BACKSPACE);
                break;


        }
    }

    public LinkedList<Input.Inputs> getKeyInputs(){
        return keyInputs;
    }

    public KeyInputAdapter() {
        this.keyInputs = new LinkedList<Input.Inputs>();
    }
}
