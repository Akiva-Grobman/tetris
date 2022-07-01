package com.akiva.graphics;

import java.awt.*;

public class Tile {

    private final int x;
    private final int y;
    private final int tileSize;
    private final int yOffset;
    private final int xOffset;
    private Color tileColor;

    Tile(int x, int y, Color tileColor, int tileSize, int xOffset, int yOffset) {
        this.x = x * tileSize;
        this.y = y * tileSize;
        this.tileSize = tileSize;
        this.tileColor = tileColor;
        this.yOffset = yOffset;
        this.xOffset = xOffset;
    }

    void draw(Graphics graphics) {
        graphics.setColor(Colors.getBoardBoarderColor());
        drawVerticalLine(0, graphics);
        drawVerticalLine(tileSize, graphics);
        drawPerpendicularLine(0, graphics);
        drawPerpendicularLine(tileSize, graphics);
        graphics.setColor(this.tileColor);
        graphics.fillRect(this.xOffset + x + 4, this.yOffset + y + 4, tileSize - 8, tileSize - 8);
    }

    public void setTileColor(Color tileColor) {
        this.tileColor = tileColor;
    }

    private void drawVerticalLine(int yAxisDistanceFromOrigin, Graphics graphics) {
        graphics.drawLine(xOffset + x,
                yOffset + y + yAxisDistanceFromOrigin,
                xOffset + x + tileSize,
                yOffset + y + yAxisDistanceFromOrigin);
    }

    private void drawPerpendicularLine(int xAxisDistanceFromOrigin, Graphics graphics) {
        graphics.drawLine(xOffset + x + xAxisDistanceFromOrigin,
                yOffset + y,
                xOffset + x + xAxisDistanceFromOrigin,
                yOffset + y + tileSize);
    }

}
