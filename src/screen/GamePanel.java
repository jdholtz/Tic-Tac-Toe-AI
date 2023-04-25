package screen;


import game.Board;
import src.Constants;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel implements ActionListener {
    private final Board board;

    GamePanel() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setLayout(null);

        this.board = new Board();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.repaint();
        this.revalidate(); // Needed to redraw every frame
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.board.draw(g);
    }
}
