package Menu;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class MusicPlayer {
    private static Clip clip;
    protected static boolean isRunning = false;

    protected static void playAudio(String filePath){
        try {
            if(!isRunning) {
                File audioFile = new File(filePath);
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
                clip = AudioSystem.getClip();
                clip.open(audioStream);
                if (Setting.isMuted() && clip.isRunning()) {
                    clip.start();
                    MusicPlayer.isRunning = true;
                }
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void MuteSong(){
        clip.stop();
    }

    public static void continuePlaying(){
        clip.start();
    }

}