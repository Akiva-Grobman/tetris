package com.akiva.graphics;

import javax.swing.*;

public class Window extends JFrame {

    public Window(int width, int height) {
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
    }

}