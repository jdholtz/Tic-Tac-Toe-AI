package screen;


import game.Board;
import src.Constants;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements MouseListener {
    private final Board board;

    GamePanel() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
        this.addMouseListener(this);

        this.board = new Board();
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.WHITE);
        super.paintComponent(g);
        this.drawPlayerInformation(g);

        g.setColor(Color.WHITE);
        this.board.draw(g);
        this.repaint();
        this.revalidate(); // Needed to redraw every frame
    }

    private void drawPlayerInformation(Graphics g) {
        // Don't draw if the screen is too short
        if (Constants.SCREEN_WIDTH <= 1000) return;

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

    private int[] getCenteredTextPosition(int x, int y, String text, Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        x -= (fontMetrics.stringWidth(text) / 2);
        y += fontMetrics.getAscent() - fontMetrics.getHeight();

        return new int[]{x, y};
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        this.board.processMouseClick(mouseEvent.getX(), mouseEvent.getY());
    }

    // These functions are here to satisfy the MouseListener interface
    @Override
    public void mousePressed(MouseEvent mouseEvent) {}

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {}

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {}

    @Override
    public void mouseExited(MouseEvent mouseEvent) {}
}
