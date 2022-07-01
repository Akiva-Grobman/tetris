package com.akiva.tetrominos;

public enum TetrominoType {
    I(0),
    O(1),
    T(2),
    S(3),
    Z(4),
    J(5),
    L(6);

    public static TetrominoType getRandomTetrominoType() {
        return TetrominoType.values()[(int) (Math.random() * TetrominoType.values().length)];
    }

    private final int colorId;

    TetrominoType(int colorId) {
        this.colorId = colorId;
    }

    public int getColorId() {
        return colorId;
    }

}
