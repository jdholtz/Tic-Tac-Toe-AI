package src.game;

import src.Constants;
import src.players.AI;
import src.players.RandomPlayer;

public class TrainingSimulator {
    private int generationNum = 1;
    private boolean training;
    private final TrainingSet[] sets = new TrainingSet[Constants.POPULATION];
    private final AI[] AIs;

    public TrainingSimulator() {
        this.AIs = new AI[]{new AI(), new RandomPlayer()};
        this.initializeSets();
        this.training = true;
    }

    private void initializeSets() {
        for (int i = 0; i < this.sets.length; i++) {
            this.sets[i] = new TrainingSet(this.AIs);
        }
    }

    public void run() {
        if (!this.training) return;
        boolean stillRunning = false;

        for (TrainingSet set : this.sets) {
            if (!set.isFinished()) {
                set.run();
                stillRunning = true;
            }
        }

        if (!stillRunning) {
            this.endGeneration();
        }
    }

    public void endGeneration() {
        this.generationNum++;
        this.initializeSets();
    }

    public int getGeneration() {
        return this.generationNum;
    }

    public void stop() {
        this.training = false;
    }

    public boolean isTraining() {
        return this.training;
    }
}
