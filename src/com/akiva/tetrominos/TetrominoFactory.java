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

    public static Tetromino getTetromino(TetrominoType tetrominoType, int centerX) {
        return new Tetromino(tetrominoShapeMapping.get(tetrominoType), tetrominoType.getColorId(), new Point(centerX - 2, 0));
    }

}
