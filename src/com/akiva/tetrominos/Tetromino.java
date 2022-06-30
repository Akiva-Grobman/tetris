package com.akiva.tetrominos;

import java.awt.*;

public class Tetromino {

    private final Point position;
    private final int colorId;
    private boolean[][] positionMatrix;

    public Tetromino(boolean[][] positionMatrix, int colorId, Point position) {
        this.positionMatrix = positionMatrix;
        this.colorId = colorId;
        this.position = position;
    }

    public boolean[][] getPositionMatrix() {
        return positionMatrix;
    }

    public void setPositionMatrix(boolean[][] positionMatrix) {
        this.positionMatrix = positionMatrix;
    }

    public Point getPosition() {
        return position;
    }

    public int getColorId() {
        return colorId;
    }

}
