package src;

import java.util.Random;

public class WeightsUtils {
    public static double[][][] getRandomWeights() {
        double[][][] weights = new double[2][][];
        weights[0] = WeightsUtils.getLayerWeights(Constants.HIDDEN_LAYER_1_SIZE, 27);
        weights[1] = WeightsUtils.getLayerWeights(9, Constants.HIDDEN_LAYER_1_SIZE);
        return weights;
    }

    private static double[][] getLayerWeights(int layerSize, int neuronSize) {
        double[][] layerWeights = new double[layerSize][];
        for (int i = 0; i < layerWeights.length; i++) {
            layerWeights[i] = WeightsUtils.getNeuronWeights(neuronSize);
        }

        return layerWeights;
    }

    private static double[] getNeuronWeights(int size) {
        double[] weights = new double[size];
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            weights[i] = rand.nextDouble(-1, 1);
        }

        return weights;
    }

    public static double[][] getRandomBias() {
        double[][] bias = new double[2][];
        bias[0] = WeightsUtils.getNeuronWeights(Constants.HIDDEN_LAYER_1_SIZE);
        bias[1] = WeightsUtils.getNeuronWeights(9);
        return bias;
    }

    public static double[][][] applyMutation(double[][][] weights) {
        for (int i = 0; i < weights.length; i++) {
            weights[i] = WeightsUtils.applyLayerMutation(weights[i]);
        }

        return weights;
    }

    public static double[][] applyLayerMutation(double[][] weights) {
        for (int i = 0; i < weights.length; i++) {
            for (int j = 0; j < weights[i].length; j++) {
                Random rand = new Random();
                if (rand.nextDouble() < Constants.MUTATION_RATE) {
                    weights[i][j] = rand.nextDouble(-1, 1);
                }
            }
        }

        return weights;
    }
}
