package src.players;


import src.game.Cell;
import src.game.Game;
import src.neural_network.NeuralNetwork;

public class AI extends Player {
    private final Game game;
    private final int currentSymbol;
    private final NeuralNetwork network;

    public AI(Game game, int currentSymbol, String name) {
        super(name);
        this.game = game;
        this.currentSymbol = currentSymbol;
        this.network = new NeuralNetwork();
    }

    public void move() {
        double[] inputs = this.getInputs();
        double[] outputs = this.network.predict(inputs);
        int move = this.getBestOutput(outputs);
        this.game.processAIMove(move);
    }

    private double[] getInputs() {
        Cell[] cells = this.game.getCells();
        double[] inputs = new double[cells.length];
        for (int i = 0; i < inputs.length; i++) {
            int symbol = cells[i].getSymbol();
            // This expression converts the symbol (O: -1, X: 1, nothing: 0) to
            // 1: The AI's symbol, 0: No symbol, -1: The opponent's symbol
            inputs[i] = symbol * this.currentSymbol;
        }

        return inputs;
    }

    /**
     * Returns the index of the highest number
     */
    private int getBestOutput(double[] outputs) {
        int max = 0;

        for (int i = 1; i < outputs.length; i++) {
            if (outputs[i] > outputs[max]) {
                max = i;
            }
        }
        return max;
    }
}
