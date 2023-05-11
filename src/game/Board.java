package game;


import src.Constants;

import java.awt.Font;
import java.awt.Graphics;

public class Board {
    private final Cell[] cells = new Cell[3 * 3];
    private int turns;

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

    public int getTurns() {
        return this.turns;
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

    public void processMouseClick(int mouseX, int mouseY) {
        // Check if the mouse intersects with any of the cells
        for (Cell cell: this.cells) {
            if (mouseX > cell.getX() + Constants.BOARD_OUTLINE_WIDTH && mouseX < cell.getX() + Constants.CELL_WIDTH &&
                mouseY > cell.getY() + Constants.BOARD_OUTLINE_WIDTH && mouseY < cell.getY() + Constants.CELL_WIDTH) {
                this.takeTurn(cell);
                break;
            }
        }
    }

    private void takeTurn(Cell cell) {
        this.turns++;

        // Alternate X's and O's
        int symbol = (int) Math.pow(-1, this.turns);
        cell.setSymbol(symbol);

        int result = this.checkBoard();
    }

    private int checkBoard() {
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
        return this.checkColumns() || this.checkRows() || this.checkDiagonals() ? 1 : -1;
    }

    private boolean checkColumns() {
        return check(0, 3) || check(1, 3) || check(2, 3);
    }

    private boolean checkRows() {
        return check(0, 1) || check(3, 1) || check(6, 1);
    }

    private boolean checkDiagonals() {
        return check(0, 4) || check(2, 2);
    }

    private boolean check(int startIndex, int step) {
        Cell cell1 = this.cells[startIndex];
        Cell cell2 = this.cells[startIndex + step];
        Cell cell3 = this.cells[startIndex + 2 * step];
        return Math.abs(cell1.getSymbol() + cell2.getSymbol() + cell3.getSymbol()) == 3;
    }
}
