package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game implements MouseListener {
    private final Board board;
    private int turns;

    // -1 indicates the game is still going. 0 indicates a tie. 1 indicates a win
    private int result = -1;

    public Game() {
        this.board = new Board();
    }

    public void draw(Graphics g) {
        g.setColor(Color.WHITE);
        this.board.draw(g);
    }

    public int getResult() {
        return this.result;
    }

    public int getTurns() {
        return this.turns;
    }

    public void processAIMove(int cellNum) {
        this.takeTurn(this.board.getCells()[cellNum]);
    }

    public boolean hasEnded() {
        return this.result != -1;
    }

    public void processMouseClick(int mouseX, int mouseY) {
        // Make sure the game is still being played
        if (this.result != -1) {
            return;
        }

        // Check if the mouse intersects with any of the cells
        for (Cell cell: this.board.getCells()) {
            if (mouseX > cell.getX() + src.Constants.BOARD_OUTLINE_WIDTH && mouseX < cell.getX() + src.Constants.CELL_WIDTH &&
                mouseY > cell.getY() + src.Constants.BOARD_OUTLINE_WIDTH && mouseY < cell.getY() + src.Constants.CELL_WIDTH) {
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

        this.result = this.board.check();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.processMouseClick(mouseEvent.getX(), mouseEvent.getY());
    }

    // These functions are here to satisfy the MouseListener interface
    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
