package src.game;

import src.players.AI;

public class TrainingGame extends Game {
    public TrainingGame(AI[] players) {
        players[0].setGame(this);
        players[1].setGame(this);
        this.players = players;

        // Set the first player to move first
        this.players[0].setMoved(false);
        this.players[1].setMoved(true);
    }

    public void onGameEnd() {
        // The game is a draw, so don't increment losses for any AI
        if (this.result != 1) {
            return;
        };

        // Whoever didn't move last has lost
        if (!this.players[0].hasMoved()) {
            ((AI) this.players[0]).incrementLosses();
        } else {
            ((AI) this.players[1]).incrementLosses();
        }
    }
}
