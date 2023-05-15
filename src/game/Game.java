package src.game;

import src.players.AI;
import src.players.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game implements MouseListener {
    protected Player[] players = new Player[2];
    // -1 indicates the game is still going. 0 indicates a tie. 1 indicates a win
    protected int result = -1;

    private final Board board;
    private int turns;

    public Game() {
        this.board = new Board();
    }

    public Game(boolean AIOpponent) {
        this();
        if (AIOpponent) {
            // Generate a random number 0 or 1
            int randIndex = (int) Math.round(Math.random());
            this.players[randIndex] = new AI(this, "AI 1");
            this.players[1 - randIndex] = new Player("Player 1");
        } else {
            this.players[0] = new Player("Player 1");
            this.players[1] = new Player("Player 2");
        }

        // Player 1 starts so offset Player 2's moves
        this.players[1].changeMove();
    }

    public void run() {
        if (this.hasEnded()) return;

        this.triggerAIMove();
    }

    private void triggerAIMove() {
        for (Player player : this.players) {
            if (player instanceof AI ai && !player.hasMoved()) {
                ai.move();
                break;
            }
        }
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

    public Player getPlayer(int playerLocation) {
        return this.players[playerLocation];
    }

    public Cell[] getCells() {
        return this.board.getCells();
    }

    public boolean hasEnded() {
        return this.result != -1;
    }

    public void processAIMove(int cellLocation) {
        this.takeTurn(this.board.getCells()[cellLocation]);
    }

    public void processMouseClick(int mouseX, int mouseY) {
        // Make sure the game is still being played
        if (this.hasEnded()) {
            return;
        }

        // Check if the mouse intersects with any of the cells
        for (Cell cell : this.board.getCells()) {
            if (mouseX > cell.getX() + src.Constants.BOARD_OUTLINE_WIDTH &&
                    mouseX < cell.getX() + src.Constants.CELL_WIDTH &&
                    mouseY > cell.getY() + src.Constants.BOARD_OUTLINE_WIDTH &&
                    mouseY < cell.getY() + src.Constants.CELL_WIDTH) {
                this.takeTurn(cell);
                break;
            }
        }
    }

    private void takeTurn(Cell cell) {
        if (this.hasEnded() || cell.getSymbol() != 0) return;
        this.turns++;

        // Alternate X's and O's
        int symbol = (int) Math.pow(-1, this.turns - 1);
        cell.setSymbol(symbol);

        this.result = this.board.check();
        this.processPlayerMove();
    }

    private void processPlayerMove() {
        for (Player player : this.players) {
            player.changeMove();
        }
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
