package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import Menu.*;
import Users.*;

class GamePanel extends JPanel implements KeyListener {
    public double angle;
    private Graphics2D graphics2D;
    private static int centerX;
    private static int centerY;
    private double relativeAngleOfMahlar;
    private boolean isStopped;
    private Mahlar mahlar;
    private patternManger pattern;
    private boolean state;
    private JPanel stoppedPanel;
    private JPanel gameOverPanel;
    private gameFrame frame;
    private User user;
    private float elapsedTime;

    public GamePanel(User user , gameFrame frame) {
        this.removeAll();
        this.revalidate();
        this.repaint();
        this.relativeAngleOfMahlar = 0;
        this.isStopped = false;
        this.user = user;
        this.frame = frame;
        this.state = false;
        this.isStopped = false;

        this.setLayout(null);

        JLabel best = new JLabel("Best : " + user.getBestRecord()); // adding best later
        best.setBounds(0, 0, 75, 75);
        best.setForeground(Color.WHITE);
        best.setBackground(Color.BLACK);
        best.setOpaque(true);
        best.setVisible(true);

        JLabel time = new JLabel();
        time.setBounds(725, 0, 75, 75);
        time.setForeground(Color.WHITE);
        time.setBackground(Color.BLACK);
        time.setOpaque(true);
        time.setVisible(true);

        this.add(best);
        this.add(time);
        this.addKeyListener(this);
        this.setFocusable(true);

        new ColorChange(this);

        Timer timer = new Timer(16, e -> {
            if (!this.isStopped) {
                this.angle += 0.05;
                this.elapsedTime += 16;
                time.setText("Time : " + this.elapsedTime / 1000);
                repaint();
            }
        });
        timer.start();
    }

    public static int getCenterX() {
        return centerX;
    }

    public static int getCenterY() {
        return centerY;
    }

    public void setGraphics2D(Graphics2D graphics2D) {
        if (this.graphics2D != null) {
            this.graphics2D = graphics2D;
        }
    }

    public void setStoppedPanel() {
        if (this.stoppedPanel == null) {
            this.stoppedPanel = new JPanel();
            this.stoppedPanel.setLayout(new FlowLayout());
            this.stoppedPanel.setBounds(this.getWidth() / 2 - 150, this.getHeight() / 2 - 50, 300, 100);
            this.stoppedPanel.setBackground(Color.DARK_GRAY);

            JButton backToMenu = new JButton("BACK TO MENU");
            JButton continueGame = new JButton("CONTINUE");

            backToMenu.setFocusable(false);
            continueGame.setFocusable(false);

            backToMenu.setForeground(Color.WHITE);
            backToMenu.setBackground(Color.RED);
            backToMenu.setOpaque(true);

            continueGame.setForeground(Color.WHITE);
            continueGame.setBackground(Color.GREEN);
            continueGame.setOpaque(true);

            backToMenu.addActionListener(e -> {
                if(Setting.isSaveable()) {
                    this.saveToFile();
                }
                this.pattern.update();
                this.frame.dispose();
                new menu();
            });

            continueGame.addActionListener(e -> {
                this.stoppedPanel.setVisible(false);
                isStopped = false;
            });

            this.stoppedPanel.add(continueGame);
            this.stoppedPanel.add(backToMenu);
            this.add(this.stoppedPanel);
            this.revalidate();
        }
    }

