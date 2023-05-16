package src;


public class Constants {
    // Values for the graphics
    public final static int SCREEN_WIDTH = 1920;
    public final static int SCREEN_HEIGHT = 1000;
    public final static int CELL_WIDTH = 200;
    public final static int BOARD_OUTLINE_WIDTH = CELL_WIDTH / 10;

    // Values for the neural network
    public final static int HIDDEN_LAYER_1_SIZE = 18;
    public final static int HIDDEN_LAYER_2_SIZE = 18;

    // Values for training
    public static final int POPULATION = 1000; // Can be a minimum of 2
    public static final int GAMES_PER_SET = 100;
    public static final double CROSSOVER_RATE = 0.1;
    public static final double MUTATION_RATE = 0.05;
}
