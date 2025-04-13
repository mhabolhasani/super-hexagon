package Menu;

import javax.swing.*;
import java.awt.*;
import Users.*;

public class History extends JPanel {
    private JList<String> historyList;

    public History() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.DARK_GRAY);

        UserManager userManager = new UserManager("src/Users/users.json");
        userManager.loadFromFile();

        DefaultListModel<String> listModel = new DefaultListModel<>();
        for (User user : userManager.getAllUsers()) {
            for (HistoryItem historyItem : user.getHistory()) {
                listModel.addElement(historyItem.toString());
            }
        }

        this.historyList = new JList<>(listModel);
        this.historyList.setFont(new Font("Arial", Font.PLAIN, 18));
        this.historyList.setForeground(Color.WHITE);
        this.historyList.setBackground(new Color(50, 50, 50));
        this.historyList.setSelectionBackground(new Color(100, 100, 200));
        this.historyList.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(this.historyList);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
        this.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back To Menu");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(70, 130, 180));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        backButton.addActionListener(e -> menu.backToMenu(this));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.DARK_GRAY);
        buttonPanel.add(backButton);
        this.add(buttonPanel, BorderLayout.NORTH);

        this.setVisible(true);
    }
}
