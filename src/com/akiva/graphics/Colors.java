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

    public static final int DEFAULT_BACKGROUND_ID = tileColors.size() - 1;

    public static Color getDefaultBackgroundColor() {
        return Color.black;
    }

    public static Color getBoardBoarderColor() {
        return Color.red;
    }

    public static Color getColorById(int colorId) {
        return tileColors.get(colorId);
    }

    public static Color getTextColor() {
        return Color.white;
    }
}
