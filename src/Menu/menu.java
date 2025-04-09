package Menu;

import javax.sound.sampled.*;
import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import Game.*;

public class menu extends JFrame implements MouseListener {
    JButton newGame;
    JButton History;
    JButton Setting;
    JButton Quit;
    JLabel best;

    public menu() {
        newGame = new JButton("New game");
        History = new JButton("History");
        Setting = new JButton("Setting");
        Quit = new JButton("Quit");
        best = new JLabel();

        JPanel keyPanel = new JPanel();
        keyPanel.setBounds(250, 150, 300, 550);
        keyPanel.setBackground(Color.black);
        keyPanel.setLayout(null);

        newGame.setBounds(0, 35, 300, 70);
        History.setBounds(0, 115, 300, 70);
        Setting.setBounds(0, 195, 300, 70);
        Quit.setBounds(0, 275, 300, 70);
        best.setBounds(0, 355, 200, 70);

        newGame.addMouseListener(this);
        History.addMouseListener(this);
        Setting.addMouseListener(this);
        Quit.addMouseListener(this);

        best.setText("best record is : ");
        best.setFont(new Font("Arial", Font.PLAIN, 18));
        best.setForeground(Color.green);
        best.setHorizontalAlignment(JLabel.CENTER);

        // adding the data later ----------------++++++++------------

        newGame.setFont(new Font("Army", Font.PLAIN, 14));
        History.setFont(new Font("Military", Font.PLAIN, 14));
        Setting.setFont(new Font("Military", Font.PLAIN, 14));
        Quit.setFont(new Font("Military", Font.PLAIN, 14));


        this.setTitle("Super Hexagon");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 600);
        this.setVisible(true);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(Color.CYAN);
        this.add(keyPanel);

        keyPanel.add(newGame);
        keyPanel.add(History);
        keyPanel.add(Setting);
        keyPanel.add(Quit);
        keyPanel.add(best);
    }

    public static void main(String[] args) {
        new menu();
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

        if(mouseEvent.getSource().equals(newGame)){
            this.setVisible(false);
            new gameFrame();
        }else if(mouseEvent.getSource().equals(History)){

        }else if(mouseEvent.getSource().equals(Setting)){

        }else if(mouseEvent.getSource().equals(Quit)){
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