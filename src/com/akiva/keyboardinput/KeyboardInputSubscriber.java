package com.akiva.keyboardinput;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

public class KeyboardInputSubscriber implements KeyListener {

    private final HashMap<Integer, Runnable> keyResponseMapping;

    public KeyboardInputSubscriber(Subscribable subscribable) {
        keyResponseMapping = new HashMap<>(){
            {
                put(KeyEvent.VK_ESCAPE, subscribable.getOnExit());
                put(KeyEvent.VK_UP, subscribable.getOnUpClick());
                put(KeyEvent.VK_DOWN, subscribable.getOnDownClick());
                put(KeyEvent.VK_RIGHT, subscribable.getOnRightClick());
                put(KeyEvent.VK_LEFT, subscribable.getLeftClick());
            }
        };
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // ignored
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyPressedCode = e.getKeyCode();
        if (this.keyResponseMapping.containsKey(keyPressedCode)) {
            this.keyResponseMapping.get(keyPressedCode).run();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // ignored
    }

}
