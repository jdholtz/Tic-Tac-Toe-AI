package src.players;

import src.game.Cell;

import java.util.ArrayList;
import java.util.Random;

public class RandomPlayer extends AI {
    public void move() {
        int randMove = this.getRandomMove();
        this.game.processAIMove(randMove);
    }

    private int getRandomMove() {
        // Pick a random move on the board out of all legal moves
        ArrayList<Integer> validMoves = new ArrayList<>();
        Cell[] cells = this.game.getCells();
        for (int i = 0; i < cells.length; i++) {
            if (cells[i].getSymbol() == 0) {
                validMoves.add(i);
            }
        }

        Random rand = new Random();
        return validMoves.get(rand.nextInt(validMoves.size()));
    }
}
