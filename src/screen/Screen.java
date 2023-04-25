package screen;


import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Screen implements KeyListener {
    private JFrame mainScreen;
    private JPanel panel;

    public Screen () {
        this.mainScreen = new JFrame("Tic-Tac-Toe");
        this.mainScreen.addKeyListener(this);
        this.mainScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainScreen.setResizable(false);
        this.mainScreen.setFocusable(true);

        this.panel = new GamePanel();
        this.mainScreen.add(this.panel);
        this.mainScreen.pack();
        this.mainScreen.setLocationRelativeTo(null);
        this.mainScreen.setVisible(true);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {}

    // These functions are here to satisfy the KeyListener interface
    @Override
    public void keyTyped(KeyEvent keyEvent) {}

    @Override
    public void keyReleased(KeyEvent keyEvent) {}
}
