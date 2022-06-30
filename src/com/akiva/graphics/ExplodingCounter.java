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
        graphics.setColor(Colors.getTextColor());
        graphics.drawString("Explosion Count: ", getWidth() / 2 - graphics.getFontMetrics().stringWidth("Explosion Count: ") / 2, getHeight() / 10);
        graphics.setFont(new Font(graphics.getFont().getFontName(), graphics.getFont().getStyle(), 40));
        graphics.drawString(String.valueOf(getExplodingCount.get()), getWidth() / 2 - graphics.getFontMetrics().stringWidth(String.valueOf(getExplodingCount.get())) / 2, getHeight() / 10 + 50);
    }
}
