package Game;

import javax.swing.*;
import java.awt.*;

public class test extends JFrame {
    private int centerX;
    private int centerY;
    private double theta;

    public static void main(String[] args) {
        new gameFrame();
    }

    public test() {
        theta = 0;
        this.setTitle("Super Hexagon");
        this.setVisible(true);
        this.getContentPane().setBackground(Color.BLACK);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        centerX = this.getWidth() / 2;
        centerY = this.getHeight() / 2;

        // استفاده از Timer برای به‌روزرسانی theta و repaint
        Timer timer = new Timer(16, e -> {
            theta += Math.toRadians(1); // افزایش theta به رادیان
            repaint(); // درخواست دوباره رسم پنل
        });
        timer.start(); // شروع تایمر
    }

    public int[] getXHexagon() {
        int[] xPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            xPoints[i] = (int) (centerX + 50 * Math.cos(i * Math.PI / 3 + theta));
        }
        return xPoints;
    }

    public int[] getYHexagon() {
        int[] yPoints = new int[6];
        for (int i = 0; i < 6; i++) {
            yPoints[i] = (int) (centerY + 50 * Math.sin(i * Math.PI / 3 + theta));
        }
        return yPoints;
    }

    public void paintLines(Graphics g, int[] xPoints, int[] yPoints) {
        for (int i = 0; i < 6; i++) {
            int endX = (int) (centerX + 800 * Math.cos(i * Math.PI / 3 + theta));
            int endY = (int) (centerY + 800 * Math.sin(i * Math.PI / 3 + theta));
            g.drawLine(xPoints[i], yPoints[i], endX, endY);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g); // پاک کردن محتوای قبلی
        Graphics2D g2D = (Graphics2D) g;

        // چرخش حول مرکز
        g2D.translate(centerX, centerY); // انتقال به مرکز
        g2D.rotate(theta); // چرخش

        // رسم شش‌ضلعی
        int[] Xpoints = getXHexagon();
        int[] Ypoints = getYHexagon();
        g2D.drawPolygon(Xpoints, Ypoints, 6);
        paintLines(g, Xpoints, Ypoints);

        // بازگشت به حالت اولیه
        g2D.rotate(-theta); // بازگشت به حالت اولیه
        g2D.translate(-centerX, -centerY); // بازگشت به موقعیت اولیه
    }
}