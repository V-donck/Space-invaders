package be.uantwerpen.fti.ei.geavanceerde.space.Java2D;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * keyadapter: reads keyinput
 */
public class KeyInputAdapter extends KeyAdapter {
    private LinkedList<Java2DInput.Inputs> keyInputs;

    /**
     * add pressed key to keyInputs
     * @param e keycode of pressed key
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        switch (keycode) {
            case KeyEvent.VK_LEFT:
                keyInputs.add(Java2DInput.Inputs.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                keyInputs.add(Java2DInput.Inputs.RIGHT);
                break;
            case KeyEvent.VK_DOWN:
                keyInputs.add(Java2DInput.Inputs.DOWN);
                break;
            case KeyEvent.VK_UP:
                keyInputs.add(Java2DInput.Inputs.UP);
                break;
            case KeyEvent.VK_SPACE:
                keyInputs.add(Java2DInput.Inputs.SPACE);
                break;
            case KeyEvent.VK_ENTER:
                keyInputs.add(Java2DInput.Inputs.ENTER);
                break;
            case KeyEvent.VK_A:
                keyInputs.add(Java2DInput.Inputs.A);
                break;
            case KeyEvent.VK_B:
                keyInputs.add(Java2DInput.Inputs.B);
                break;
            case KeyEvent.VK_C:
                keyInputs.add(Java2DInput.Inputs.C);
                break;
            case KeyEvent.VK_D:
                keyInputs.add(Java2DInput.Inputs.D);
                break;
            case KeyEvent.VK_E:
                keyInputs.add(Java2DInput.Inputs.E);
                break;
            case KeyEvent.VK_F:
                keyInputs.add(Java2DInput.Inputs.F);
                break;
            case KeyEvent.VK_G:
                keyInputs.add(Java2DInput.Inputs.G);
                break;
            case KeyEvent.VK_H:
                keyInputs.add(Java2DInput.Inputs.H);
                break;
            case KeyEvent.VK_I:
                keyInputs.add(Java2DInput.Inputs.I);
                break;
            case KeyEvent.VK_J:
                keyInputs.add(Java2DInput.Inputs.J);
                break;
            case KeyEvent.VK_K:
                keyInputs.add(Java2DInput.Inputs.K);
                break;
            case KeyEvent.VK_L:
                keyInputs.add(Java2DInput.Inputs.L);
                break;
            case KeyEvent.VK_M:
                keyInputs.add(Java2DInput.Inputs.M);
                break;
            case KeyEvent.VK_N:
                keyInputs.add(Java2DInput.Inputs.N);
                break;
            case KeyEvent.VK_O:
                keyInputs.add(Java2DInput.Inputs.O);
                break;
            case KeyEvent.VK_P:
                keyInputs.add(Java2DInput.Inputs.P);
                break;
            case KeyEvent.VK_Q:
                keyInputs.add(Java2DInput.Inputs.Q);
                break;
            case KeyEvent.VK_R:
                keyInputs.add(Java2DInput.Inputs.R);
                break;
            case KeyEvent.VK_S:
                keyInputs.add(Java2DInput.Inputs.S);
                break;
            case KeyEvent.VK_T:
                keyInputs.add(Java2DInput.Inputs.T);
                break;
            case KeyEvent.VK_U:
                keyInputs.add(Java2DInput.Inputs.U);
                break;
            case KeyEvent.VK_V:
                keyInputs.add(Java2DInput.Inputs.V);
                break;
            case KeyEvent.VK_W:
                keyInputs.add(Java2DInput.Inputs.W);
                break;
            case KeyEvent.VK_X:
                keyInputs.add(Java2DInput.Inputs.X);
                break;
            case KeyEvent.VK_Y:
                keyInputs.add(Java2DInput.Inputs.Y);
                break;
            case KeyEvent.VK_Z:
                keyInputs.add(Java2DInput.Inputs.Z);
                break;
            case KeyEvent.VK_BACK_SPACE:
                keyInputs.add(Java2DInput.Inputs.BACKSPACE);
                break;
        }
    }

    /**
     * gets keyInputs
     * @return list keyInputs
     */
    public LinkedList<Java2DInput.Inputs> getKeyInputs(){
        return keyInputs;
    }

    /**
     * creates keyInputAdapter, create linkedlist keyInputs
     */
    public KeyInputAdapter() {
        this.keyInputs = new LinkedList<>();
    }
}