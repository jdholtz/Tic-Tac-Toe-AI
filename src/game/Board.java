package src.game;


import src.Constants;

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

    public Cell[] getCells() {
        return this.cells;
    }

    public void draw(Graphics g) {
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

    public int check() {
        // First, check if someone has won
        int result = checkWin();

        // Last, check a tie
        boolean tie = true;
        for (Cell cell: this.cells) {
            if (cell.getSymbol() == 0) {
                tie = false;
                break;
            }
        }

        if (tie && result == -1) {
            return 0;
        }

        return result;
    }

    private int checkWin() {
        return this.checkColumnWins() || this.checkRowWins() || this.checkDiagonalWins() ? 1 : -1;
    }

    private boolean checkColumnWins() {
        return checkWins(0, 3) || checkWins(1, 3) || checkWins(2, 3);
    }

    private boolean checkRowWins() {
        return checkWins(0, 1) || checkWins(3, 1) || checkWins(6, 1);
    }

    private boolean checkDiagonalWins() {
        return checkWins(0, 4) || checkWins(2, 2);
    }

    private boolean checkWins(int startIndex, int step) {
        Cell cell1 = this.cells[startIndex];
        Cell cell2 = this.cells[startIndex + step];
        Cell cell3 = this.cells[startIndex + 2 * step];
        return Math.abs(cell1.getSymbol() + cell2.getSymbol() + cell3.getSymbol()) == 3;
    }
}
