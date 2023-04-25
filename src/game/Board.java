package game;


import src.Constants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

public class Board {
    private final Cell[] cells = new Cell[3 * 3];

    public Board() {
        this.initializeCells();
    }

    private void initializeCells() {
        for (int i = 0; i < 3; i++) {
            int cellX = Constants.SCREEN_WIDTH / 2 + (int) (Constants.CELL_WIDTH * (i - 1.5));
            for (int j = 0; j < 3; j++) {
                int cellY = Constants.SCREEN_HEIGHT / 2 + (int) (Constants.CELL_WIDTH * (j - 1.5));
                cells[i + j * 3] = new Cell(cellX, cellY);
            }
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);

        int fontSize = Constants.CELL_WIDTH - Constants.BOARD_OUTLINE_WIDTH * 2;
        g.setFont(new Font("SansSerif", Font.PLAIN, fontSize));
        int rectLength = Constants.CELL_WIDTH * 3 + Constants.BOARD_OUTLINE_WIDTH;

        // Draw the top and left borders
        Cell initialCell = this.cells[0];
        g.fillRect(initialCell.getX(), initialCell.getY(), rectLength, Constants.BOARD_OUTLINE_WIDTH);
        g.fillRect(initialCell.getX(), initialCell.getY(), Constants.BOARD_OUTLINE_WIDTH, rectLength);

        for (int i = 0; i < this.cells.length; i++) {
            Cell cell = this.cells[i];

            // Draw horizontal lines
            if (i % 3 == 0) {
                g.fillRect(cell.getX(), cell.getY() + Constants.CELL_WIDTH, rectLength, Constants.BOARD_OUTLINE_WIDTH);
            }

            // Draw vertical lines
            if (i < 3) {
                g.fillRect(cell.getX() + Constants.CELL_WIDTH, cell.getY(), Constants.BOARD_OUTLINE_WIDTH, rectLength);
            }

            cell.drawSymbol(g);
        }
    }
}
