package com.akiva.graphics;


import javax.swing.*;
import java.awt.*;
import java.util.function.Supplier;

public class ExplodingCounter extends JPanel {

    private final Supplier<Integer> getExplodingCount;

    public ExplodingCounter(int width, int height, Supplier<Integer> getExplodingCount) {
        this.getExplodingCount = getExplodingCount;
        Dimension dimension = new Dimension(width, height);
        setPreferredSize(dimension);
        setMaximumSize(dimension);
        setMinimumSize(dimension);
        setBackground(Colors.getDefaultBackgroundColor());
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        String explodingMessage = "Explosion Count: ";
        String explodingValue = String.valueOf(getExplodingCount.get());
        int textYAxisPosition = getHeight() / 10;
        graphics.setColor(Colors.getTextColor());
        graphics.drawString(explodingMessage,
                getCenteredXPositionForString(explodingMessage, graphics),
                textYAxisPosition);
        graphics.setFont(new Font(graphics.getFont().getFontName(), graphics.getFont().getStyle(), 40));
        graphics.drawString(explodingValue,
                getCenteredXPositionForString(explodingValue, graphics),
                textYAxisPosition + 50);
    }

    private int getCenteredXPositionForString(String str, Graphics graphics) {
        return getWidth() / 2 - graphics.getFontMetrics().stringWidth(str) / 2;
    }

}
