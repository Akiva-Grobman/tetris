package com.akiva.logic;

public class TetrominoRotator {

    public static boolean[][] getRotated(boolean[][] originalTetromino) {
        int tetrominoSize = originalTetromino.length;
        assert tetrominoSize == originalTetromino[0].length;
        boolean[][] rotated = new boolean[tetrominoSize][tetrominoSize];
        for (int x = 0; x < tetrominoSize / 2; x++) {
            for (int y = x; y < tetrominoSize - x - 1; y++) {
                boolean temp = originalTetromino[x][y];
                rotated[x][y] = originalTetromino[y][tetrominoSize - 1 - x];
                rotated[y][tetrominoSize - 1 - x]
                        = originalTetromino[tetrominoSize - 1 - x][tetrominoSize - 1 - y];
                rotated[tetrominoSize - 1 - x][tetrominoSize - 1 - y] = originalTetromino[tetrominoSize - 1 - y][x];
                rotated[tetrominoSize - 1 - y][x] = temp;
            }
        }
        return rotated;
    }

}
