package screen.panels;

import screen.Screen;
import screen.components.Button;
import src.Constants;

import java.awt.event.ActionEvent;

public class SelectionPanel extends Panel {
    private Screen screen;

    private Button playerButton;
    private Button playerAIButton;
    private Button trainingButton;

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
            this.screen.setPanel(new GamePanel());
        } else if (actionEvent.getSource() == this.playerAIButton) {
            System.out.println("Player vs. AI button clicked");
        } else if (actionEvent.getSource() == this.trainingButton) {
            System.out.println("Train AI button clicked");
        } else {
            System.out.println("Unknown actionEvent. Skipping...");
        }
    }
}
