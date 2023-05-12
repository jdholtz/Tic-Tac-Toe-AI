package src.neural_network;


import src.Constants;

import java.util.Random;

public class NeuralNetwork {
    private final Neuron[] hiddenLayer1 = new Neuron[Constants.HIDDEN_LAYER_1_SIZE];
    private final Neuron[] hiddenLayer2 = new Neuron[Constants.HIDDEN_LAYER_2_SIZE];
    private final Neuron[] outputLayer = new Neuron[9];

    public NeuralNetwork() {
        this.initializeLayers();
    }

    private void initializeLayers() {
        // Hidden layers
        for (int i = 0; i < this.hiddenLayer1.length; i++) {
            double[] weights = this.getRandomWeights(9);
            this.hiddenLayer1[i] = new Neuron(weights);
        }

        for (int i = 0; i < this.hiddenLayer2.length; i++) {
            double[] weights = this.getRandomWeights(this.hiddenLayer1.length);
            this.hiddenLayer2[i] = new Neuron(weights);
        }

        // Output layer
        for (int i = 0; i < this.outputLayer.length; i++) {
            double[] weights = this.getRandomWeights(this.hiddenLayer2.length);
            this.outputLayer[i] = new Neuron(weights);
        }
    }

    private double[] getRandomWeights(int size) {
        double[] weights = new double[size];
        for (int i = 0; i < size; i++) {
            Random rand = new Random();
            weights[i] = rand.nextDouble(-1, 1);
        }

        return weights;
    }

    public double[] predict(double[] inputs) {
        double[] hiddenLayer1Outputs = new double[this.hiddenLayer1.length];
        for (int i = 0; i < this.hiddenLayer1.length; i++) {
            double output = this.hiddenLayer1[i].compute(inputs);
            hiddenLayer1Outputs[i] = output;
        }

        double[] hiddenLayer2Outputs = new double[this.hiddenLayer2.length];
        for (int i = 0; i < this.hiddenLayer2.length; i++) {
            double output = this.hiddenLayer2[i].compute(hiddenLayer1Outputs);
            hiddenLayer2Outputs[i] = output;
        }

        double[] outputLayerOutputs = new double[this.outputLayer.length];
        for (int i = 0; i < this.outputLayer.length; i++) {
            double output = this.outputLayer[i].compute(hiddenLayer2Outputs);
            outputLayerOutputs[i] = output;
        }

        return outputLayerOutputs;
    }
}
