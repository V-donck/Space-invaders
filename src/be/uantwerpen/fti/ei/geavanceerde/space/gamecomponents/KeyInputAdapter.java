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
        }
    }

    public LinkedList<Input.Inputs> getKeyInputs(){
        return keyInputs;
    }

    public KeyInputAdapter() {
        this.keyInputs = new LinkedList<Input.Inputs>();
    }
}
