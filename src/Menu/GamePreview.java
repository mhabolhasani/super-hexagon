package Menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Users.*;
import Game.*;

public class GamePreview extends JFrame {

    private JTextField usernameField;
    private JButton startButton;
    private JLabel messageLabel;

    public GamePreview() {
        setTitle("Game Preview Panel");
        setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        usernameField = new JTextField(20);
        startButton = new JButton("Start Game");
        messageLabel = new JLabel(" ", JLabel.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("username : "));
        inputPanel.add(usernameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(startButton);

        setLayout(new BorderLayout());
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(messageLabel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText().trim();
                if (username.isEmpty()) {
                    messageLabel.setText("please enter your username");
                    messageLabel.setForeground(Color.RED);
                } else {
                    messageLabel.setText("welcome " + username + "!");
                    messageLabel.setForeground(new Color(0, 128, 0));
                    UserManager userManager = new UserManager("src/Users/users.json");
                    User user = userManager.getOrCreateUser(username);
                    new gameFrame(user);
                    disposeFrame();
                }
            }
        });

        setVisible(true);
    }

    public void disposeFrame(){
        this.dispose();
    }

}
