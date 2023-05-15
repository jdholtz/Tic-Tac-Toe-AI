package src.game;

import src.Constants;
import src.players.AI;

public class TrainingSet {
    private TrainingGame currentGame;
    private int totalGames;
    private final AI[] AIs;

    public TrainingSet(AI[] AIs) {
        this.AIs = AIs;
        this.currentGame = new TrainingGame(this.AIs);
    }

    public void run() {
        this.currentGame.run();
        if (this.currentGame.hasEnded()) {
            this.currentGame.onGameEnd();
            this.nextGame();
        }
    }

    private void nextGame() {
        this.totalGames++;
        this.swapAIs();
        this.currentGame = new TrainingGame(this.AIs);
    }

    private void swapAIs() {
        // Swap the AIs so they each get an even amount of playing first and second
        AI tempAI = this.AIs[0];
        this.AIs[0] = this.AIs[1];
        this.AIs[1] = tempAI;
    }

    public boolean isFinished() {
        return this.totalGames >= Constants.GAMES_PER_SET;
    }
}
