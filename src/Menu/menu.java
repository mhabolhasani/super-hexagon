package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;

import Users.UserManager;

public class menu extends JFrame implements MouseListener {
    JButton newGame;
    JButton History;
    JButton Setting;
    JButton Quit;
    JLabel best;
    static JPanel keyPanel;
    static Setting settingPanel;
    static History historyPanel;

    public menu() {
        UserManager userManager  = new UserManager("src/Users/users.json");
        settingPanel = new Setting();
        historyPanel = new History();
        settingPanel.setVisible(false);
        historyPanel.setVisible(false);
        settingPanel.setBounds(0, 0, 800, 600);
        historyPanel.setBounds(0, 0, 800, 600);
        this.add(settingPanel);
        this.add(historyPanel);
        if(!MusicPlayer.isRunning) {
            MusicPlayer.playAudio("song1.wav");
        }
        newGame = createStyledButton("New Game");
        History = createStyledButton("History");
        Setting = createStyledButton("Setting");
        Quit = createStyledButton("Quit");
        DecimalFormat df = new DecimalFormat("#.###");
        best = new JLabel(String.format("Best record is:" + df.format(userManager.getBest())));
        best.setFont(new Font("Arial", Font.BOLD, 18));
        best.setForeground(Color.WHITE);
        best.setHorizontalAlignment(JLabel.CENTER);

        keyPanel = new JPanel();
        keyPanel.setBounds(250, 100, 300, 550);
        keyPanel.setBackground(new Color(30, 30, 30));
        keyPanel.setLayout(null);
        keyPanel.setVisible(true);

        newGame.setBounds(0, 35, 300, 70);
        History.setBounds(0, 115, 300, 70);
        Setting.setBounds(0, 195, 300, 70);
        Quit.setBounds(0, 275, 300, 70);
        best.setBounds(0, 355, 300, 70);

        newGame.addMouseListener(this);
        History.addMouseListener(this);
        Setting.addMouseListener(this);
        Quit.addMouseListener(this);

        this.setTitle("Super Hexagon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(new Color(20, 20, 20));
        this.add(keyPanel);

        keyPanel.add(newGame);
        keyPanel.add(History);
        keyPanel.add(Setting);
        keyPanel.add(Quit);
        keyPanel.add(best);

        this.setVisible(true);
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder());
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    public static void backToMenu(JPanel panel) {
        panel.setVisible(false);
        keyPanel.setVisible(true);
    }

    public static void main(String[] args) {
        new menu();
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {}

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (mouseEvent.getSource().equals(newGame)) {
            this.dispose();
            new GamePreview();
        } else if (mouseEvent.getSource().equals(History)) {
            this.historyPanel.setVisible(true);
            this.keyPanel.setVisible(false);
            this.revalidate();
            this.repaint();
        } else if (mouseEvent.getSource().equals(Setting)) {
            this.settingPanel.setVisible(true);
            this.keyPanel.setVisible(false);
            this.revalidate();
            this.repaint();
        } else if (mouseEvent.getSource().equals(Quit)) {
            this.setVisible(false);
            System.exit(0);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }
}
