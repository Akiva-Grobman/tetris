package com.akiva.logic;

import com.akiva.keyboardinput.Subscribable;
import com.akiva.tetrominos.Tetromino;
import com.akiva.tetrominos.TetrominoFactory;
import com.akiva.tetrominos.TetrominoType;

import java.util.Arrays;

public class TetrisGame implements GameLoopUpdatable, Subscribable {

    private final boolean[][] tetrisBoard;
    private final int[][] colorIdBoard;
    private Tetromino currentTetromino;
    private Tetromino nextTetromino;

    public TetrisGame(int columnTileCount, int rowTileCount) {
        tetrisBoard = new boolean[columnTileCount][];
        colorIdBoard = new int[columnTileCount][];
        for (int x = 0; x < columnTileCount; x++) {
            tetrisBoard[x] = new boolean[rowTileCount];
            colorIdBoard[x] = new int[rowTileCount];
            Arrays.fill(tetrisBoard[x], false);
            Arrays.fill(colorIdBoard[x], 7);
        }
        currentTetromino = getRandomTetromino();
        nextTetromino = getRandomTetromino();
    }

    @Override
    public void updateEachSecond() {
        try {
            movedTetrominoDown();
        } catch (ReachedBottomException e) {
            updateCurrentTetromino();
        }
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

    private void onExit() {
        System.exit(0);
    }

    private void onDownClick() {
        try {
            movedTetrominoDown();
        } catch (ReachedBottomException e) {
            updateCurrentTetromino();
        }
    }

    private void movedTetrominoDown() throws ReachedBottomException {
        for (int x = 0; x < currentTetromino.getPositionMatrix().length; x++) {
            for (int y = 0; y < currentTetromino.getPositionMatrix()[x].length; y++) {
                if (!currentTetromino.getPositionMatrix()[x][y]) {
                    continue;
                }
                int newYPosition = y + currentTetromino.getPosition().y + 1;
                if (!(newYPosition < tetrisBoard[0].length) || tetrisBoard[x + currentTetromino.getPosition().x][newYPosition]) {
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
                if (!(newXPosition < tetrisBoard.length) || !(newXPosition >= 0)  || tetrisBoard[newXPosition][y + currentTetromino.getPosition().y]) {
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
                    tetrisBoard[x + currentTetromino.getPosition().x][y + currentTetromino.getPosition().y] = true;
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
                tetrisBoard.length / 2
            );
    }

}
