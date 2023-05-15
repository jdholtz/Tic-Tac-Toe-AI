package src.neural_network;


import src.Constants;

public class NeuralNetwork {
    private final Neuron[] hiddenLayer1 = new Neuron[Constants.HIDDEN_LAYER_1_SIZE];
    private final Neuron[] hiddenLayer2 = new Neuron[Constants.HIDDEN_LAYER_2_SIZE];
    private final Neuron[] outputLayer = new Neuron[9];

    public NeuralNetwork(double[][][] weights, double[][] bias) {
        this.initializeLayers(weights, bias);
    }

    private void initializeLayers(double[][][] weights, double[][] bias) {
        // Hidden layers
        for (int i = 0; i < this.hiddenLayer1.length; i++) {
            this.hiddenLayer1[i] = new Neuron(weights[0][i], bias[0][i]);
        }

        for (int i = 0; i < this.hiddenLayer2.length; i++) {
            this.hiddenLayer2[i] = new Neuron(weights[1][i], bias[1][i]);
        }

        // Output layer
        for (int i = 0; i < this.outputLayer.length; i++) {
            this.outputLayer[i] = new Neuron(weights[2][i], bias[2][i]);
        }
    }

    public double[] predict(double[] inputs) {
        double[] hiddenLayer1Outputs = new double[this.hiddenLayer1.length];
        for (int i = 0; i < this.hiddenLayer1.length; i++) {
            hiddenLayer1Outputs[i] = this.hiddenLayer1[i].compute(inputs);
        }

        double[] hiddenLayer2Outputs = new double[this.hiddenLayer2.length];
        for (int i = 0; i < this.hiddenLayer2.length; i++) {
            hiddenLayer2Outputs[i] = this.hiddenLayer2[i].compute(hiddenLayer1Outputs);
        }

        double[] outputLayerOutputs = new double[this.outputLayer.length];
        for (int i = 0; i < this.outputLayer.length; i++) {
            outputLayerOutputs[i] = this.outputLayer[i].compute(hiddenLayer2Outputs);
        }

        return outputLayerOutputs;
    }
}
