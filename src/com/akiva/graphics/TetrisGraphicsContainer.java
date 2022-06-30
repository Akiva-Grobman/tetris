package com.akiva.graphics;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.function.Supplier;

public class TetrisGraphicsContainer implements GameLoopDrawable {

    private final Window window;

    public TetrisGraphicsContainer(int tileColumnCount, int tileRowCount, Supplier<int[][]> getBoardColors) {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width / 3 * 2;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        height = height - (height / 10);
        window = new Window(width, height);
        TetrisGraphicsBoard tetrisGraphicsBoard = new TetrisGraphicsBoard(
                new Dimension(width / 3, height - (height / 10)),
                tileColumnCount,
                tileRowCount,
                getBoardColors
        );
        window.addComponent(tetrisGraphicsBoard);
    }

    @Override
    public void render() {
        this.window.repaint();
    }

    public void addKeyboardListener(KeyListener keyListener) {
        this.window.addKeyListener(keyListener);
    }

}
