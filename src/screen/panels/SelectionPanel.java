package screen.panels;

import screen.components.Button;
import src.Constants;

public class SelectionPanel extends Panel {
    private Button playerButton;
    private Button playerAIButton;
    private Button trainingButton;

    public SelectionPanel() {
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
}
