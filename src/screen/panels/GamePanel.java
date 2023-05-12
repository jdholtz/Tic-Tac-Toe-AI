package src.screen.panels;


import src.game.Game;
import src.players.Player;
import src.Constants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class GamePanel extends Panel {
    private final Game game;

    GamePanel(boolean AIOpponent) {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        this.game = new Game(AIOpponent);
        this.addMouseListener(this.game);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        super.paintComponent(g);
        this.game.run(g);
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

        Player player1 = this.game.getPlayer(0);
        Color color1 = player1.hasMoved() ? Color.WHITE : Color.GREEN;

        g.setColor(color1);
        this.drawString(posX, posY, player1.getName(), g);

        Player player2 = this.game.getPlayer(1);
        Color color2 = player2.hasMoved() ? Color.WHITE : Color.GREEN;

        g.setColor(color2);
        this.drawString(Constants.SCREEN_WIDTH - posX, posY, player2.getName(), g);
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
