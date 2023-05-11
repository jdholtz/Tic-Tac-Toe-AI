package screen;


import screen.panels.Panel;
import screen.panels.SelectionPanel;

import javax.swing.JFrame;

public class Screen {
    private final JFrame mainScreen;
    private Panel panel;

    public Screen () {
        this.mainScreen = new JFrame("Tic-Tac-Toe");
        this.mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainScreen.setResizable(false);
        this.mainScreen.setFocusable(true);

        this.panel = new SelectionPanel(this);
        this.mainScreen.add(this.panel);
        this.mainScreen.pack();
        this.mainScreen.setLocationRelativeTo(null);
        this.mainScreen.setVisible(true);
    }

    public void setPanel(Panel panel) {
        this.mainScreen.remove(this.panel);
        this.panel = panel;
        this.mainScreen.add(panel);
        this.panel.revalidate();
    }
}
