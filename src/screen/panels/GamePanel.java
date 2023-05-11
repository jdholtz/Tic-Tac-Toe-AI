package screen.panels;


import game.Game;
import src.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class GamePanel extends Panel {
    private final Game game;

    GamePanel() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        this.game = new Game();
        this.addMouseListener(this.game);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        super.paintComponent(g);
        this.game.draw(g);
        this.drawPlayerInformation(g);
        this.repaint();
        this.revalidate(); // Needed to redraw every frame
    }

    private void drawPlayerInformation(Graphics g) {
        // Don't draw if the screen is too short
        if (Constants.SCREEN_WIDTH <= 1000) return;

        if (this.game.getResult() != -1) {
            this.drawBoardResult(g);
        }

        int fontSize = Constants.SCREEN_WIDTH / 20;
        g.setFont(new Font("SansSerif", Font.PLAIN, fontSize));

        int posX = (Constants.SCREEN_WIDTH - Constants.CELL_WIDTH * 3) / 4;
        int posY = (Constants.SCREEN_HEIGHT - Constants.CELL_WIDTH * 3) / 2;

        // Set the player's text color to green if it is their turn
        Color color1 = Color.GREEN;
        Color color2 = Color.WHITE;
        if (this.game.getTurns() % 2 == 1) {
            color1 = Color.WHITE;
            color2 = Color.GREEN;
        }

        // Player 1
        g.setColor(color1);
        int[] pos1 = this.getCenteredTextPosition(posX, posY, "Player 1", g);
        this.drawString(posX, posY, "Player 1", g);

        // Player 2
        g.setColor(color2);
        this.drawString(Constants.SCREEN_WIDTH - posX, posY, "Player 2", g);
    }

    private void drawBoardResult(Graphics g) {
        g.setColor(Color.ORANGE);
        int fontSize = Constants.SCREEN_WIDTH / 15;
        g.setFont(new Font("SansSerif", Font.PLAIN, fontSize));

        int posX = Constants.SCREEN_WIDTH / 2;
        int posY = Constants.SCREEN_HEIGHT / 2 + Constants.CELL_WIDTH * 2;

        if (this.game.getResult() == 0) {
            // Tie
            this.drawString(posX, posY, "Tie", g);
        } else {
            String player = this.game.getTurns() % 2 == 1 ? "Player 1" : "Player 2";
            this.drawString(posX, posY, player + " wins!", g);
        }
    }
}
