package src.neural_network;

import java.util.Random;

public class Neuron {
    private final double[] weights;
    private final double bias;

    public Neuron(double[] weights) {
        this.weights = weights;

        // Initialize the bias randomly from -1 to 1
        Random random = new Random();
        this.bias = random.nextDouble(-1, 1);
    }

    public double compute(double[] inputs) {
        double sum = this.bias;
        for (int i = 0; i < inputs.length; i++) {
           sum += inputs[i] * this.weights[i];
        }

        return this.sigmoid(sum);
    }

    private double sigmoid(double i) {
        return 1 / (1 + Math.exp(-1 * i));
    }
}
