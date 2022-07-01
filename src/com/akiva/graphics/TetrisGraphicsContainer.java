package com.akiva.graphics;

import java.awt.*;
import java.awt.event.KeyListener;
import java.util.function.Supplier;

public class TetrisGraphicsContainer implements GameLoopDrawable {

    private final Window window;

    public TetrisGraphicsContainer(int tileColumnCount,
                                   int tileRowCount,
                                   Supplier<int[][]> getBoardColors,
                                   Supplier<int[][]> getNextTetromino,
                                   Supplier<Integer> getExplodedCount) {
        int width = Toolkit.getDefaultToolkit().getScreenSize().width / 3 * 2;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        height = height - (height / 10);
        window = new Window(width, height);
        Dimension tetrisBoardDimensions = getBoardDimensions(width, height);
        TetrisGraphicsBoard tetrisGraphicsBoard = new TetrisGraphicsBoard(
                new Dimension(tetrisBoardDimensions.width, tetrisBoardDimensions.height),
                tileColumnCount,
                tileRowCount,
                getBoardColors
        );
        window.addComponent(tetrisGraphicsBoard, BorderLayout.CENTER);
        Dimension explodingCounterDimensions = getExplodingCounterDimensions(width, height);
        window.addComponent(
                new ExplodingCounter(
                        explodingCounterDimensions.width,
                        explodingCounterDimensions.height,
                        getExplodedCount),
                BorderLayout.WEST
            );
        Dimension nextTetrominoDimensions = getNextTetrominoDisplayDimensions(width, height);
        window.addComponent(
                new NextTetrominoDisplay(
                        nextTetrominoDimensions.width,
                        nextTetrominoDimensions.height,
                        getNextTetromino),
                BorderLayout.EAST
        );
    }

    @Override
    public void render() {
        this.window.repaint();
    }

    public void addKeyboardListener(KeyListener keyListener) {
        this.window.addKeyListener(keyListener);
    }

    private Dimension getBoardDimensions(int displayWidth, int displayHeight) {
        return new Dimension(displayWidth / 3, displayHeight - (displayHeight / 10));
    }

    private Dimension getExplodingCounterDimensions(int displayWidth, int displayHeight) {
        return new Dimension(displayWidth / 3, displayHeight / 10);
    }

    private Dimension getNextTetrominoDisplayDimensions(int displayWidth, int displayHeight) {
        return new Dimension(displayWidth / 3, displayHeight / 10);
    }

}
