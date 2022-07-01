package com.akiva.logic;

import com.akiva.graphics.Colors;
import com.akiva.keyboardinput.Subscribable;
import com.akiva.tetrominos.Tetromino;
import com.akiva.tetrominos.TetrominoFactory;
import com.akiva.tetrominos.TetrominoType;
import java.util.Arrays;

public class TetrisGameLogic implements GameLoopUpdatable, Subscribable {

    private static final int EMPTY_SQUARE_COLOR_ID = Colors.DEFAULT_BACKGROUND_ID;
    private final int[][] tetrisBoard;
    private int lineExplodedCount = 0;
    private Tetromino currentTetromino;
    private Tetromino nextTetromino;

    public TetrisGameLogic(int columnTileCount, int rowTileCount) {
        tetrisBoard = new int[columnTileCount][];
        for (int x = 0; x < columnTileCount; x++) {
            tetrisBoard[x] = new int[rowTileCount];
            Arrays.fill(tetrisBoard[x], EMPTY_SQUARE_COLOR_ID);
        }
        currentTetromino = getRandomTetromino();
        nextTetromino = getRandomTetromino();
    }

    @Override
    public void update() {
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

    public int[][] getTetrisBoard() {
        return getTetrisBoardWithCurrentTetromino();
    }

    private int[][] getTetrisBoardWithCurrentTetromino() {
        int[][] currentGameState = new int[tetrisBoard.length][];
        for (int i = 0; i < currentGameState.length; i++) {
            currentGameState[i] = Arrays.copyOf(tetrisBoard[i], tetrisBoard[i].length);
        }
        for (int x = 0; x < currentTetromino.getPositionMatrix().length; x++) {
            for (int y = 0; y < currentTetromino.getPositionMatrix()[x].length; y++) {
                if (currentTetromino.getPositionMatrix()[x][y]) {
                    currentGameState[x + currentTetromino.getPosition().x][y + currentTetromino.getPosition().y] = currentTetromino.getColorId();
                }
            }
        }
        return currentGameState;
    }

    public int[][] getNextTetromino() {
        int[][] tetrominoBoard = new int[nextTetromino.getPositionMatrix().length][nextTetromino.getPositionMatrix()[0].length];
        for (int x = 0; x < nextTetromino.getPositionMatrix().length; x++) {
            for (int y = 0; y < nextTetromino.getPositionMatrix()[x].length; y++) {
                if (nextTetromino.getPositionMatrix()[x][y]) {
                    tetrominoBoard[x][y] = nextTetromino.getColorId();
                } else {
                    tetrominoBoard[x][y] = EMPTY_SQUARE_COLOR_ID;
                }
            }
        }
        return tetrominoBoard;
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
        for (int[] column : tetrisBoard) {
            if (column[column.length - 1] == EMPTY_SQUARE_COLOR_ID) {
                return;
            }
        }
        for (int x = 0; x < tetrisBoard.length; x++) {
            System.arraycopy(tetrisBoard[x], 0, tetrisBoard[x], 1, tetrisBoard[x].length - 1);
            tetrisBoard[x][0] = EMPTY_SQUARE_COLOR_ID;
        }
        lineExplodedCount++;
        removeBottomLineIfFull();
    }

    private void movedTetrominoDown() throws ReachedBottomException {
        for (int x = 0; x < currentTetromino.getPositionMatrix().length; x++) {
            for (int y = 0; y < currentTetromino.getPositionMatrix()[x].length; y++) {
                if (!currentTetromino.getPositionMatrix()[x][y]) {
                    continue;
                }
                int newYPosition = y + currentTetromino.getPosition().y + 1;
                if (!(newYPosition < tetrisBoard[0].length)
                        || tetrisBoard[x + currentTetromino.getPosition().x][newYPosition] != EMPTY_SQUARE_COLOR_ID) {
                    throw new ReachedBottomException();
                }
            }
        }
        currentTetromino.getPosition().y += 1;
    }

    private void onRightClick() {
        currentTetromino.getPosition().x = moveOnXAxis(1);
    }

    private void onLeftClick() {
        currentTetromino.getPosition().x = moveOnXAxis(-1);
    }

    private void onUpClick() {
        currentTetromino.setPositionMatrix(TetrominoRotator.getRotated(currentTetromino.getPositionMatrix()));
    }

    private int moveOnXAxis(int direction) {
        for (int x = 0; x < currentTetromino.getPositionMatrix().length; x++) {
            for (int y = 0; y < currentTetromino.getPositionMatrix()[x].length; y++) {
                if (!currentTetromino.getPositionMatrix()[x][y]) {
                    continue;
                }
                int newXPosition = x + currentTetromino.getPosition().x + direction;
                if (!(newXPosition < tetrisBoard.length) || !(newXPosition >= 0)  || tetrisBoard[newXPosition][y + currentTetromino.getPosition().y] != EMPTY_SQUARE_COLOR_ID) {
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
                    tetrisBoard[x + currentTetromino.getPosition().x][y + currentTetromino.getPosition().y] = currentTetromino.getColorId();
                }
            }
        }
        currentTetromino = nextTetromino;
        nextTetromino = getRandomTetromino();
    }

    private Tetromino getRandomTetromino() {
        return TetrominoFactory.getTetromino(
                TetrominoType.getRandomTetrominoType(),
                tetrisBoard.length);
    }

}
