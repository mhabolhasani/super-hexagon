package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gameFrame extends JFrame {
    public gameFrame(){
        this.setTitle("Super Hexagon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800,600);
        this.add(new GamePanel());
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}

class GamePanel extends JPanel implements KeyListener {
    public double Angle = 0;
    private Graphics2D g;
    private int centerX;
    private int centerY;
    private double angleOfMahlar = 0 ;
    private double Speed;
    private boolean isStopped = false;
    private Mahlar mahlar;
    private patternManger pattern;
    private boolean state;

    public GamePanel() {
        this.Speed = 0.2;
        this.state = false;

        this.setLayout(null);

        JLabel best = new JLabel("Best : "); // adding best later
        best.setBounds(0,0,75,75);
        best.setForeground(Color.WHITE);
        best.setBackground(Color.BLACK);
        best.setOpaque(true);
        best.setVisible(true);

        JLabel time = new JLabel("time ");
        time.setBounds(725,0,75,75);
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
            this.Angle += 0.15;
            repaint();
            this.state = Collision.checkCollision(
                    this.mahlar,
                    this.pattern.obsPattern.mainPattern ,
                    this.centerX ,
                    this.centerY
            );
            if(this.state == true){
                System.out.print("fucked");
                ((Timer) e.getSource()).stop();
                //new GameOver();
            }
        });
        timer.start();
    };

    private int[] getXHexagon(int centerX, int centerY) {
        int[] xPoints = new int[6];
        for(int i = 0; i < 6; i++){
            xPoints[i] = (int) (centerX + 50 * Math.cos(i * Math.PI / 3 + this.Angle));
        }
        return xPoints;
    }

    private int[] getYHexagon(int centerX, int centerY) {
        int[] yPoints = new int[6];
        for(int i = 0; i < 6; i++){
            yPoints[i] = (int) (centerY + 50 * Math.sin(i * Math.PI / 3 + this.Angle));
        }
        return yPoints;
    }

    private void paintLines(Graphics g, int[] xPoints, int[] yPoints, int centerX, int centerY) {
        for(int i = 0; i < 6; i++){
            int endX = (int) (centerX + 800 * Math.cos(i * Math.PI / 3 + this.Angle));
            int endY = (int) (centerY + 800 * Math.sin(i * Math.PI / 3 + this.Angle));
            g.drawLine(xPoints[i], yPoints[i], endX, endY);
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2D = (Graphics2D) g;
        if (this.g == null) {
            this.g = g2D;
        }
        super.paintComponent(g);
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int[] xPoints = getXHexagon(centerX, centerY);
        int[] yPoints = getYHexagon(centerX, centerY);
        g2D.drawPolygon(xPoints, yPoints, 6);
        paintLines(g, xPoints, yPoints, centerX, centerY);
        this.pattern = new patternManger(g2D , this.Angle , centerX , centerY);
        this.mahlar = new Mahlar(this.angleOfMahlar + this.Angle);
        this.mahlar.drawMahlar(g2D,centerX, centerY);
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        switch (keyEvent.getKeyChar()) {
            case 'a' :
                this.angleOfMahlar -= Math.PI / 3;
                this.mahlar.setAngle(this.mahlar.getAngle() - Math.PI / 3);
                break;
            case 'd' :
                this.angleOfMahlar += Math.PI / 3;
                this.mahlar.setAngle(this.mahlar.getAngle() + Math.PI / 3);
                break;
            case KeyEvent.VK_SPACE :
        }
        this.repaint();
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

/*
    public void StopGame(){
        this.isStopped = true;
        JPanel stopPanel = new JPanel();
        JButton Continue = new JButton("CONTINUE");
        JButton BacktoMenu = new JButton("BACK TO MENU");
        Continue.setForeground(Color.BLACK);
        BacktoMenu.setForeground(Color.BLACK);
        Continue.setFocusable(false);
        BacktoMenu.setFocusable(false);
        Continue.setBounds(this.getWidth() / 2 - 120  , this.getHeight() / 2 - 25 ,
                100, 50);
        BacktoMenu.setBounds(this.getWidth() / 2 - 20  , this.getHeight() / 2 - 25 ,
                100, 50);
        Continue.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {

            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

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
        });
        stopPanel.add(Continue);
        stopPanel.add(BacktoMenu);
        this.add(stopPanel);
    } */
}