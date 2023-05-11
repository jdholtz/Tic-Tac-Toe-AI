package screen;


import screen.panels.SelectionPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Screen {
    private final JFrame mainScreen;
    private final JPanel panel;

    public Screen () {
        this.mainScreen = new JFrame("Tic-Tac-Toe");
        this.mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainScreen.setResizable(false);
        this.mainScreen.setFocusable(true);

        this.panel = new SelectionPanel();
        this.mainScreen.add(this.panel);
        this.mainScreen.pack();
        this.mainScreen.setLocationRelativeTo(null);
        this.mainScreen.setVisible(true);
    }
}
