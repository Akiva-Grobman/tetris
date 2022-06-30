package com.akiva.tetrominos;

import com.akiva.graphics.Colors;

import java.awt.*;

public enum Tetromino {
    I(new boolean[][]{
            {false, false, true, false},
            {false, false, true, false},
            {false, false, true, false},
            {false, false, true, false}
    }, 0),
    O(new boolean[][]{
            {false, false, false, false},
            {false, true, true, false},
            {false, true, true, false},
            {false, false, false, false}
    }, 1),
    T(new boolean[][]{
            {false, false, true, false},
            {false, true, true, false},
            {false, false, true, false},
            {false, false, false, false}
    }, 2),
    S(new boolean[][]{
            {false, true, false, false},
            {false, true, false, false},
            {false, false, true, false},
            {false, false, true, false}
    }, 3),
    Z(new boolean[][]{
            {false, false, true, false},
            {false, false, true, false},
            {false, true, false, false},
            {false, true, false, false}
    }, 4),
    J(new boolean[][]{
            {false, true, true, false},
            {false, true, false, false},
            {false, true, false, false},
            {false, false, false, false}
    }, 5),
    L(new boolean[][]{
            {false, true, true, false},
            {false, false, true, false},
            {false, false, true, false},
            {false, false, false, false}
    }, 6);

    private final int colorId;
    private final Point position;
    private boolean[][] matrix;

    Tetromino(boolean[][] matrix, int colorId) {
        this.colorId = colorId;
        this.position = new Point();
        int matrixSize = 4;
        assert matrix.length == matrixSize;
        for (boolean[] line: matrix) {
            assert line.length == matrixSize;
        }
        this.matrix = matrix;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    public Point getPosition() {
        return position;
    }

    public int getColorId() {
        return colorId;
    }
}
