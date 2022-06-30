package com.akiva.graphics;

import java.awt.*;
import java.util.*;
import java.util.List;

public class Colors {

    private static final List<Color> tileColors = new ArrayList<>(){
        {
            add(Color.green);
            add(Color.cyan);
            add(Color.orange);
            add(Color.yellow);
            add(Color.BLUE);
            add(Color.magenta);
            add(Color.PINK);
            add(getDefaultBackgroundColor());
        }
    };

    static Color getDefaultBackgroundColor() {
        return Color.black;
    }

    static Color getBoardBoarderColor() {
        return Color.red;
    }

    public static Color getTileColor(int id) {
        return tileColors.get(id);
    }

    static Color getRandomColor() {
        return new Color(
                (int) (Math.random() * 255),
                (int) (Math.random() * 255),
                (int) (Math.random() * 255)
        );
    }

    public static Color getColorById(int colorId) {
        return tileColors.get(colorId);
    }
}
