package src;

import java.util.Random;

public class WeightsUtils {
    public static double[][][] getRandomWeights() {
        double[][][] weights = new double[3][][];
        weights[0] = WeightsUtils.getLayerWeights(Constants.HIDDEN_LAYER_1_SIZE, 27);
        weights[1] = WeightsUtils.getLayerWeights(Constants.HIDDEN_LAYER_2_SIZE, Constants.HIDDEN_LAYER_1_SIZE);
        weights[2] = WeightsUtils.getLayerWeights(9, Constants.HIDDEN_LAYER_2_SIZE);
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
        double[][] bias = new double[3][];
        bias[0] = WeightsUtils.getNeuronWeights(Constants.HIDDEN_LAYER_1_SIZE);
        bias[1] = WeightsUtils.getNeuronWeights(Constants.HIDDEN_LAYER_2_SIZE);
        bias[2] = WeightsUtils.getNeuronWeights(9);
        return bias;
    }

}
