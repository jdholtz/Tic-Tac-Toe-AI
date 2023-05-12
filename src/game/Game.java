package game;

import players.AI;
import players.Player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Game implements MouseListener {
    private final Board board;
    private final Player[] players = new Player[2];
    private int turns;

    // -1 indicates the game is still going. 0 indicates a tie. 1 indicates a win
    private int result = -1;

    public Game(boolean AIOpponent) {
        this.board = new Board();
        if (AIOpponent) {
            // Generate a random number 0 or 1
            int randIndex = (int) Math.round(Math.random());
            int AISymbol = randIndex == 0 ? 1 : -1;
            this.players[randIndex] = new AI(this, AISymbol, "AI " + (randIndex + 1));
            this.players[1 - randIndex] = new Player("Player " + (2 - randIndex));
        } else {
            this.players[0] = new Player("Player 1");
            this.players[1] = new Player("Player 2");
        }

        // Player 1 starts so offset Player 2's moves
        this.players[1].changeMove();
    }

    public void run(Graphics g) {
        this.triggerAIMove();
        this.draw(g);
    }

    private void triggerAIMove() {
        if (this.hasEnded()) return;

        for (Player player : this.players) {
            if (player instanceof AI ai && !player.hasMoved()) {
                ai.move();
            }
        }
    }

    private void draw(Graphics g) {
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
        if (this.result != -1) {
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
        this.turns++;

        // Alternate X's and O's
        int symbol = (int) Math.pow(-1, this.turns);
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
