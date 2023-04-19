package screen;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;


public class GamePanel extends JPanel {
    private final int[] board = new int[3 * 3];

    GamePanel() {
        this.setPreferredSize(new Dimension(1001, 1001));
        this.setBackground(Color.BLACK);
        this.setLayout(null);
    }
}
