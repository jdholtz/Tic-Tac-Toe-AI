package src.players;


import src.Constants;
import src.game.Cell;
import src.game.Game;
import src.neural_network.NeuralNetwork;

public class AI extends Player {
    protected Game game;

    private final NeuralNetwork network;
    private int losses;

    public AI() {
        this(null, "Default AI");
    }

    public AI(Game game, String name) {
        super(name);
        this.game = game;
        this.network = new NeuralNetwork();
    }

    public void move() {
        double[] inputs = this.getInputs();
        double[] outputs = this.network.predict(inputs);
        int move = this.getBestValidMove(outputs);
        this.game.processAIMove(move);
    }

    private double[] getInputs() {
        Cell[] cells = this.game.getCells();
        double[] inputs = new double[cells.length * 3];
        for (int i = 0; i < cells.length; i++) {
            int symbol = cells[i].getSymbol();
            // 1 is X, -1 is O, 0 is empty
            inputs[i] = symbol == 1 ? 1 : 0;
            inputs[i + 9] = symbol == -1 ? 1 : 0;
            inputs[i + 18] = symbol == 0 ? 1 : 0;
        }

        return inputs;
    }

    private int getBestValidMove(double[] outputs) {
        int bestOutput = this.getBestOutput(outputs);
        while (this.game.getCells()[bestOutput].getSymbol() != 0) {
            outputs[bestOutput] = -1;
            bestOutput = this.getBestOutput(outputs);
        }

        return bestOutput;
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

    public void setGame(Game game) {
        this.game = game;
    }

    public void incrementLosses() {
        this.losses++;
    }

    public double getFitness() {
        return (Constants.GAMES_PER_SET - this.losses) / (double) Constants.GAMES_PER_SET;
    }
}
