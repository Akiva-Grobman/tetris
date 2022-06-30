package com.akiva.graphics;

import java.awt.*;

public class Tile {

    private final int x;
    private final int y;
    private final int tileSize;
    private Color tileColor;

    Tile(int x, int y, Color tileColor, int tileSize) {
        this.x = x * tileSize;
        this.y = y * tileSize;
        this.tileSize = tileSize;
        this.tileColor = tileColor;
    }

    void draw(Graphics graphics) {
        graphics.setColor(Colors.getBoardBoarderColor());
        graphics.drawLine(this.x, this.y, this.x + this.tileSize, this.y);
        graphics.drawLine(this.x, this.y + this.tileSize, this.x + this.tileSize, this.y + this.tileSize);
        graphics.drawLine(this.x, this.y, this.x, this.y + this.tileSize);
        graphics.drawLine(this.x + this.tileSize, this.y, this.x + this.tileSize, this.y + this.tileSize);
        graphics.setColor(this.tileColor);
        graphics.fillRect(x + 4, y + 4, tileSize - 8, tileSize - 8);
    }

    public void setTileColor(Color tileColor) {
        this.tileColor = tileColor;
    }
}
