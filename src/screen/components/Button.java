package screen.components;

import javax.swing.JButton;
import java.awt.Font;


public class Button extends JButton {
    public Button(String title, int x, int y, int width, int height) {
        this.setFont(new Font("Times New Roman", Font.PLAIN, 22));
        this.setText(title);
        this.setBounds(x - width / 2, y - height / 2, width, height);
        this.setFocusable(false); // So focus is still on the JFrame instead of the JButton
    }
}