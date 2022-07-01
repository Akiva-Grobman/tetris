package com.akiva.graphics;

import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class TetrisGraphicsBoard extends JPanel {

    static final int TILE_SIZE = 35;
    private final Tile[][] tiles;
    private final Supplier<int[][]> getBoardColors;

    public TetrisGraphicsBoard(Dimension displayDimension, int tileColumnCount, int tileRowCount, Supplier<int[][]> getBoardColors) {
        this.getBoardColors = getBoardColors;
        setPreferredSize(displayDimension);
        setMaximumSize(displayDimension);
        setMinimumSize(displayDimension);
        setBackground(Colors.getDefaultBackgroundColor());
        tiles = getStartingTiles(tileColumnCount, tileRowCount);
        setVisible(true);
    }

    private Tile[][] getStartingTiles(int tileColumnCount, int tileRowCount) {
        Tile[][] tiles = new Tile[tileColumnCount][];
        Color color = Colors.getDefaultBackgroundColor();
        for (int x = 0; x < tileColumnCount; x++) {
            tiles[x] = new Tile[tileRowCount];
            for (int y = 0; y < tileRowCount; y++) {
                tiles[x][y] = new Tile(x, y, color, TILE_SIZE, 0, TILE_SIZE);
            }
        }
        return tiles;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        int[][] boardColors = getBoardColors.get();
        for (int x = 0; x < tiles.length; x++) {
            for (int y = 0; y < tiles[x].length; y++) {
                tiles[x][y].setTileColor(Colors.getColorById(boardColors[x][y]));
                tiles[x][y].draw(graphics);
            }
        }
    }

}
