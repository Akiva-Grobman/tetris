package com.akiva.graphics;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private final JPanel graphicsContainer;

    public Window(int width, int height) {
        graphicsContainer = new JPanel();
        Dimension graphicsContainerDimension = new Dimension(width, height);
        graphicsContainer.setLayout(new BorderLayout());
        graphicsContainer.setPreferredSize(graphicsContainerDimension);
        graphicsContainer.setMaximumSize(graphicsContainerDimension);
        graphicsContainer.setMinimumSize(graphicsContainerDimension);
        graphicsContainer.setBackground(Colors.getDefaultBackgroundColor());
        graphicsContainer.setVisible(true);

        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        add(graphicsContainer);
        setVisible(true);
    }

    public void addComponent(Component component, String location) {
        graphicsContainer.add(component, location);
    }

}