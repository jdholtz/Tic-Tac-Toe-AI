package screen;


import game.Board;
import game.Game;
import players.AI;
import players.Actor;
import src.Constants;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

public class GamePanel extends JPanel {
    private final Game game;

    GamePanel() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        this.game = new Game();

        // Only listen to one player. This avoids processing two mouse clicks
        // at the same time
//        this.addMouseListener(this.players[0]);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        super.paintComponent(g);
        this.board.draw(g);
        this.drawPlayerInformation(g);
        this.repaint();
        this.revalidate(); // Needed to redraw every frame
    }

    private void drawPlayerInformation(Graphics g) {
        // Don't draw if the screen is too short
        if (Constants.SCREEN_WIDTH <= 1000) return;

        if (this.board.getResult() != -1) {
            this.drawBoardResult(g);
        }

        int fontSize = Constants.SCREEN_WIDTH / 20;
        g.setFont(new Font("SansSerif", Font.PLAIN, fontSize));

        int posX = (Constants.SCREEN_WIDTH - Constants.CELL_WIDTH * 3) / 4;
        int posY = (Constants.SCREEN_HEIGHT - Constants.CELL_WIDTH * 3) / 2;

        // Set the player's text color to green if it is their turn
        Color color1 = Color.GREEN;
        Color color2 = Color.WHITE;
        if (this.board.getTurns() % 2 == 1) {
            color1 = Color.WHITE;
            color2 = Color.GREEN;
        }

        // Player 1
        g.setColor(color1);
        int[] pos1 = this.getCenteredTextPosition(posX, posY, "Player 1", g);
        g.drawString("Player 1", pos1[0], pos1[1]);

        // Player 2
        g.setColor(color2);
        int[] pos2 = this.getCenteredTextPosition(Constants.SCREEN_WIDTH - posX, posY, "Player 2", g);
        g.drawString("Player 2", pos2[0], pos2[1]);
    }

    private void drawBoardResult(Graphics g) {
        g.setColor(Color.GREEN);
        int fontSize = Constants.SCREEN_WIDTH / 15;
        g.setFont(new Font("SansSerif", Font.PLAIN, fontSize));

        int posX = Constants.SCREEN_WIDTH / 2;
        int posY = Constants.SCREEN_HEIGHT / 2 - Constants.CELL_WIDTH * 2;

        if (this.board.getResult() == 0) {
            // Tie
            int[] pos = this.getCenteredTextPosition(posX, posY, "Tie", g);
            g.drawString("Tie", pos[0], pos[1]);
        } else {
            String player = this.board.getTurns() % 2 == 1 ? "Player 1" : "Player 2";
            int[] pos = this.getCenteredTextPosition(posX, posY, player + " wins!", g);
            g.drawString(player + " wins!", pos[0], pos[1]);
        }
    }

    private int[] getCenteredTextPosition(int x, int y, String text, Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        x -= (fontMetrics.stringWidth(text) / 2);
        y += fontMetrics.getAscent() - (fontMetrics.getHeight() / 2);

        return new int[]{x, y};
    }
}
