package Game;

import javax.swing.*;

import Users.*;

public class gameFrame extends JFrame {
    public gameFrame(User user){
        this.setTitle("Super Hexagon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.add(new GamePanel(user , this));
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}