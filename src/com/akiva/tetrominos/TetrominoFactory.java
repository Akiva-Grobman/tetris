package com.akiva.tetrominos;

import java.awt.*;
import java.util.HashMap;

public class TetrominoFactory {

    private final static HashMap<TetrominoType, boolean[][]> tetrominoShapeMapping = new HashMap<>(){
        {
            put(TetrominoType.I,
                new boolean[][]{
                        {false, false, true, false},
                        {false, false, true, false},
                        {false, false, true, false},
                        {false, false, true, false}
                });
            put(TetrominoType.O,
                    new boolean[][]{
                        {false, false, false, false},
                        {false, true, true, false},
                        {false, true, true, false},
                        {false, false, false, false}
                    });
            put(TetrominoType.T,
                    new boolean[][]{
                        {false, false, true, false},
                        {false, true, true, false},
                        {false, false, true, false},
                        {false, false, false, false}
                    });
            put(TetrominoType.S,
                    new boolean[][]{
                        {false, true, false, false},
                        {false, true, false, false},
                        {false, false, true, false},
                        {false, false, true, false}
                    });
            put(TetrominoType.Z,
                    new boolean[][]{
                        {false, false, true, false},
                        {false, false, true, false},
                        {false, true, false, false},
                        {false, true, false, false}
                    });
            put(TetrominoType.J,
                    new boolean[][]{
                        {false, true, true, false},
                        {false, true, false, false},
                        {false, true, false, false},
                        {false, false, false, false}
                    });
            put(TetrominoType.L,
                    new boolean[][]{
                        {false, true, true, false},
                        {false, false, true, false},
                        {false, false, true, false},
                        {false, false, false, false}
                    });
        }
    };

    public static Tetromino getTetromino(TetrominoType tetrominoType, int boardWidth) {
        return new Tetromino(
                tetrominoShapeMapping.get(tetrominoType),
                tetrominoType.getColorId(),
                getBoardTopCenterPosition(boardWidth));
    }

    private static Point getBoardTopCenterPosition(int boardWidth) {
        int tetrominoWidth = tetrominoShapeMapping.get(TetrominoType.I).length;
        return new Point(boardWidth / 2 - tetrominoWidth / 2, 0);
    }

}
