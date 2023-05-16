package src.genetic_algorithm;

import src.Constants;
import src.WeightsUtils;
import src.players.AI;

import java.util.Random;

public class Generation {
    private final AI[] population;
    private AI[] bestParents;
    private int crossoverParent = 0;

    public Generation(AI[] population) {
        this.population = population;
        this.selectParents();
    }

    private void selectParents() {
        AI parent1 = this.population[0];
        AI parent2 = this.population[1];

        for (AI currentAI : this.population) {
            if (currentAI.getFitness() > parent1.getFitness()) {
                parent2 = parent1;
                parent1 = currentAI;
            } else if (currentAI.getFitness() > parent2.getFitness()) {
                parent2 = currentAI;
            }
        }

        System.out.println("Best fitness: " + parent1.getFitness());
        System.out.println("Second best fitness: " + parent2.getFitness());
        this.bestParents = new AI[]{parent1, parent2};
    }

    public AI[] getNewPopulation() {
        AI[] newPopulation = new AI[Constants.POPULATION];
        for (int i = 0; i < newPopulation.length; i++) {
            newPopulation[i] = this.crossover();
        }

        return newPopulation;
    }

    private AI crossover() {
        double[][][] weights = new double[3][][];
        double[][] bias = new double[3][];
        for (int i = 0; i < weights.length; i++) {
            int layerSize = this.bestParents[0].weights[i].length;
            weights[i] = this.selectLayerWeights(i, layerSize);
        }

        for (int i = 0; i < bias.length; i++) {
            double[] newBias = new double[this.bestParents[0].bias[i].length];
            Random rand = new Random();
            for (int j = 0; j < newBias.length; j++) {
                if (rand.nextDouble() < Constants.CROSSOVER_RATE) {
                    this.crossoverParent = 1 - this.crossoverParent;
                }
                newBias[i] = this.bestParents[this.crossoverParent].bias[i][j];
            }
            bias[i] = newBias;
        }

        weights = WeightsUtils.applyMutation(weights);
        bias = WeightsUtils.applyLayerMutation(bias);
        return new AI(weights, bias);
    }

    private double[][] selectLayerWeights(int layer, int layerSize) {
        double[][] layerWeights = new double[layerSize][];
        for (int i = 0; i < layerSize; i++) {
            int neuronSize = this.bestParents[0].weights[layer][i].length;
            layerWeights[i] = this.selectNeuronWeights(layer, i, neuronSize);
        }

        return layerWeights;
    }

    private double[] selectNeuronWeights(int layer, int neuron, int neuronSize) {
        double[] neuronWeights = new double[neuronSize];
        for (int i = 0; i < neuronSize; i++) {
            Random rand = new Random();
            if (rand.nextDouble() < Constants.CROSSOVER_RATE) {
                this.crossoverParent = 1 - this.crossoverParent;
            }
            neuronWeights[i] = this.bestParents[this.crossoverParent].weights[layer][neuron][i];
        }

        return neuronWeights;
    }
}