    private int[] getXHexagon() {
        int[] xPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (centerX + 50 * Math.cos(i * Math.PI / 3 + this.angle));
        }
        return xPoints;
    }

    private int[] getYHexagon() {
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            yPoints[i] = (int) (centerY + 50 * Math.sin(i * Math.PI / 3 + this.angle));
        }
        return yPoints;
    }

    private void paintLines(Graphics g, int[] xPoints, int[] yPoints ) {
        for (int i = 0; i < 6; i++) {
            int endX = (int) (centerX + 800 * Math.cos(i * Math.PI / 3 + this.angle));
            int endY = (int) (centerY + 800 * Math.sin(i * Math.PI / 3 + this.angle));
            g.drawLine(xPoints[i], yPoints[i], endX, endY);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (!this.isStopped) {
            Graphics2D g2D = (Graphics2D) g;
            setGraphics2D(g2D);
            super.paintComponent(g);
            GamePanel.centerX = getWidth() / 2;
            GamePanel.centerY = getHeight() / 2;
            int[] xPoints = getXHexagon();
            int[] yPoints = getYHexagon();
            g2D.drawPolygon(xPoints, yPoints, 6);
            paintLines(g, xPoints, yPoints);
            this.pattern = new patternManger(g2D, this.angle);
            this.mahlar = new Mahlar(this.relativeAngleOfMahlar + this.angle);
            this.mahlar.drawMahlar(g2D);
            this.state = Collision.col(
                    this.mahlar,
                    this.pattern.patternList.get(0).mainPattern,
                    this.centerX,
                    this.centerY
            );
            if (this.state) {
                this.isStopped = true;
                this.gameOverPanel();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()) {
            case 'a':
                this.relativeAngleOfMahlar -= Math.PI / 3;
                break;
            case 'd':
                this.relativeAngleOfMahlar += Math.PI / 3;
                break;
            case 'c':
                this.isStopped = true;
                this.stoppedPanel(true);
                break;
            case 'v':
                this.isStopped = false;
                this.stoppedPanel(false);
                break;
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    public void stoppedPanel(boolean state) {
        setStoppedPanel();
        this.stoppedPanel.setVisible(state);
    }

    public void gameOverPanel() {
        if(this.gameOverPanel == null) {
            this.gameOverPanel = new JPanel();
            gameOverPanel.setLayout(new BoxLayout(gameOverPanel, BoxLayout.Y_AXIS));
            gameOverPanel.setBounds(this.getWidth() / 2 - 150, this.getHeight() / 2 - 100,
                    300, 200);
            gameOverPanel.setBackground(Color.DARK_GRAY);

            JLabel gameOverLabel = new JLabel("GAME OVER", SwingConstants.CENTER);
            gameOverLabel.setForeground(Color.WHITE);
            gameOverLabel.setFont(new Font("Arial", Font.BOLD, 28));
            gameOverLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

            JButton newGame = new JButton("NEW GAME");
            JButton backToMenu = new JButton("BACK TO MENU");

            newGame.setFocusable(false);
            backToMenu.setFocusable(false);

            newGame.setForeground(Color.WHITE);
            newGame.setBackground(Color.BLUE);
            newGame.setOpaque(true);
            newGame.setAlignmentX(Component.CENTER_ALIGNMENT);

            backToMenu.setForeground(Color.WHITE);
            backToMenu.setBackground(Color.RED);
            backToMenu.setOpaque(true);
            backToMenu.setAlignmentX(Component.CENTER_ALIGNMENT);

            newGame.addActionListener(e -> {
                if(Setting.isSaveable()) {
                    this.user.updateUser(this.elapsedTime / 1000);
                    saveToFile();
                }
                this.frame.removeAll();
                this.frame.revalidate();
                this.frame.dispose();
                this.pattern.update();
                new gameFrame(this.user);
            });

            backToMenu.addActionListener(e -> {
                if(Setting.isSaveable()) {
                    saveToFile();
                }
                this.pattern.update();
                this.setVisible(false);
                this.frame.dispose();
                new menu();
            });

            gameOverPanel.add(Box.createVerticalStrut(10));
            gameOverPanel.add(gameOverLabel);
            gameOverPanel.add(Box.createVerticalStrut(15));
            gameOverPanel.add(newGame);
            gameOverPanel.add(Box.createVerticalStrut(10));
            gameOverPanel.add(backToMenu);
            this.add(gameOverPanel);
            this.revalidate();
            this.repaint();
            gameOverPanel.setVisible(true);
            this.pattern.update();
        }else{
            this.gameOverPanel.setVisible(true);
            this.revalidate();
            this.pattern.update();
        }
    }

    public void saveToFile(){
        UserManager userManager = new UserManager("src/Users/users.json");
        User user = userManager.getOrCreateUser(this.user.getUserName());
        user.updateUser(elapsedTime / 1000);
        userManager.saveToFile();
    }

}