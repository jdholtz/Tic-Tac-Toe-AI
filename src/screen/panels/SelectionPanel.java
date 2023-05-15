package src.screen.panels;

import src.screen.Screen;
import src.screen.components.Button;
import src.Constants;

import java.awt.event.ActionEvent;

public class SelectionPanel extends Panel {
    private final Screen screen;

    private final Button playerButton;
    private final Button playerAIButton;
    private final Button trainingButton;

    public SelectionPanel(Screen screen) {
        this.screen = screen;

        this.playerButton = new Button("Player vs. Player", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 4, 200, 70);
        this.playerButton.addActionListener(this);
        this.add(this.playerButton);

        this.playerAIButton = new Button("Player vs. AI", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2, 200, 70);
        this.playerAIButton.addActionListener(this);
        this.add(this.playerAIButton);

        this.trainingButton = new Button("Train AI", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 4 * 3, 200, 70);
        this.trainingButton.addActionListener(this);
        this.add(this.trainingButton);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.playerButton) {
            this.screen.setPanel(new GamePanel(false));
        } else if (actionEvent.getSource() == this.playerAIButton) {
            this.screen.setPanel(new GamePanel(true));
        } else if (actionEvent.getSource() == this.trainingButton) {
            this.screen.setPanel(new TrainingPanel());
        } else {
            System.out.println("Unknown actionEvent. Skipping...");
        }
    }
}
