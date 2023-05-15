package src.game;

import src.Constants;
import src.players.AI;
import src.players.RandomPlayer;

public class TrainingSimulator {
    private int generationNum = 1;
    private boolean training;
    private final TrainingSet[] sets = new TrainingSet[Constants.POPULATION];

    public TrainingSimulator() {
        this.initializeSets();
        this.training = true;
    }

    private void initializeSets() {
        AI[] AIs = this.getNewPopulation();
        this.initializeSets(AIs);
    }

    private void initializeSets(AI[] population) {
        for (int i = 0; i < this.sets.length; i++) {
            AI[] players = new AI[]{population[i], new RandomPlayer()};
            this.sets[i] = new TrainingSet(players);
        }
    }

    private AI[] getNewPopulation() {
        AI[] population = new AI[Constants.POPULATION];
        for (int i = 0; i < population.length; i++) {
            population[i] = new AI();
        }

        return population;
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
