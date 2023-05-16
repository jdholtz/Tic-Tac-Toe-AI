package src.game;

import src.Constants;
import src.WeightsUtils;
import src.genetic_algorithm.Generation;
import src.players.AI;
import src.players.RandomPlayer;

import java.util.ArrayList;

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
        ArrayList<Object> weightsAndBiases = WeightsUtils.loadWeights();
        double[][][] weights = (double[][][]) weightsAndBiases.get(0);
        double[][] bias = (double[][]) weightsAndBiases.get(1);
        for (int i = 0; i < this.population.length; i++) {
            this.population[i] = new AI(weights, bias);
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
        this.setNewMutationRate();
        Generation generation = new Generation(this.population, this.generationNum);
        this.population = generation.getNewPopulation();
        this.generationNum++;
        this.initializeSets();
    }

    private void setNewMutationRate() {
        if (this.generationNum > 500 || this.generationNum % 10 != 0) {
            return;
        }

        Constants.MUTATION_RATE -= Constants.MUTATION_RATE * 0.1;
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
