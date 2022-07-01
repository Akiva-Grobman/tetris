package com.akiva.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

import static com.akiva.graphics.TetrisGraphicsBoard.TILE_SIZE;

public class NextTetrominoDisplay extends JPanel {

    private final Tile[][] tiles;
    private final Supplier<int[][]> getNextTetromino;

    public NextTetrominoDisplay(int width, int height, Supplier<int[][]> getNextTetromino) {
        this.getNextTetromino = getNextTetromino;
        tiles = getStartingTiles(width);
        Dimension dimension = new Dimension(width, height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setBackground(Colors.getDefaultBackgroundColor());
    }

    private Tile[][] getStartingTiles(int displayWidth) {
        int tileColumnCount = 4;
        int tileRowCount = 4;
        Tile[][] tiles = new Tile[tileColumnCount][];
        Color color = Colors.getDefaultBackgroundColor();
        for (int x = 0; x < tileColumnCount; x++) {
            tiles[x] = new Tile[tileRowCount];
            for (int y = 0; y < tileRowCount; y++) {
                tiles[x][y] = new Tile(x, y, color, TILE_SIZE, displayWidth / 2 - TILE_SIZE * 2, TILE_SIZE);
            }
        }
        return tiles;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int[][] tetrominoColors = getNextTetromino.get();
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y].setTileColor(Colors.getColorById(tetrominoColors[x][y]));
                tiles[x][y].draw(graphics);
            }
        }
    }
}
