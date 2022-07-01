package com.akiva;

import com.akiva.gameloop.GameLoop;
import com.akiva.graphics.TetrisGraphicsContainer;
import com.akiva.keyboardinput.KeyboardInputSubscriber;
import com.akiva.logic.TetrisGameLogic;

public class Main {

    public static void main(String[] args) {
        int boardWidth = 9;
        int boardHeight = 22;
        TetrisGameLogic updatable = new TetrisGameLogic(boardWidth, boardHeight);
        TetrisGraphicsContainer drawable = new TetrisGraphicsContainer(
                boardWidth,
                boardHeight,
                updatable::getTetrisBoard,
                updatable::getNextTetromino,
                updatable::getLineExplodedCount
        );
        GameLoop gameLoop = new GameLoop(drawable, updatable);
        KeyboardInputSubscriber keyboardInputSubscriber = new KeyboardInputSubscriber(updatable);
        drawable.addKeyboardListener(keyboardInputSubscriber);
        gameLoop.run();
    }

}
