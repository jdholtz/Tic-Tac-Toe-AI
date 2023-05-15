package src.screen.panels;

import src.Constants;
import src.game.TrainingSimulator;
import src.screen.components.Button;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;

public class TrainingPanel extends Panel {
    private final TrainingSimulator simulator;
    private final Button stopButton;

    public TrainingPanel() {
        this.simulator = new TrainingSimulator();

        this.stopButton = new Button("Stop training", Constants.SCREEN_WIDTH / 2, Constants.SCREEN_HEIGHT / 2 + Constants.CELL_WIDTH, 200, 70);
        this.stopButton.addActionListener(this);
        this.add(this.stopButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.simulator.run();
        this.drawTrainingInfo(g);

        // Don't repaint if it isn't necessary. This prevents
        // the game from being very laggy if training has been stopped
        if (!this.simulator.isTraining()) return;
        this.repaint();
    }

    private void drawTrainingInfo(Graphics g) {
        g.setColor(Color.GREEN);
        g.setFont(new Font("TimesRoman", Font.BOLD, 96));
        int posX = Constants.SCREEN_WIDTH / 2;
        int posY = Constants.SCREEN_HEIGHT / 2 - Constants.CELL_WIDTH / 2;
        this.drawString(posX, posY, "Training...", g);

        this.drawString(posX, posY + Constants.CELL_WIDTH, "Generation " + this.simulator.getGeneration(), g);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        if (actionEvent.getSource() == this.stopButton) {
            this.simulator.stop();
        } else {
            System.out.println("Unknown actionEvent. Skipping...");
        }
    }
}
