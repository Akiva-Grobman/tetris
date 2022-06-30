package com.akiva.logic;

import com.akiva.graphics.Colors;
import com.akiva.keyboardinput.Subscribable;
import com.akiva.tetrominos.Tetromino;
import com.akiva.tetrominos.TetrominoFactory;
import com.akiva.tetrominos.TetrominoType;
import java.util.Arrays;

public class TetrisGame implements GameLoopUpdatable, Subscribable {

    private static final int EMPTY_SQUARE_COLOR_ID = Colors.DEFAULT_BACKGROUND_ID;
    private final int[][] colorIdBoard;
    private int lineExplodedCount = 0;
    private Tetromino currentTetromino;
    private Tetromino nextTetromino;

    public TetrisGame(int columnTileCount, int rowTileCount) {
        colorIdBoard = new int[columnTileCount][];
        for (int x = 0; x < columnTileCount; x++) {
            colorIdBoard[x] = new int[rowTileCount];
            Arrays.fill(colorIdBoard[x], 7);
        }
        currentTetromino = getRandomTetromino();
        nextTetromino = getRandomTetromino();
    }

    @Override
    public void updateEachSecond() {
        onDownClick();
    }

    @Override
    public Runnable getOnExit() {
        return this::onExit;
    }

    @Override
    public Runnable getOnDownClick() {
        return this::onDownClick;
    }

    @Override
    public Runnable getOnRightClick() {
        return this::onRightClick;
    }

    @Override
    public Runnable getLeftClick() {
        return this::onLeftClick;
    }

    @Override
    public Runnable getOnUpClick() {
        return this::onUpClick;
    }

    public int[][] getColorIdBoard() {
        int[][] coloredBoard = new int[colorIdBoard.length][colorIdBoard[0].length];
        for (int i = 0; i < coloredBoard.length; i++) {
            coloredBoard[i] = Arrays.copyOf(colorIdBoard[i], colorIdBoard[i].length);
        }
        for (int x = 0; x < currentTetromino.getPositionMatrix().length; x++) {
            for (int y = 0; y < currentTetromino.getPositionMatrix()[x].length; y++) {
                if (currentTetromino.getPositionMatrix()[x][y]) {
                    coloredBoard[x + currentTetromino.getPosition().x][y + currentTetromino.getPosition().y] = currentTetromino.getColorId();
                }
            }
        }
        return coloredBoard;
    }

    public int getLineExplodedCount() {
        return lineExplodedCount;
    }

    private void onExit() {
        System.exit(0);
    }

    private synchronized void onDownClick() {
        try {
            movedTetrominoDown();
        } catch (ReachedBottomException e) {
            updateCurrentTetromino();
            removeBottomLineIfFull();
        }
    }

    private void removeBottomLineIfFull() {
        for (int[] column : colorIdBoard) {
            if (column[column.length - 1] == EMPTY_SQUARE_COLOR_ID) {
                return;
            }
        }
        lineExplodedCount++;
        for (int x = 0; x < colorIdBoard.length; x++) {
            System.arraycopy(colorIdBoard[x], 0, colorIdBoard[x], 1, colorIdBoard[x].length - 1);
            colorIdBoard[x][0] = 7;
        }
        removeBottomLineIfFull();
    }

    private void movedTetrominoDown() throws ReachedBottomException {
        for (int x = 0; x < currentTetromino.getPositionMatrix().length; x++) {
            for (int y = 0; y < currentTetromino.getPositionMatrix()[x].length; y++) {
                if (!currentTetromino.getPositionMatrix()[x][y]) {
                    continue;
                }
                int newYPosition = y + currentTetromino.getPosition().y + 1;
                if (!(newYPosition < colorIdBoard[0].length) || colorIdBoard[x + currentTetromino.getPosition().x][newYPosition] != EMPTY_SQUARE_COLOR_ID) {
                    throw new ReachedBottomException();
                }
            }
        }
        currentTetromino.getPosition().y += 1;
    }

    private void onRightClick() {
        currentTetromino.getPosition().x = getUpdatedXPosition(1);
    }

    private void onLeftClick() {
        currentTetromino.getPosition().x = getUpdatedXPosition(-1);
    }

    private void onUpClick() {
        currentTetromino.setPositionMatrix(TetrominoRotator.getRotated(currentTetromino.getPositionMatrix()));
    }

    private int getUpdatedXPosition(int direction) {
        for (int x = 0; x < currentTetromino.getPositionMatrix().length; x++) {
            for (int y = 0; y < currentTetromino.getPositionMatrix()[x].length; y++) {
                if (!currentTetromino.getPositionMatrix()[x][y]) {
                    continue;
                }
                int newXPosition = x + currentTetromino.getPosition().x + direction;
                if (!(newXPosition < colorIdBoard.length) || !(newXPosition >= 0)  || colorIdBoard[newXPosition][y + currentTetromino.getPosition().y] != EMPTY_SQUARE_COLOR_ID) {
                    return currentTetromino.getPosition().x;
                }
            }
        }
        return currentTetromino.getPosition().x + direction;
    }

    private void updateCurrentTetromino() {
        for (int x = 0; x < currentTetromino.getPositionMatrix().length; x++) {
            for (int y = 0; y < currentTetromino.getPositionMatrix()[x].length; y++) {
                if (currentTetromino.getPositionMatrix()[x][y]) {
                    colorIdBoard[x + currentTetromino.getPosition().x][y + currentTetromino.getPosition().y] = currentTetromino.getColorId();
                }
            }
        }
        currentTetromino = nextTetromino;
        nextTetromino = getRandomTetromino();
    }

    private Tetromino getRandomTetromino() {
        return TetrominoFactory.getTetromino(
                TetrominoType.values()[(int)(Math.random() * TetrominoType.values().length)],
                colorIdBoard.length / 2
            );
    }

}
