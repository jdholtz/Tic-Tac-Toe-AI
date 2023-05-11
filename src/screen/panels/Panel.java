package screen.panels;

import src.Constants;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel extends JPanel implements ActionListener {
    Panel() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.drawTitle(g);
    }

    private void drawTitle(Graphics g) {
        g.setFont(new Font("TimesRoman", Font.BOLD, 96));
        g.setColor(Color.ORANGE);

        int x = Constants.SCREEN_WIDTH / 2;
        int y = Constants.SCREEN_HEIGHT / 2 - Constants.CELL_WIDTH * 2;
        this.drawString(x, y, "Tic-Tac-Toe AI", g);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {}

    protected void drawString(int x, int y, String text, Graphics g) {
        int[] pos = this.getCenteredTextPosition(x, y, text, g);
        g.drawString(text, pos[0], pos[1]);
    }

    protected int[] getCenteredTextPosition(int x, int y, String text, Graphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        x -= (fontMetrics.stringWidth(text) / 2);
        y += fontMetrics.getAscent() - (fontMetrics.getHeight() / 2);
        return new int[]{x, y};
    }
}
