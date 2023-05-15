package src.neural_network;

public class Neuron {
    private final double[] weights;
    private final double bias;

    public Neuron(double[] weights, double bias) {
        this.weights = weights;
        this.bias = bias;
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
