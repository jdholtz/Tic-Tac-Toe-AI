package game;


import src.Constants;

import java.awt.FontMetrics;
import java.awt.Graphics;

public class Cell {
    private final int x;
    private final int y;
    // 0 means no symbol exists, 1 is X, and -1 is O
    private int symbol;

    Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.symbol = 0;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public int getSymbol() {
        return this.symbol;
    }

    public void setSymbol(int symbol) {
        this.symbol = symbol;
    }

    public void drawSymbol(Graphics g) {
        if (symbol == 0) return;
        String symbolString = symbol == 1 ? "X" : "O";

        // Offset the symbol a little to center it in the cell better
        int offset = 5;
        FontMetrics fontMetrics = g.getFontMetrics();

        int posX = this.x + Constants.CELL_WIDTH / 2 + Constants.BOARD_OUTLINE_WIDTH / 2 - offset;
        int posY = this.y + Constants.CELL_WIDTH / 2 + Constants.BOARD_OUTLINE_WIDTH / 2 + offset;
        // Center the text based on the font
        posX -= (fontMetrics.stringWidth(symbolString) / 2);
        posY += fontMetrics.getAscent() - (fontMetrics.getHeight() / 2);

        g.drawString(symbolString, posX, posY);
    }
}
