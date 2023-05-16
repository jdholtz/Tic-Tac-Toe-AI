package src.game;

import src.Constants;
import src.genetic_algorithm.Generation;
import src.players.AI;
import src.players.RandomPlayer;

public class TrainingSimulator {
    private int generationNum = 1;
    private boolean training;
    private final TrainingSet[] sets = new TrainingSet[Constants.POPULATION];
    private AI[] population = new AI[Constants.POPULATION];

    public TrainingSimulator() {
        this.initializePopulation();
        this.initializeSets();
        this.training = true;
    }

    private void initializePopulation() {
        for (int i = 0; i < this.population.length; i++) {
            this.population[i] = new AI();
        }

    }

    private void initializeSets() {
        for (int i = 0; i < this.sets.length; i++) {
            AI[] players = new AI[]{this.population[i], new RandomPlayer()};
            this.sets[i] = new TrainingSet(players);
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
        Generation generation = new Generation(this.population);
        this.population = generation.getNewPopulation();
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
