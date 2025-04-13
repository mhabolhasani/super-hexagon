package Menu;

import javax.swing.*;
import java.awt.*;

public class Setting extends JPanel {
    private static Boolean muted;
    private static Boolean saveable;

    public Setting() {
        initalizeConditions();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBackground(new Color(30, 30, 30));
        this.setBorder(BorderFactory.createEmptyBorder(60, 200, 60, 200));

        JToggleButton muteButton = createToggleButton("Sound On");
        muteButton.addActionListener(e -> {
            Setting.muted = muteButton.isSelected();
            muteButton.setText(muted ? "Sound Off" : "Sound On");
            if (!muted) {
                MusicPlayer.MuteSong();
            } else {
                MusicPlayer.continuePlaying();
            }
        });

        JToggleButton saveButton = createToggleButton("Save Off");
        saveButton.addActionListener(e -> {
            Setting.saveable = saveButton.isSelected();
            saveButton.setText(saveable ? "Save On" : "Save Off");
        });

        JButton backButton = createStyledButton("Back to Menu");
        backButton.addActionListener(e -> menu.backToMenu(this));

        add(Box.createVerticalGlue());
        add(centerPanel(muteButton));
        add(Box.createVerticalStrut(20));
        add(centerPanel(saveButton));
        add(Box.createVerticalStrut(20));
        add(centerPanel(backButton));
        add(Box.createVerticalGlue());

        this.setVisible(true);
    }

    private JToggleButton createToggleButton(String text) {
        JToggleButton button = new JToggleButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(250, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setBackground(new Color(100, 160, 100));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setMaximumSize(new Dimension(250, 50));
        button.setAlignmentX(Component.CENTER_ALIGNMENT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    private JPanel centerPanel(JComponent component) {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.add(Box.createHorizontalGlue());
        panel.add(component);
        panel.add(Box.createHorizontalGlue());
        return panel;
    }

    public static boolean isMuted() {
        return muted;
    }

    public static boolean isSaveable() {
        return saveable;
    }

    public void initalizeConditions(){
        if(muted == null){
            muted = false;
        }
        if(saveable == null){
            saveable = true;
        }
    }
}
