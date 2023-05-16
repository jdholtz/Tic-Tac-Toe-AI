package src.genetic_algorithm;

import src.Constants;
import src.WeightsUtils;
import src.players.AI;

import java.util.Random;

public class Generation {
    private final AI[] population;
    private double totalFitness;
    private int crossoverParent = 0;

    public Generation(AI[] population) {
        this.population = population;
        this.calculateFitness();
    }

    private void calculateFitness() {
        AI ai1 = this.population[0];

        for (AI ai : this.population) {
            this.totalFitness += ai.getFitness();

            // Calculate the best fitness for the generation
            if (ai.getFitness() > ai1.getFitness()) {
                ai1 = ai;
            }
        }
        System.out.println("Best fitness: " + ai1.getFitness());
        System.out.println("Average fitness: " + this.totalFitness / this.population.length);
    }

    public AI[] getNewPopulation() {
        AI[] newPopulation = new AI[Constants.POPULATION];
        for (int i = 0; i < newPopulation.length; i++) {
            AI[] parents = this.selectParentsNew();
            newPopulation[i] = this.crossover(parents);
        }

        return newPopulation;
    }

    private AI[] selectParentsNew() {
        AI parent1 = this.selectParent();
        AI parent2 = this.selectParent();
        return new AI[]{parent1, parent2};
    }

    private AI selectParent() {
        double rand = new Random().nextDouble(this.totalFitness);
        double currentSum = 0;

        for (AI ai : this.population) {
            currentSum += ai.getFitness();
            if (currentSum > rand) {
                return ai;
            }
        }

        // This will never actually be reached. It's here to please the compiler.
        return this.population[0];
    }

    private AI crossover(AI[] parents) {
        double[][][] weights = new double[2][][];
        double[][] bias = new double[2][];
        for (int i = 0; i < weights.length; i++) {
            int layerSize = parents[0].weights[i].length;
            weights[i] = this.selectLayerWeights(parents, i, layerSize);
        }

        for (int i = 0; i < bias.length; i++) {
            double[] newBias = new double[parents[0].bias[i].length];
            Random rand = new Random();
            for (int j = 0; j < newBias.length; j++) {
                if (rand.nextDouble() < Constants.CROSSOVER_RATE) {
                    this.crossoverParent = 1 - this.crossoverParent;
                }
                newBias[i] = parents[this.crossoverParent].bias[i][j];
            }
            bias[i] = newBias;
        }

        weights = WeightsUtils.applyMutation(weights);
        bias = WeightsUtils.applyLayerMutation(bias);
        return new AI(weights, bias);
    }

    private double[][] selectLayerWeights(AI[] parents, int layer, int layerSize) {
        double[][] layerWeights = new double[layerSize][];
        for (int i = 0; i < layerSize; i++) {
            int neuronSize = parents[0].weights[layer][i].length;
            layerWeights[i] = this.selectNeuronWeights(parents, layer, i, neuronSize);
        }

        return layerWeights;
    }

    private double[] selectNeuronWeights(AI[] parents, int layer, int neuron, int neuronSize) {
        double[] neuronWeights = new double[neuronSize];
        for (int i = 0; i < neuronSize; i++) {
            Random rand = new Random();
            if (rand.nextDouble() < Constants.CROSSOVER_RATE) {
                this.crossoverParent = 1 - this.crossoverParent;
            }
            neuronWeights[i] = parents[this.crossoverParent].weights[layer][neuron][i];
        }

        return neuronWeights;
    }
}
