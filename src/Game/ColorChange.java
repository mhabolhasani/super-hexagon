package Game;

import javax.swing.*;
import java.awt.*;

public class ColorChange {
    private float hue = 0.0f;
    public ColorChange(JPanel panel){
        Timer timer = new Timer(100, e  ->{
                hue += 0.01f;
                if (hue > 1.0f){
                    hue = 0.0f;
                }
                panel.setBackground(Color.getHSBColor(hue, 1.0f, 1.0f));
            }
        );
        timer.start();
    }
}
